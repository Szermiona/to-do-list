package com.todolist.model;


import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = TaskDAO.TABLE_NAME)
public class TaskDAO {

    public static final String TABLE_NAME = "tasks";
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
    @Column(name = COLUMN_PREFIX + "description")
    private String description;

    @Column(name = COLUMN_PREFIX + "category")
    private Category category;

    @Min(value = 1, message = "{task.validation.priority}")
    @Max(value = 5, message = "{task.validation.priority}")
    @Column(name = COLUMN_PREFIX + "priority")
    private int priority;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = COLUMN_PREFIX + "deadline")
    @NotNull(message = "{task.validation.null}")
    @Future(message = "{task.validation.date}")
    private LocalDate deadline;
}
