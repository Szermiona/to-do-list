package com.todolist.controller;

import com.todolist.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequiredArgsConstructor
public class AnonymousController {

    private final TaskService taskService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
    }

    @GetMapping("/")
    public String displayMainPage(Model model) {
        model.addAttribute("taskList", taskService.fetchAll());
        return "index";
    }

    @GetMapping("/signup")
    public String displayRegistrationForm() {
        return "signup";
    }

    @GetMapping("/login")
    public String displayLoginForm() {
        return "login";
    }
}
