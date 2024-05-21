package com.tp.timeAhead.data.requests;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Запрос на создание/изменение категории")
public record CategoryRequest(
        @Schema(description = "Название категории")
        @NotBlank(message = "Название не может быть пустым")
        String name
) {

}
