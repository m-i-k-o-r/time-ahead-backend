package com.tp.timeAhead.data.forms.activity;

import java.time.LocalDateTime;
import java.util.UUID;

public record ActivityForm(
        String name,
        String description,
        LocalDateTime startTime,
        LocalDateTime endTime,
        UUID categoryId
) {

}
