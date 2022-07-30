package com.todolist.service;

import com.todolist.dto.CreateTaskDTO;
import com.todolist.dto.TaskDTO;
import com.todolist.entity.TaskDAO;
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
        List<TaskDTO> tasks = new ArrayList<>();

        CreateTaskDTO createTaskDTO1 = new CreateTaskDTO("TaskDAO 1", Category.WORK, 1, LocalDate.now().plusDays(5));
        tasks.add(taskService.addTask(createTaskDTO1));

        CreateTaskDTO createTaskDTO2 = new CreateTaskDTO("TaskDAO 2", Category.HOUSEHOLD, 2, LocalDate.now().plusDays(10));
        tasks.add(taskService.addTask(createTaskDTO2));

        CreateTaskDTO createTaskDTO3 = new CreateTaskDTO("TaskDAO 3", Category.GENERAL, 3, LocalDate.now().plusDays(15));
        tasks.add(taskService.addTask(createTaskDTO3));

        CreateTaskDTO createTaskDTO4 = new CreateTaskDTO("TaskDAO 4", Category.PERSONAL, 4, LocalDate.now().plusDays(20));
        tasks.add(taskService.addTask(createTaskDTO4));
    }

    @Test
    void addTask_returnsTaskDTO_givenCreateTaskDto() {
        CreateTaskDTO createTaskDTO = new CreateTaskDTO();
        createTaskDTO.setCategory(Category.HOUSEHOLD);
        createTaskDTO.setDeadline(LocalDate.now().plusDays(10));
        createTaskDTO.setDescription("task description");
        createTaskDTO.setPriority(3);
        TaskDTO taskDTO = taskService.addTask(createTaskDTO);
        Assertions.assertNotNull(taskDTO);
    }

    @Test
    void addTask_returnsTaskDTO_givenTaskObject() {
        TaskDAO taskDAO = new TaskDAO();
        taskDAO.setCategory(Category.HOUSEHOLD);
        taskDAO.setDeadline(LocalDate.now().plusDays(10));
        taskDAO.setDescription("taskDAO description");
        taskDAO.setPriority(3);
        TaskDTO taskDTO = taskService.addTask(taskDAO);
        Assertions.assertNotNull(taskDTO);
    }

    @Test
    void fetchAll_returnsListOfTasks() {
        List<TaskDTO> tasks = taskService.fetchAll();
        Assertions.assertEquals(4, tasks.size());
    }

    @Test
    void deleteById() {
        taskService.deleteById(taskRepository.findAll().get(0).getId());
        Assertions.assertEquals(3, taskRepository.findAll().size());
    }

    @Test
    void deleteAll() {
        taskService.deleteAll();
        Assertions.assertEquals(0, taskRepository.findAll().size());
    }
}