package com.todolist.controller;

import com.todolist.dto.CreateTaskDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.todolist.service.TaskService;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequiredArgsConstructor
public class IndexController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
    }

    private final TaskService taskService;


    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("taskList", taskService.fetchAll());
        return "index";
    }

    @GetMapping("/add_task")
    public String addForm(Model model) {
        model.addAttribute("task", new CreateTaskDTO());
        return "add_task";
    }

    @PostMapping("/add_task")
    public String add(@Valid @ModelAttribute("task") CreateTaskDTO createTaskDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add_task";
        }
        model.addAttribute("task", taskService.addTask(createTaskDTO));
        return "added_new_task";
    }
}
