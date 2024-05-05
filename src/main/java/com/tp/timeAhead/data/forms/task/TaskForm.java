package com.tp.timeAhead.data.forms.task;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskForm {
    private String name;
    private String description;
    private LocalDateTime reminder;
}
