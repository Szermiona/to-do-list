package com.todolist.service;

import com.todolist.dto.UserDTO;
import com.todolist.entity.TaskDAO;
import com.todolist.entity.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import com.todolist.dto.CreateTaskDTO;
import com.todolist.dto.TaskDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TaskMapper {

    private UserService userService;
    private UserMapper userMapper;

    @Autowired
    public void setTaskMapper(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    public TaskDTO mapToDto(TaskDAO taskDAO) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setCategory(taskDAO.getCategory());
        taskDTO.setDeadline(taskDAO.getDeadline());
        taskDTO.setDescription(taskDAO.getDescription());
        taskDTO.setId(taskDAO.getId());
        taskDTO.setPriority(taskDAO.getPriority());
        return taskDTO;
    }

    public List<TaskDTO> mapToDto(List<TaskDAO> taskDAOList) {
        if (Optional.ofNullable(taskDAOList).isEmpty()) {
            return new ArrayList<>();
        } else {
            return taskDAOList.stream()
                    .map(this::mapToDto)
                    .collect(Collectors.toList());
        }
    }
    
    public TaskDAO mapToEntity(CreateTaskDTO createTaskDTO) {
        TaskDAO taskDAO = new TaskDAO();
        taskDAO.setCategory(createTaskDTO.getCategory());
        taskDAO.setDeadline(createTaskDTO.getDeadline());
        taskDAO.setDescription(createTaskDTO.getDescription());
        taskDAO.setPriority(createTaskDTO.getPriority());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        UserDTO currentUserDTO = userService.findByUserName(currentPrincipalName);
        UserDAO currentUserDAO = userMapper.mapToUserDAO(currentUserDTO);
        taskDAO.setUserDAO(currentUserDAO);

        return taskDAO;
    }
}
