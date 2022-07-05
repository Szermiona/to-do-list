package com.todolist.service;

import com.todolist.dto.CreateTaskDTO;
import com.todolist.dto.TaskDTO;
import com.todolist.model.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.todolist.model.Category;
import com.todolist.repository.TaskRepository;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class TaskServiceTest {

    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskRepository taskRepository;

    @Test
    void addTask_returnsTaskDTO_givenCreateTaskDto() {
        CreateTaskDTO createTaskDTO = new CreateTaskDTO();
        createTaskDTO.setCategory(Category.HOUSEHOLD);
        createTaskDTO.setDeadline(LocalDate.now().plusDays(10));
        createTaskDTO.setDescription("task description");
        createTaskDTO.setPriority(3);
        TaskDTO taskDTO = taskService.addTask(createTaskDTO);
        List<TaskDTO> taskDTOS = taskService.fetchAll();
        Assertions.assertNotNull(taskDTO);
    }

    @Test
    void addTask_returnsTaskDTO_givenTaskObject() {
        Task task = new Task();
        task.setCategory(Category.HOUSEHOLD);
        task.setDeadline(LocalDate.now().plusDays(10));
        task.setDescription("task description");
        task.setPriority(3);
        TaskDTO taskDTO = taskService.addTask(task);
        List<TaskDTO> taskDTOS = taskService.fetchAll();
        Assertions.assertNotNull(taskDTO);
    }

    @Test
    void fetchAll() {
    }
}