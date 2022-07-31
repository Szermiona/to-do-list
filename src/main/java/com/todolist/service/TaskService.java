package com.todolist.service;

import com.todolist.dto.CreateTaskDto;
import com.todolist.dto.TaskDto;
import com.todolist.entity.TaskDao;
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
    public TaskDto addTask(CreateTaskDto createTaskDTO) {
        TaskDao taskDAO = taskMapper.mapToEntity(createTaskDTO);
        taskRepository.save(taskDAO);
        return taskMapper.mapToDto(taskDAO);
    }

    @Transactional
    public TaskDto addTask(TaskDao taskDAO) {
        taskRepository.save(taskDAO);
        return taskMapper.mapToDto(taskDAO);
    }

    @Transactional
    public List<TaskDto> fetchAll() {
        List<TaskDao> all = taskRepository.findAll();
        return taskMapper.mapToDto(all);
    }

    @Transactional
    public TaskDto findById(UUID id) {
        Optional<TaskDao> task = taskRepository.findById(id);
        return task.map(taskMapper::mapToDto).orElse(null);
    }

    @Transactional
    public void deleteById(UUID id) {
        if (taskRepository.findById(id).isPresent()) {
            taskRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("TaskDao not found.");
        }
    }

    @Transactional
    public void deleteAll() {
        if (!taskRepository.findAll().isEmpty()) {
            taskRepository.deleteAll();
        } else {
            throw new IllegalArgumentException("TaskDao list is empty.");
        }
    }

}
