package com.todolist.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = UserDao.TABLE_NAME)
public class UserDao {

    public static final String TABLE_NAME = "users";
    public static final String COLUMN_PREFIX = "u_";

    public UserDao(UUID id, String username, String password, String email, String role, boolean isActive) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.isActive = isActive;
    }

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(name = COLUMN_PREFIX + "id")
    private UUID id;

    @Column(name = COLUMN_PREFIX + "username")
    private String username;

    @Column(name = COLUMN_PREFIX + "password")
    private String password;

    @Column(name = COLUMN_PREFIX + "email")
    private String email;

    @Column(name = COLUMN_PREFIX + "role")
    private String role;

    @Column(name = COLUMN_PREFIX + "is_active")
    private boolean isActive;

    public List<String> getRolesList() {
        if (role.length() > 0) {
            return Arrays.asList(role.split(","));
        }
        return new ArrayList<>();
    }
}
