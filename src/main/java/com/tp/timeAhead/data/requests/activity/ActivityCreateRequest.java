package com.tp.timeAhead.data.requests.activity;

import java.time.LocalDateTime;
import java.util.UUID;

public record ActivityCreateRequest(
        String name,
        String description,
        LocalDateTime startTime,
        LocalDateTime endTime,
        UUID categoryId,
        UUID userId
) {

}
