package com.todolist.dto;

import com.todolist.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskDTO {

    private String description;
    private Category category;
    private int priority;
    private LocalDate deadline;
}
