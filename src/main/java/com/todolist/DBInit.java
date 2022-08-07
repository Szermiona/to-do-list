package com.todolist;


import com.todolist.dto.CreateUserDTO;
import com.todolist.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DBInit implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(DBInit.class);

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setDBInit(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        CreateUserDTO createUserDTO1 = new CreateUserDTO("user", "user@mail.com", passwordEncoder.encode("user"), "USER", true);
        userService.addUser(createUserDTO1);

        CreateUserDTO createUserDTO2 = new CreateUserDTO("admin", "admin@mail.com", passwordEncoder.encode("admin"), "ADMIN", true);
        userService.addUser(createUserDTO2);

        LOGGER.info("***    Initialized database     ***");
    }
}
