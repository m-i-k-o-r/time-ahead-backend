package com.tp.timeAhead.data.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Schema(description = "Запрос на создание/изменение задачи")
public record TaskRequest(
        @Schema(description = "Название задачи")
        @NotBlank(message = "Название не может быть пустым")
        String name,

        @Schema(description = "Описание задачи")
        String description,

        @Schema(description = "Время и дата напоминания задачи задачи", example = "2024-05-20T20:40")
        LocalDateTime reminder
) {

}
