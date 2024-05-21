package com.tp.timeAhead.data.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalTime;
import java.util.List;

@Schema(description = "Запрос на создание/изменение привычки")
public record HabitRequest(
        @Schema(description = "Название привычки")
        @NotBlank(message = "Название не может быть пустым")
        String name,

        @Schema(description = "Описание привычки")
        String description,

        @Schema(description = "Время напоминания", example = "23:45")
        @NotBlank(message = "Время не может быть пустым")
        LocalTime reminderTime,

        @Schema(description = "Дни напоминания", example = "[\"вторник\", \"среда\"]")
        @NotEmpty(message = "Список дней не может быть пустым")
        List<String> reminderDays
) {

}
