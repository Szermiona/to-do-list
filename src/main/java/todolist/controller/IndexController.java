package todolist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import todolist.dto.CreateTaskDTO;
import todolist.repository.TaskRepository;
import todolist.service.TaskRepositoryService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final TaskRepositoryService taskRepositoryService;
    private final TaskRepository taskRepository;


    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("taskList", taskRepository.findAll());
        return "index";
    }

    @GetMapping("/add_task")
    public String addForm(Model model) {
        model.addAttribute("task", new CreateTaskDTO());
        return "add_task";
    }

    @PostMapping("/add_task")
    public String add(@Valid @ModelAttribute CreateTaskDTO createTaskDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "add_task";
        }

        taskRepositoryService.addTask(createTaskDTO);
        return "added_new_task";
    }
}
