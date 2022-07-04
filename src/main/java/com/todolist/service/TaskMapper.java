package com.todolist.service;

import org.springframework.stereotype.Component;
import com.todolist.dto.CreateTaskDTO;
import com.todolist.dto.TaskDTO;
import com.todolist.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TaskMapper {

    public TaskDTO mapToDto(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setCategory(task.getCategory());
        taskDTO.setDeadline(task.getDeadline());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setId(task.getId());
        taskDTO.setPriority(task.getPriority());
        return taskDTO;
    }

    public List<TaskDTO> mapToDto(List<Task> taskList) {
        if (Optional.ofNullable(taskList).isEmpty()) {
            return new ArrayList<>();
        } else {
            return taskList.stream()
                    .map(this::mapToDto)
                    .collect(Collectors.toList());
        }
    }
    
    public Task mapToEntity(CreateTaskDTO createTaskDTO) {
        Task task = new Task();
//        task.setId(UUID.randomUUID());
        task.setCategory(createTaskDTO.getCategory());
        task.setDeadline(createTaskDTO.getDeadline());
        task.setDescription(createTaskDTO.getDescription());
        task.setPriority(createTaskDTO.getPriority());
        return task;
    }
}
