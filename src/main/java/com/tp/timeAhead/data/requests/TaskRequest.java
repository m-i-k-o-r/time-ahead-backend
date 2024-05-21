package com.tp.timeAhead.data.requests;

import java.time.LocalDateTime;

public record TaskRequest(
        String name,
        String description,
        LocalDateTime reminder
) {

}
