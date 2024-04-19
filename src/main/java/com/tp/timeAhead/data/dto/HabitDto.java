package com.tp.timeAhead.data.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HabitDto {
    private String name;
    private String description;
    private String repeatReminder;
    private int numReminder;
    private boolean isDone;
    private UUID userId;
}
