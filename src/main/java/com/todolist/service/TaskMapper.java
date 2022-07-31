package com.todolist.service;

import com.todolist.entity.TaskDao;
import org.springframework.stereotype.Component;
import com.todolist.dto.CreateTaskDto;
import com.todolist.dto.TaskDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TaskMapper {

    public TaskDto mapToDto(TaskDao taskDAO) {
        TaskDto taskDTO = new TaskDto();
        taskDTO.setCategory(taskDAO.getCategory());
        taskDTO.setDeadline(taskDAO.getDeadline());
        taskDTO.setDescription(taskDAO.getDescription());
        taskDTO.setId(taskDAO.getId());
        taskDTO.setPriority(taskDAO.getPriority());
        return taskDTO;
    }

    public List<TaskDto> mapToDto(List<TaskDao> taskDaoList) {
        if (Optional.ofNullable(taskDaoList).isEmpty()) {
            return new ArrayList<>();
        } else {
            return taskDaoList.stream()
                    .map(this::mapToDto)
                    .collect(Collectors.toList());
        }
    }
    
    public TaskDao mapToEntity(CreateTaskDto createTaskDTO) {
        TaskDao taskDAO = new TaskDao();
//        taskDAO.setId(UUID.randomUUID());
        taskDAO.setCategory(createTaskDTO.getCategory());
        taskDAO.setDeadline(createTaskDTO.getDeadline());
        taskDAO.setDescription(createTaskDTO.getDescription());
        taskDAO.setPriority(createTaskDTO.getPriority());
        return taskDAO;
    }
}
