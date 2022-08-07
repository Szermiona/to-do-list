package com.todolist.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class UserDTO implements Serializable {

    private UUID id;
    private String username;
    private String password;
    private String email;
    private String role;
    private boolean isActive;

    public List<String> getRolesList() {
        if (role.length()>0) {
            return Arrays.asList(role.split(","));
        }
        return new ArrayList<>();
    }
}

