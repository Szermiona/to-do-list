package todolist.domain;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Task {

    @Size(min = 4, max = 20, message = "{task.validation.description}")
    @NotNull(message = "{task.validation.blank}")
    private String description;

    @NotNull(message = "{task.validation.blank}")
    private Category category;

    @Min(value = 1, message = "{task.validation.priority}")
    @Max(value = 5, message = "{task.validation.priority}")
    private int priority;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "{task.validation.date}")
    @NotNull(message = "{task.validation.blank}")
    private LocalDate deadline;
}
