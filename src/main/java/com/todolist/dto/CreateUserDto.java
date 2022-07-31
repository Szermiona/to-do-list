package com.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {

        private String userName;
        private String email;
        private String password;
        private String role;
        private boolean isActive;
}
