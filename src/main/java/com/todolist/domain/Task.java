package com.todolist.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class Task {

    @Size(min = 4, max = 20, message = "{task.validation.description}")
    private String description;

    private Category category;

    @Min(value = 1, message = "{task.validation.priority}")
    @Max(value = 5, message = "{task.validation.priority}")
    private int priority;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "{task.validation.null}")
    @Future(message = "{task.validation.date}")
    private LocalDate deadline;
}
