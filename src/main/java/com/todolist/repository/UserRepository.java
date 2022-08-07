package com.todolist.repository;

import com.todolist.entity.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserDAO, UUID> {

    UserDAO findByUserName(String userName);
}
