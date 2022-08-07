package com.todolist.domain;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class User {

    @Size(min = 5, max = 20, message = "{user.validation.userName}")
    @NotBlank(message = "{user.validation.userName}")
    private String username;

    @Size(min = 8, message = "{user.validation.password}")
    @NotBlank(message = "{user.validation.password}")
    private String password;

    @Email(message = "{user.validation.email}")
    @NotBlank(message = "{user.validation.email}")
    private String email;
}
