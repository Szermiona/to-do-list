package com.todolist.service;

import com.todolist.dto.CreateTaskDto;
import com.todolist.dto.TaskDto;
import com.todolist.entity.TaskDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.todolist.domain.Category;
import com.todolist.repository.TaskRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class TaskServiceTest {

    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskRepository taskRepository;

    @BeforeEach
    void populateDatabase() {
        List<TaskDto> tasks = new ArrayList<>();

        CreateTaskDto createTaskDto1 = new CreateTaskDto("Task 1", Category.WORK, 1, LocalDate.now().plusDays(5));
        tasks.add(taskService.addTask(createTaskDto1));

        CreateTaskDto createTaskDto2 = new CreateTaskDto("Task 2", Category.HOUSEHOLD, 2, LocalDate.now().plusDays(10));
        tasks.add(taskService.addTask(createTaskDto2));

        CreateTaskDto createTaskDto3 = new CreateTaskDto("Task 3", Category.GENERAL, 3, LocalDate.now().plusDays(15));
        tasks.add(taskService.addTask(createTaskDto3));

        CreateTaskDto createTaskDto4 = new CreateTaskDto("Task 4", Category.PERSONAL, 4, LocalDate.now().plusDays(20));
        tasks.add(taskService.addTask(createTaskDto4));
    }

    @Test
    void addTask_returnsTaskDTO_givenCreateTaskDto() {
        CreateTaskDto createTaskDTO = new CreateTaskDto();
        createTaskDTO.setCategory(Category.HOUSEHOLD);
        createTaskDTO.setDeadline(LocalDate.now().plusDays(10));
        createTaskDTO.setDescription("task description");
        createTaskDTO.setPriority(3);
        TaskDto taskDTO = taskService.addTask(createTaskDTO);
        Assertions.assertNotNull(taskDTO);
    }

    @Test
    void addTask_returnsTaskDTO_givenTaskEntity() {
        TaskDao taskDAO = new TaskDao();
        taskDAO.setCategory(Category.HOUSEHOLD);
        taskDAO.setDeadline(LocalDate.now().plusDays(10));
        taskDAO.setDescription("taskDAO description");
        taskDAO.setPriority(3);
        TaskDto taskDTO = taskService.addTask(taskDAO);
        Assertions.assertNotNull(taskDTO);
    }

    @Test
    void fetchAll_returnsListOfTasks() {
        List<TaskDto> tasks = taskService.fetchAll();
        Assertions.assertEquals(4, tasks.size());
    }

    @Test
    void deleteById() {
        Assertions.assertEquals(4, taskRepository.findAll().size());
        taskService.deleteById(taskRepository.findAll().get(0).getId());
        Assertions.assertEquals(3, taskRepository.findAll().size());
    }

    @Test
    void deleteAll() {
        Assertions.assertEquals(4, taskRepository.findAll().size());
        taskService.deleteAll();
        Assertions.assertEquals(0, taskRepository.findAll().size());
    }
}