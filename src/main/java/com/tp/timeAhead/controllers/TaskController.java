package com.tp.timeAhead.controllers;

import com.tp.timeAhead.data.dto.TaskDto;
import com.tp.timeAhead.data.forms.task.TaskCreateForm;
import com.tp.timeAhead.data.forms.task.TaskForm;
import com.tp.timeAhead.service.TaskService;
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
    public TaskDto createTask(@RequestBody TaskCreateForm form) {
        return taskService.createTask(form);
    }

    @PutMapping("/{id}")
    public TaskDto updateTask(@PathVariable UUID id, @RequestBody TaskForm form) {
        return taskService.updateTask(id, form);
    }

    @GetMapping("/{id}")
    public TaskDto getTask(@PathVariable UUID id) {
        return taskService.getTask(id);
    }

    @GetMapping()
    public List<TaskDto> getAllTasks() {
        return taskService.getAllTask();
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable UUID id) {
        taskService.deleteTask(id);
    }
}
