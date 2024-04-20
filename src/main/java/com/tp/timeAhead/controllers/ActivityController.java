package com.tp.timeAhead.controllers;

import com.tp.timeAhead.data.dto.ActivityDto;
import com.tp.timeAhead.data.forms.activity.ActivityCreateForm;
import com.tp.timeAhead.data.forms.activity.ActivityForm;
import com.tp.timeAhead.service.ActivityService;
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
    public ActivityDto createActivity(@RequestBody ActivityCreateForm form) {
        return activityService.createActivity(form);
    }

    @PutMapping("/{id}")
    public ActivityDto updateActivity(@PathVariable UUID id, @RequestBody ActivityForm form) {
        return activityService.updateActivity(id, form);
    }

    @GetMapping("/{id}")
    public ActivityDto getActivity(@PathVariable UUID id) {
        return activityService.getActivity(id);
    }

    @GetMapping()
    public List<ActivityDto> getAllActivities(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data,
                                              @RequestParam(required = false) UUID categoryId) {
        return activityService.getAllActivity(data, categoryId);
    }

    @DeleteMapping("/{id}")
    public void deleteActivity(@PathVariable UUID id) {
        activityService.deleteActivity(id);
    }
}
