package com.todolist.service;

import com.todolist.dto.CreateUserDTO;
import com.todolist.dto.UserDTO;
import com.todolist.entity.UserDAO;
import com.todolist.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public void setUserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserDTO addUser(CreateUserDTO createUserDTO) {
        UserDAO user = new UserDAO(createUserDTO.getUserName(), createUserDTO.getPassword(), createUserDTO.getEmail(), createUserDTO.getRole(), createUserDTO.isActive());
        userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    public UserDTO findByUserName(String username) {
        UserDAO user = userRepository.findByUserName(username);
        return modelMapper.map(user, UserDTO.class);
    }
}
