package com.tp.timeAhead.data.forms.activity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ActivityForm {
    private String name;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private UUID categoryId;
}
