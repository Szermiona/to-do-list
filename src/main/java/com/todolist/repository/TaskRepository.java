package com.todolist.repository;

import com.todolist.model.TaskDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<TaskDAO, UUID> {

}
