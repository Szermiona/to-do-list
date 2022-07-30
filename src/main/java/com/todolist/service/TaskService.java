package com.todolist.service;

import com.todolist.dto.CreateTaskDTO;
import com.todolist.dto.TaskDTO;
import com.todolist.entity.TaskDAO;
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
    private final ModelMapper modelMapper;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.modelMapper = modelMapper;
    }


    @Transactional
    public TaskDTO addTask(CreateTaskDTO createTaskDTO) {
        TaskDAO taskDAO = taskMapper.mapToEntity(createTaskDTO);
        taskRepository.save(taskDAO);
        return taskMapper.mapToDto(taskDAO);
    }

    @Transactional
    public TaskDTO addTask(TaskDAO taskDAO) {
        taskRepository.save(taskDAO);
        return taskMapper.mapToDto(taskDAO);
    }

    @Transactional
    public List<TaskDTO> fetchAll() {
        List<TaskDAO> all = taskRepository.findAll();
        return taskMapper.mapToDto(all);
    }

    @Transactional
    public TaskDTO findById(UUID id) {
        Optional<TaskDAO> task = taskRepository.findById(id);
        return task.map(taskMapper::mapToDto).orElse(null);
    }

    @Transactional
    public void deleteById(UUID id) {
        if (taskRepository.findById(id).isPresent()) {
            taskRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("TaskDAO not found.");
        }
    }

    @Transactional
    public void deleteAll() {
        if (!taskRepository.findAll().isEmpty()) {
            taskRepository.deleteAll();
        } else {
            throw new IllegalArgumentException("TaskDAO list is empty.");
        }
    }

}
