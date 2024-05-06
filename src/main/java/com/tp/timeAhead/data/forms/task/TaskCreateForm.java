package com.tp.timeAhead.data.forms.task;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskCreateForm(
        String name,
        String description,
        LocalDateTime reminder,
        UUID userId
) {

}
