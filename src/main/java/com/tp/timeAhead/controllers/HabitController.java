package com.tp.timeAhead.controllers;

import com.tp.timeAhead.data.requests.habit.HabitCreateRequest;
import com.tp.timeAhead.data.requests.habit.HabitUpdateRequest;
import com.tp.timeAhead.data.responses.HabitDto;
import com.tp.timeAhead.services.HabitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/habit")
public class HabitController {
    private final HabitService habitService;

    @Operation(
            summary = "Создать привычку",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Привычка создана")
            })
    @ResponseStatus(HttpStatus.OK)
    @PostMapping()
    public HabitDto createHabit(@RequestBody HabitCreateRequest form) {
        return habitService.createHabit(form);
    }

    @Operation(
            summary = "Изменить привычку",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Привычка изменена")
            })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public HabitDto updateHabit(@PathVariable UUID id, @RequestBody HabitUpdateRequest form) {
        return habitService.updateHabit(id, form);
    }

    @Operation(
            summary = "Измененить завершение привычки",
            description = "Если done = false, то оно измениться на true и numReminder++, когда была поставлена отметка выполения привычки; " +
                    "Если done = true, то оно измениться на false и numReminder--, если отметка привычки была убрана",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Все привычки получены")
            })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}/complete")
    public HabitDto changeCompleteHabit(@PathVariable UUID id) {
        return habitService.changeCompleteHabit(id);
    }

    @Operation(
            summary = "Измененить отметку привычки",
            description = "Поле done станет false, т.е. привычка сбросит отметку выполнения (например по завершению дня)",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Все привычки получены")
            })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}/enable")
    public HabitDto enableHabit(@PathVariable UUID id) {
        return habitService.enableHabit(id);
    }

    @Operation(
            summary = "Изменить активность привычки",
            description = "Если numReminder имеет отрицательное значение - привычка завершена, если положительное - активна; " +
                    "Вызов этого метода меняет значение этого поля на противоположное",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Все привычки получены")
            })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}/end")
    public HabitDto changeEndingHabit(@PathVariable UUID id) {
        return habitService.changeEndingHabit(id);
    }

    @Operation(
            summary = "Получить привычку",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Привычка получена")
            })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public HabitDto getHabit(@PathVariable UUID id) {
        return habitService.getHabit(id);
    }

    @Operation(
            summary = "Получить все привычки",
            description = "day - день недели за который запрашиваются привычки; " +
                    "Если его не передать, то будут просто получены все привычки",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Все привычки получены")
            })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<HabitDto> getAllHabits(@RequestParam UUID userId,
                                       @RequestParam(required = false) String day) {
        return habitService.getAllHabit(userId, day);
    }

    @Operation(
            summary = "Удалить привычку",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Привычка удалена")
            })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteHabit(@PathVariable UUID id) {
        habitService.deleteHabit(id);
    }
}
