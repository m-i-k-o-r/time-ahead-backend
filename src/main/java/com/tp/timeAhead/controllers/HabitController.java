package com.tp.timeAhead.controllers;

import com.tp.timeAhead.data.responses.HabitDto;
import com.tp.timeAhead.data.requests.habit.HabitCreateRequest;
import com.tp.timeAhead.data.requests.habit.HabitUpdateRequest;
import com.tp.timeAhead.services.HabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/habit")
public class HabitController {
    private final HabitService habitService;

    @PostMapping()
    public HabitDto createHabit(@RequestBody HabitCreateRequest form) {
        return habitService.createHabit(form);
    }

    @PutMapping("/{id}")
    public HabitDto updateHabit(@PathVariable UUID id, @RequestBody HabitUpdateRequest form) {
        return habitService.updateHabit(id, form);
    }

    @PutMapping("/{id}/complete")
    public HabitDto changeCompleteHabit(@PathVariable UUID id) {
        return habitService.changeCompleteHabit(id);
    }

    @PutMapping("/{id}/enable")
    public HabitDto enableHabit(@PathVariable UUID id) {
        return habitService.enableHabit(id);
    }

    @PutMapping("/{id}/end")
    public HabitDto changeEndingHabit(@PathVariable UUID id) {
        return habitService.changeEndingHabit(id);
    }

    @GetMapping("/{id}")
    public HabitDto getHabit(@PathVariable UUID id) {
        return habitService.getHabit(id);
    }

    @GetMapping()
    public List<HabitDto> getAllHabits(@RequestParam UUID userId,
                                       @RequestParam(required = false) String day) {
        return habitService.getAllHabit(userId, day);
    }

    @DeleteMapping("/{id}")
    public void deleteHabit(@PathVariable UUID id) {
        habitService.deleteHabit(id);
    }
}
