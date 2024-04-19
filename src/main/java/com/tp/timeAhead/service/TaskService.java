package com.tp.timeAhead.service;

import com.tp.timeAhead.data.dto.TaskDto;
import com.tp.timeAhead.data.forms.task.TaskCreateForm;
import com.tp.timeAhead.data.forms.task.TaskForm;
import com.tp.timeAhead.data.mappers.TaskMapper;
import com.tp.timeAhead.exception.NotFoundException;
import com.tp.timeAhead.models.Task;
import com.tp.timeAhead.repos.TaskRepository;
import com.tp.timeAhead.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskDto createTask(TaskCreateForm form) {
        return TaskMapper.INSTANCE.toDto(taskRepository.save(Task.builder()
                .name(form.getName())
                .description(form.getDescription())
                .reminder(form.getReminder())
                .isDone(false)
                .user(userRepository.findById(form.getUserId()).orElseThrow(() -> new NotFoundException("User with this id not found")))
                .build()));
    }

    public TaskDto updateTask(UUID id, TaskForm form) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Task with this id not found"));
        task.setName(form.getName());
        task.setDescription(form.getDescription());
        task.setReminder(form.getReminder());
        task.setDone(form.isDone());
        return TaskMapper.INSTANCE.toDto(taskRepository.save(task));
    }

    public TaskDto getTask(UUID id) {
        return TaskMapper.INSTANCE.toDto(taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Task with this id not found")));
    }

    public List<TaskDto> getAllTask() {
        return TaskMapper.INSTANCE.toDto(taskRepository.findAll());
    }

    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }
}
