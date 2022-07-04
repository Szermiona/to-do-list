package com.todolist.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import com.todolist.model.Category;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
public class TaskDTO {

    private UUID id;
    private String description;
    private Category category;
    private int priority;
    private LocalDate deadline;
}
