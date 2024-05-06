package com.tp.timeAhead.data.requests.habit;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public record HabitCreateRequest(
        String name,
        String description,
        LocalTime reminderTime,
        List<String> reminderDays,
        UUID userId
) {

}
