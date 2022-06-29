package todolist.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import todolist.domain.Category;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
public class TaskDTO {

    public TaskDTO(UUID id, String description, Category category, int priority, LocalDate deadline) {
        this.id = id;
        this.description = description;
        this.category = category;
        this.priority = priority;
        this.deadline = deadline;
    }

    private UUID id;
    private String description;
    private Category category;
    private int priority;
    private LocalDate deadline;
}
