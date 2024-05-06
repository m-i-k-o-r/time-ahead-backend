package com.tp.timeAhead.controllers;

import com.tp.timeAhead.data.responses.TaskDto;
import com.tp.timeAhead.data.requests.task.TaskCreateRequest;
import com.tp.timeAhead.data.requests.task.TaskUpdateRequest;
import com.tp.timeAhead.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    @PostMapping()
    public TaskDto createTask(@RequestBody TaskCreateRequest form) {
        return taskService.createTask(form);
    }

    @PutMapping("/{id}")
    public TaskDto updateTask(@PathVariable UUID id, @RequestBody TaskUpdateRequest form) {
        return taskService.updateTask(id, form);
    }

    @PutMapping("/{id}/complete")
    public TaskDto changeCompleteTask(@PathVariable UUID id) {
        return taskService.changeCompleteTask(id);
    }

    @GetMapping("/{id}")
    public TaskDto getTask(@PathVariable UUID id) {
        return taskService.getTask(id);
    }

    @GetMapping()
    public List<TaskDto> getAllTasks(@RequestParam UUID userId,
                                     @RequestParam(defaultValue = "ASC") String reminderSortDirection,
                                     @RequestParam(defaultValue = "false") Boolean isDone) {
        return taskService.getAllTask(userId, reminderSortDirection, isDone);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable UUID id) {
        taskService.deleteTask(id);
    }
}
