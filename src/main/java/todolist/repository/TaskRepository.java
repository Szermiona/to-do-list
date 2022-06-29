package todolist.repository;

import todolist.domain.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    private static List<Task> tasksRepository = new ArrayList<>();

    public static List<Task> getTasksRepository() {
        return tasksRepository;
    }
}
