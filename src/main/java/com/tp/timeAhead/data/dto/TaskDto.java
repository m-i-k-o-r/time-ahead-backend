package com.tp.timeAhead.data.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String name,
        String description,
        LocalDateTime reminder,
        boolean done
) {

}
