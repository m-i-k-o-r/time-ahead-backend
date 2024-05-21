package com.tp.timeAhead.data.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Запрос на аутентификацию администратора")
public record AdminRequest(
        @Schema(description = "логин")
        @Size(min = 5, max = 255, message = "Логин должен содержать от 5 до 100 символов")
        @NotBlank(message = "Логин не может быть пустым")
        String login,

        @Schema(description = "Пароль")
        @Size(min = 4, max = 255, message = "Длина пароля должна быть от 4 до 255 символов")
        @NotBlank(message = "Пароль не может быть пустым")
        String password
) {

}
