package todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import todolist.entity.TaskDAO;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<TaskDAO, UUID> {

    @Override
    List<TaskDAO> findAll();
}
