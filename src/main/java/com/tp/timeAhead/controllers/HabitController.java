package com.tp.timeAhead.controllers;

import com.tp.timeAhead.data.requests.habit.HabitRequest;
import com.tp.timeAhead.data.requests.habit.HabitUpdateFlagRequest;
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
            description = "reminderTime следует передавать не в формате объекта, а строки, т.е. в формате \"HH:mm\" или \"HH:mm:ss\"",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Привычка создана")
            })
    @ResponseStatus(HttpStatus.OK)
    @PostMapping()
    public HabitDto createHabit(@RequestBody HabitRequest form) {
        return habitService.createHabit(form);
    }

    @Operation(
            summary = "Изменить привычку",
            description = "reminderTime следует передавать не в формате объекта, а строки, т.е. в формате \"HH:mm\" или \"HH:mm:ss\"",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Привычка изменена")
            })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public HabitDto updateHabit(@PathVariable UUID id, @RequestBody HabitRequest form) {
        return habitService.updateHabit(id, form);
    }

    @Operation(
            summary = "Измененить поля-флаги привычки",
            description = "changeComplete - меняет флаг done на противоположный и меняет numReminder, т.е. " +
                    "если done = false, то оно измениться на true и numReminder++, когда была поставлена отметка выполения привычки, " +
                    "а если done = true, то оно измениться на false и numReminder--, если отметка привычки была убрана; " +
                    "resetDone - поле done станет false, т.е. привычка сбросит отметку выполнения (например по завершению дня); " +
                    "changeEnd - меняет флаг end на противоположный (закончена ли привычка или нет);" +
                    "Отправлять надо все флаги, но только один из которых должен быть true (1 - true, 2 оставшихся  - false)",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Все привычки получены")
            })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}/flag")
    public HabitDto updateFlagHabit(@PathVariable UUID id, @RequestBody HabitUpdateFlagRequest form) {
        return habitService.updateFlagHabit(id, form);
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
            description = "day - день недели за который запрашиваются привычки, eсли его не передать, то будут просто получены все привычки; " +
                    "Флаг isEnd отвечает за то, каким будет список: из завершенных привычек или еще не завершенных",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Все привычки получены")
            })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<HabitDto> getAllHabits(@RequestParam(required = false) String day,
                                       @RequestParam(defaultValue = "false") boolean isEnd) {
        return habitService.getAllHabit(day, isEnd);
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
