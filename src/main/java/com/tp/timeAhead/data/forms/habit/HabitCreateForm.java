package com.tp.timeAhead.data.forms.habit;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class HabitCreateForm {
    private String name;
    private String description;
    private LocalTime reminderTime;
    private List<String> reminderDays;
    private UUID userId;
}