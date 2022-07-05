package com.todolist.service;

import com.todolist.dto.CreateTaskDTO;
import com.todolist.dto.TaskDTO;
import com.todolist.model.Task;
import com.todolist.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }


    @Transactional
    public TaskDTO addTask(CreateTaskDTO createTaskDTO) {
        Task task = taskMapper.mapToEntity(createTaskDTO);
        taskRepository.save(task);
        return taskMapper.mapToDto(task);
    }

    @Transactional
    public List<TaskDTO> fetchAll() {
        List<Task> all = taskRepository.findAll();
        return taskMapper.mapToDto(all);
    }

    @Transactional
    public TaskDTO findById(UUID id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.map(taskMapper::mapToDto).orElse(null);
    }

}
