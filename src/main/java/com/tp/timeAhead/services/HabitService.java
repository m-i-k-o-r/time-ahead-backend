package com.tp.timeAhead.services;

import com.tp.timeAhead.data.mappers.HabitMapper;
import com.tp.timeAhead.data.requests.habit.HabitCreateRequest;
import com.tp.timeAhead.data.requests.habit.HabitUpdateFlagRequest;
import com.tp.timeAhead.data.requests.habit.HabitUpdateRequest;
import com.tp.timeAhead.data.responses.HabitDto;
import com.tp.timeAhead.exceptions.NotFoundException;
import com.tp.timeAhead.models.Habit;
import com.tp.timeAhead.repos.HabitRepository;
import com.tp.timeAhead.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HabitService {
    private final HabitRepository habitRepository;
    private final UserRepository userRepository;

    public HabitDto createHabit(HabitCreateRequest form) {
        String cron = String.format("0 %s %s * * %s",
                form.reminderTime().getMinute(),
                form.reminderTime().getHour(),
                String.join(",", form.reminderDays()).toLowerCase());
        return HabitMapper.INSTANCE.toDto(habitRepository.save(Habit.builder()
                .name(form.name())
                .description(form.description())
                .repeatReminder(cron)
                .numReminder(0)
                .isDone(false)
                .user(userRepository.findById(form.userId()).orElseThrow(() -> new NotFoundException("Пользователь с этим id не найден")))
                .build()));
    }

    public HabitDto updateHabit(UUID id, HabitUpdateRequest form) {
        Habit habit = habitRepository.findById(id).orElseThrow(() -> new NotFoundException("Привычка с этим id не найдена"));
        habit.setName(form.name());
        habit.setDescription(form.description());
        String cron = String.format("0 %s %s * * %s",
                form.reminderTime().getMinute(),
                form.reminderTime().getHour(),
                String.join(",", form.reminderDays()).toLowerCase());
        habit.setRepeatReminder(cron);
        return HabitMapper.INSTANCE.toDto(habitRepository.save(habit));
    }

    public HabitDto updateFlagHabit(UUID id, HabitUpdateFlagRequest form) {
        Habit habit = habitRepository.findById(id).orElseThrow(() -> new NotFoundException("Привычка с этим id не найдена"));
        if (form.changeComplete()) {
            if (habit.isDone()) {
                habit.setNumReminder(habit.getNumReminder() - 1);
            } else {
                habit.setNumReminder(habit.getNumReminder() + 1);
            }
            habit.setDone(!habit.isDone());
        } else if (form.resetDone()) {
            habit.setDone(false);
        } else if (form.changeEnd()) {
            habit.setEnd(!habit.isEnd());
        }
        return HabitMapper.INSTANCE.toDto(habitRepository.save(habit));
    }

    public HabitDto getHabit(UUID id) {
        return HabitMapper.INSTANCE.toDto(habitRepository.findById(id).orElseThrow(() -> new NotFoundException("Habit with this id not found")));
    }

    public List<HabitDto> getAllHabit(UUID userId, String day, boolean isEnd) {
        if (day == null) {
            return HabitMapper.INSTANCE.toDto(habitRepository.findAll(userId, isEnd));
        } else {
            return HabitMapper.INSTANCE.toDto(habitRepository.findAllByDay(userId, day, isEnd));
        }
    }

    public void deleteHabit(UUID id) {
        habitRepository.deleteById(id);
    }
}
