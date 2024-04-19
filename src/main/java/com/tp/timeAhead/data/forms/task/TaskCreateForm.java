package com.tp.timeAhead.data.forms.task;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class TaskCreateForm {
    private String name;
    private String description;
    private LocalDateTime reminder;
    private boolean isDone;
    private UUID userId;
}