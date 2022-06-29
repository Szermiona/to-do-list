package todolist.entity;


import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;
import todolist.domain.Category;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TaskDAO {

    public static final String TABLE_NAME = "task";
    public static final String COLUMN_PREFIX = "t_";

    public TaskDAO(String description, Category category, int priority, LocalDate deadline) {
        this.description = description;
        this.category = category;
        this.priority = priority;
        this.deadline = deadline;
    }

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID id;

    @Size(min = 4, max = 20, message = "{task.validation.description}")
    @NotNull(message = "{task.validation.blank}")
    @Column(name = COLUMN_PREFIX + "id")
    private String description;

    @NotNull(message = "{task.validation.blank}")
    @Column(name = COLUMN_PREFIX + "category")
    private Category category;

    @Min(value = 1, message = "{task.validation.priority}")
    @Max(value = 5, message = "{task.validation.priority}")
    @Column(name = COLUMN_PREFIX + "priority")
    private int priority;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "{task.validation.date}")
    @NotNull(message = "{task.validation.blank}")
    @Column(name = COLUMN_PREFIX + "deadline")
    private LocalDate deadline;
}
