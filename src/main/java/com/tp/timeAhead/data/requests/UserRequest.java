package com.tp.timeAhead.data.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Запрос на регистрацию/аутентификацию и изменение пользователя")
public record UserRequest(
        @Schema(description = "Адрес электронной почты", example = "user@example.com")
        @Size(min = 10, max = 255, message = "Адрес электронной почты должен содержать от 10 до 255 символов")
        @NotBlank(message = "Адрес электронной почты не может быть пустым")
        @Email(message = "Email адрес должен быть в формате user@example.com")
        String email,

        @Schema(description = "Пароль")
        @Size(min = 4, max = 255, message = "Длина пароля должна быть от 4 до 255 символов")
        @NotBlank(message = "Пароль не может быть пустым")
        String password
) {

}
