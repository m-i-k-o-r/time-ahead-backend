package com.tp.timeAhead.data.requests.habit;

import java.time.LocalTime;
import java.util.List;

public record HabitRequest(
        String name,
        String description,
        LocalTime reminderTime,
        List<String> reminderDays
) {

}
