package com.tp.timeAhead.data.requests.task;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskCreateRequest(
        String name,
        String description,
        LocalDateTime reminder,
        UUID userId
) {

}
