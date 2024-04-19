package com.tp.timeAhead.data.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private String name;
    private String description;
    private LocalDateTime reminder;
    private boolean isDone;
    private UUID userId;
}
