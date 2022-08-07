package com.todolist.controller;

import com.todolist.domain.Task;
import com.todolist.dto.CreateTaskDTO;
import com.todolist.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class AuthorizedController {

    private final TaskService taskService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
    }

    @GetMapping("/add-task")
    public String displayAddForm(Model model) {
        model.addAttribute("task", new Task());
        return "add-task";
    }

    @PostMapping("/add-task")
    public String addNewTask(@Valid @ModelAttribute Task task, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-task";
        }
        CreateTaskDTO createTaskDTO = new CreateTaskDTO(task.getDescription(), task.getCategory(), task.getPriority(), task.getDeadline());
        model.addAttribute("task", taskService.addTask(createTaskDTO));
        model.addAttribute("taskList", taskService.fetchAll());
        return "index";
    }

    @GetMapping("/delete-task/{id}")
    public RedirectView deleteTask(@PathVariable("id") UUID id) {
        taskService.deleteById(id);
        return new RedirectView("/");
    }

    @GetMapping("/delete-all")
    public RedirectView deleteAllTasks() {
        taskService.deleteAll();
        return new RedirectView("/");
    }
}
