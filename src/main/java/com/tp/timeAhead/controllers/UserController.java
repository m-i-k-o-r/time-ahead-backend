package com.tp.timeAhead.controllers;

import com.tp.timeAhead.data.responses.UserDto;
import com.tp.timeAhead.data.requests.user.UserRequest;
import com.tp.timeAhead.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Operation(
            summary = "Создать пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Пользователь создан")
            })
    @ResponseStatus(HttpStatus.OK)
    @PostMapping()
    public UserDto createUser(@RequestBody UserRequest form) {
        return userService.createUser(form);
    }

    @Operation(
            summary = "Изменить пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Пользователь изменен")
            })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable UUID id, @RequestBody UserRequest form) {
        return userService.updateUser(id, form);
    }

    @Operation(
            summary = "Получить пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Пользователь получен")
            })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable UUID id) {
        return userService.getUser(id);
    }

    @Operation(
            summary = "Получить всех пользователей",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Все пользователи получены")
            })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<UserDto> getAllUsers() {
        return userService.getAllUser();
    }

    @Operation(
            summary = "Удалить пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Пользователь удален")
            })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }
}
