package todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import todolist.domain.Task;
import todolist.service.TaskRepositoryService;

import javax.validation.Valid;

@Controller
public class IndexController {

    @Autowired
    TaskRepositoryService tasksRepositoryService;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("taskList", tasksRepositoryService.getTasksList());
        return "index";
    }

    @GetMapping("/add_task")
    public String addForm(Model model) {
        model.addAttribute("task", new Task());
        return "add_task";
    }

    @PostMapping("/add_task")
    public String add(@Valid @ModelAttribute Task task, BindingResult result) {
        if (result.hasErrors()) {
            return "add_task";
        }
        tasksRepositoryService.addTask(task);
        return "added_new_task";
    }
}
