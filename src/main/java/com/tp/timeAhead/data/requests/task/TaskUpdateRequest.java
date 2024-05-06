package com.tp.timeAhead.data.requests.task;

import java.time.LocalDateTime;

public record TaskUpdateRequest(
        String name,
        String description,
        LocalDateTime reminder
) {

}
