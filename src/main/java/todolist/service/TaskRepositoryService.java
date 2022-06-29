package todolist.service;

import org.springframework.stereotype.Component;
import todolist.domain.Task;
import todolist.repository.TaskRepository;

import java.util.List;

@Component
public class TaskRepositoryService {

    public List<Task> getTasksList() {
        return TaskRepository.getTasksRepository();
    }

    public void addTask(Task task) {
        List<Task> taskList = TaskRepository.getTasksRepository();
        taskList.add(task);
    }
}
