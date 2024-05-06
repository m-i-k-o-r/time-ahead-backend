package com.tp.timeAhead.services;

import com.tp.timeAhead.data.mappers.TaskMapper;
import com.tp.timeAhead.data.requests.task.TaskCreateRequest;
import com.tp.timeAhead.data.requests.task.TaskUpdateRequest;
import com.tp.timeAhead.data.responses.TaskDto;
import com.tp.timeAhead.exceptions.NotFoundException;
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

    public TaskDto createTask(TaskCreateRequest form) {
        return TaskMapper.INSTANCE.toDto(taskRepository.save(Task.builder()
                .name(form.name())
                .description(form.description())
                .reminder(form.reminder())
                .isDone(false)
                .user(userRepository.findById(form.userId()).orElseThrow(() -> new NotFoundException("Пользователь с этим id не найден")))
                .build()));
    }

    public TaskDto updateTask(UUID id, TaskUpdateRequest form) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Задача с этим id не найдена"));
        task.setName(form.name());
        task.setDescription(form.description());
        task.setReminder(form.reminder());
        return TaskMapper.INSTANCE.toDto(taskRepository.save(task));
    }

    public TaskDto changeCompleteTask(UUID id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Задача с этим id не найдена"));
        task.setDone(!task.isDone());
        return TaskMapper.INSTANCE.toDto(taskRepository.save(task));
    }

    public TaskDto getTask(UUID id) {
        return TaskMapper.INSTANCE.toDto(taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Задача с этим id не найдена")));
    }

    public List<TaskDto> getAllTask(UUID userId, String reminderSortDirection, Boolean isDone) {
        if (reminderSortDirection.equalsIgnoreCase("asc")) {
            return TaskMapper.INSTANCE.toDto(taskRepository.findAllByOrderByReminderAsc(userId, isDone));
        } else if (reminderSortDirection.equalsIgnoreCase("desc")) {
            return TaskMapper.INSTANCE.toDto(taskRepository.findAllByOrderByReminderDesc(userId, isDone));
        }

        throw new NotFoundException("Неверное значение SortDirection: " + reminderSortDirection);
    }

    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }
}
