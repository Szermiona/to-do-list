package todolist.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import todolist.domain.Category;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CreateTaskDTO {

    public CreateTaskDTO(String description, Category category, int priority, LocalDate deadline) {
        this.description = description;
        this.category = category;
        this.priority = priority;
        this.deadline = deadline;
    }

    private String description;
    private Category category;
    private int priority;
    private LocalDate deadline;
}
