package com.todolist.service;

import com.todolist.dto.CreateTaskDTO;
import com.todolist.dto.CreateUserDTO;
import com.todolist.dto.UserDTO;
import com.todolist.entity.TaskDAO;
import com.todolist.entity.UserDAO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO mapToDto(UserDAO userDAO) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userDAO.getId());
        userDTO.setActive(userDAO.isActive());
        userDTO.setEmail(userDAO.getEmail());
        userDTO.setUserName(userDAO.getUserName());
        userDTO.setPassword(userDAO.getPassword());
        userDTO.setRole(userDAO.getRole());
        return userDTO;
    }

    public UserDAO mapToEntity(CreateUserDTO createUserDTO) {
        UserDAO userDAO = new UserDAO();
        userDAO.setUserName(createUserDTO.getUserName());
        userDAO.setPassword(createUserDTO.getPassword());
        userDAO.setEmail(createUserDTO.getEmail());
        userDAO.setRole(createUserDTO.getRole());
        userDAO.setActive(createUserDTO.isActive());
        return userDAO;
    }

    public UserDAO mapToUserDAO(UserDTO userDTO) {
        UserDAO userDAO = new UserDAO();
        userDAO.setId(userDTO.getId());
        userDAO.setUserName(userDTO.getUserName());
        userDAO.setEmail(userDTO.getEmail());
        userDAO.setPassword(userDTO.getPassword());
        userDAO.setRole(userDTO.getRole());
        userDAO.setActive(userDTO.isActive());
        return userDAO;
    }
}
