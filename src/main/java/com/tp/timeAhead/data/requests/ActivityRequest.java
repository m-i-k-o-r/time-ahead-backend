package com.tp.timeAhead.data.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "Запрос на создание/изменение активности")
public record ActivityRequest(
        @Schema(description = "Название активности")
        @NotBlank(message = "Название не может быть пустым")
        String name,

        @Schema(description = "Описание активности")
        String description,

        @Schema(description = "Время начала", example = "2024-05-19T8:30")
        @NotBlank(message = "Время начала не может быть пустым")
        LocalDateTime startTime,

        @Schema(description = "Время завершения", example = "2024-05-20T20:40")
        LocalDateTime endTime,

        @Schema(description = "id категории")
        UUID categoryId
) {

}
