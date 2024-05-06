package com.tp.timeAhead.data.responses;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public record HabitDto(
        UUID id,
        String name,
        String description,
        LocalTime reminderTime,
        List<String> reminderDays,
        int numReminder,
        boolean done
) {

}
