package com.todolist.controller;

import com.todolist.model.Task;
import com.todolist.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class TaskController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
    }

    private final TaskService taskService;

    @GetMapping("/add-task")
    public String displayAddForm(Model model) {
        model.addAttribute("task", new Task());
        return "add-task";
    }

    @PostMapping("/add-task")
    public String addNewTask(@Valid Task task, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-task";
        }
        model.addAttribute("task", taskService.addTask(task));
        model.addAttribute("taskList", taskService.fetchAll());
        return "index";
    }

    @GetMapping("/delete-task/{id}")
    public RedirectView deleteTask(@PathVariable("id") UUID id) {
        taskService.deleteById(id);
        return new RedirectView("/");
    }

    @GetMapping("/delete-all")
    public String deleteAllTasks() {
        taskService.deleteAll();
        return "index";
    }
}
