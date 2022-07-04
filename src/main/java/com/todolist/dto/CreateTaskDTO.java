package com.todolist.dto;

import lombok.Data;
import com.todolist.model.Category;

import java.time.LocalDate;

@Data
public class CreateTaskDTO {

    private String description;
    private Category category;
    private int priority;
    private LocalDate deadline;
}
