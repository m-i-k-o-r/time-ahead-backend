package com.tp.timeAhead.data.forms.habit;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class HabitForm {
    private String name;
    private String description;
    private LocalTime reminderTime;
    private List<String> reminderDays;
    private int numReminder;
    private boolean isDone;
}
