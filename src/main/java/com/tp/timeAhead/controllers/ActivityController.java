package com.tp.timeAhead.controllers;

import com.tp.timeAhead.data.responses.ActivityDto;
import com.tp.timeAhead.data.requests.activity.ActivityCreateRequest;
import com.tp.timeAhead.data.requests.activity.ActivityUpdateRequest;
import com.tp.timeAhead.services.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/activity")
public class ActivityController {
    private final ActivityService activityService;

    @PostMapping()
    public ActivityDto createActivity(@RequestBody ActivityCreateRequest form) {
        return activityService.createActivity(form);
    }

    @PutMapping("/{id}")
    public ActivityDto updateActivity(@PathVariable UUID id, @RequestBody ActivityUpdateRequest form) {
        return activityService.updateActivity(id, form);
    }

    @GetMapping("/{id}")
    public ActivityDto getActivity(@PathVariable UUID id) {
        return activityService.getActivity(id);
    }

    @GetMapping()
    public List<ActivityDto> getAllActivities(@RequestParam UUID userId,
                                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data,
                                              @RequestParam(required = false) UUID categoryId) {
        return activityService.getAllActivity(userId, data, categoryId);
    }

    @DeleteMapping("/{id}")
    public void deleteActivity(@PathVariable UUID id) {
        activityService.deleteActivity(id);
    }
}
