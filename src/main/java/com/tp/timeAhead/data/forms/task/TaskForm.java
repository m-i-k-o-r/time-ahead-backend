package com.tp.timeAhead.data.forms.task;

import java.time.LocalDateTime;

public record TaskForm(
        String name,
        String description,
        LocalDateTime reminder
) {

}
