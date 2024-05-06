package com.tp.timeAhead.data.forms.habit;

import java.time.LocalTime;
import java.util.List;

public record HabitForm(
        String name,
        String description,
        LocalTime reminderTime,
        List<String> reminderDays
) {

}
