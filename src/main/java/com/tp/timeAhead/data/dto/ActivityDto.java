package com.tp.timeAhead.data.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ActivityDto(
        UUID id,
        String name,
        String description,
        LocalDateTime startTime,
        LocalDateTime endTime,
        CategoryDto category
) {

}
