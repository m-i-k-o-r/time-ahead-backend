package com.tp.timeAhead.data.dto;

import lombok.*;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HabitDto {
    private String name;
    private String description;
    private LocalTime reminderTime;
    private List<String> reminderDays;
    private int numReminder;
    private boolean isDone;
    private UUID userId;
}
