package com.todolist.entity;


import com.todolist.domain.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = TaskDao.TABLE_NAME)
public class TaskDao {

    public static final String TABLE_NAME = "tasks";
    public static final String COLUMN_PREFIX = "t_";

    public TaskDao(String description, Category category, int priority, LocalDate deadline, UserDao userDao) {
        this.description = description;
        this.category = category;
        this.priority = priority;
        this.deadline = deadline;
        this.userDao = userDao;
    }

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(name = COLUMN_PREFIX + "id")
    private UUID id;

    @Column(name = COLUMN_PREFIX + "description")
    private String description;

    @Column(name = COLUMN_PREFIX + "category")
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = COLUMN_PREFIX + "priority")
    private int priority;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = COLUMN_PREFIX + "deadline")
    private LocalDate deadline;

    @ManyToOne
    @JoinColumn(name = UserDao.COLUMN_PREFIX + "id")
//    @NotNull
    private UserDao userDao;
}
