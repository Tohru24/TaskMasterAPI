package com.app.todoapp.service.dto;

import com.app.todoapp.persistence.entity.TaskStatus;
import lombok.Data;
import java.time.LocalDateTime;
@Data
public class TaskInDTO {
    private String title;
    private String description;
    private LocalDateTime eta;

}
