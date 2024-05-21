package com.tp.timeAhead.data.requests;

import java.time.LocalDateTime;
import java.util.UUID;

public record ActivityRequest(
        String name,
        String description,
        LocalDateTime startTime,
        LocalDateTime endTime,
        UUID categoryId
) {

}
