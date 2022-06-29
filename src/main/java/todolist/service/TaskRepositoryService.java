package todolist.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todolist.dto.CreateTaskDTO;
import todolist.dto.TaskDTO;
import todolist.entity.TaskDAO;
import todolist.repository.TaskRepository;

@Service
public class TaskRepositoryService {

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    public TaskRepositoryService(TaskRepository taskRepository, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public TaskDTO addTask(CreateTaskDTO createTaskDTO) {
        TaskDAO taskDAO = new TaskDAO(createTaskDTO.getDescription(),createTaskDTO.getCategory(), createTaskDTO.getPriority(), createTaskDTO.getDeadline());
        taskRepository.save(taskDAO);
        return modelMapper.map(taskDAO, TaskDTO.class);
    }
}
