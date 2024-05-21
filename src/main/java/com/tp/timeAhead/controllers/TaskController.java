package com.tp.timeAhead.controllers;

import com.tp.timeAhead.data.responses.TaskDto;
import com.tp.timeAhead.data.requests.TaskRequest;
import com.tp.timeAhead.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    @Operation(
            summary = "Создать задачу",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Задача создана")
            })
    @ResponseStatus(HttpStatus.OK)
    @PostMapping()
    public TaskDto createTask(@RequestBody TaskRequest form) {
        return taskService.createTask(form);
    }

    @Operation(
            summary = "Изменить задачу",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Задача изменена")
            })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public TaskDto updateTask(@PathVariable UUID id, @RequestBody TaskRequest form) {
        return taskService.updateTask(id, form);
    }

    @Operation(
            summary = "Изменить завершение задачи",
            description = "Меняется значение поля done на противоположное",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Активность создана")
            })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}/complete")
    public TaskDto changeCompleteTask(@PathVariable UUID id) {
        return taskService.changeCompleteTask(id);
    }

    @Operation(
            summary = "Получить задачу",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Задача получена")
            })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public TaskDto getTask(@PathVariable UUID id) {
        return taskService.getTask(id);
    }

    @Operation(
            summary = "Получить все задачи",
            description = "Значение reminderSortDirection влияет на порядок списка задач (ASC, DESС), " +
                    "isDone - из чего будет список: из завершенных задач или не завершенных",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Все задачи получены")
            })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<TaskDto> getAllTasks(@RequestParam(defaultValue = "ASC") String reminderSortDirection,
                                     @RequestParam(defaultValue = "false") Boolean isDone) {
        return taskService.getAllTask(reminderSortDirection, isDone);
    }

    @Operation(
            summary = "Удалить задачу",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Задача удалена")
            })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable UUID id) {
        taskService.deleteTask(id);
    }
}
