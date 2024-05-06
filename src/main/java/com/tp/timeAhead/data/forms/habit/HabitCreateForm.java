package com.tp.timeAhead.data.forms.habit;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public record HabitCreateForm(
        String name,
        String description,
        LocalTime reminderTime,
        List<String> reminderDays,
        UUID userId
) {

}
