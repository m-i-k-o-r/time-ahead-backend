package com.tp.timeAhead.data.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDto {
    private String name;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private CategoryDto category;
    private UUID userId;
}
