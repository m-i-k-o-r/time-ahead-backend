package com.tp.timeAhead.controllers;

import com.tp.timeAhead.data.requests.ActivityRequest;
import com.tp.timeAhead.data.responses.ActivityDto;
import com.tp.timeAhead.services.ActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/activity")
public class ActivityController {
    private final ActivityService activityService;

    @Operation(
            summary = "Создать активность",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Активность создана")
            })
    @ResponseStatus(HttpStatus.OK)
    @PostMapping()
    public ActivityDto createActivity(@RequestBody @Validated ActivityRequest form) {
        return activityService.createActivity(form);
    }

    @Operation(
            summary = "Изменить активность",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Активность изменена")
            })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public ActivityDto updateActivity(@PathVariable UUID id, @RequestBody @Validated ActivityRequest form) {
        return activityService.updateActivity(id, form);
    }

    @Operation(
            summary = "Получить активность",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Активность получена")
            })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ActivityDto getActivity(@PathVariable UUID id) {
        return activityService.getActivity(id);
    }

    @Operation(
            summary = "Получить все активности",
            description = "data - дата за которую требуется получить список выполненых активностей; " +
                    "categoryId - категория по которой нужно найти все активности за этот день (data)",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Все активности получены")
            })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<ActivityDto> getAllActivities(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data,
                                              @RequestParam(required = false) UUID categoryId) {
        return activityService.getAllActivity(data, categoryId);
    }

    @Operation(
            summary = "Удалить активность",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Активность удалена")
            })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteActivity(@PathVariable UUID id) {
        activityService.deleteActivity(id);
    }
}
