package com.tp.timeAhead.service;

import com.tp.timeAhead.data.dto.HabitDto;
import com.tp.timeAhead.data.forms.habit.HabitCreateForm;
import com.tp.timeAhead.data.forms.habit.HabitForm;
import com.tp.timeAhead.data.mappers.HabitMapper;
import com.tp.timeAhead.exception.NotFoundException;
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

    public HabitDto createHabit(HabitCreateForm form) {
        String cron = String.format("0 %s %s * * %s",
                form.getReminderTime().getMinute(),
                form.getReminderTime().getHour(),
                String.join(",", form.getReminderDays()).toLowerCase());
        return HabitMapper.INSTANCE.toDto(habitRepository.save(Habit.builder()
                .name(form.getName())
                .description(form.getDescription())
                .repeatReminder(cron)
                .numReminder(0)
                .isDone(false)
                .user(userRepository.findById(form.getUserId()).orElseThrow(() -> new NotFoundException("User with this id not found")))
                .build()));
    }

    public HabitDto updateHabit(UUID id, HabitForm form) {
        Habit habit = habitRepository.findById(id).orElseThrow(() -> new NotFoundException("Habit with this id not found"));
        habit.setName(form.getName());
        habit.setDescription(form.getDescription());
        String cron = String.format("0 %s %s * * %s",
                form.getReminderTime().getMinute(),
                form.getReminderTime().getHour(),
                String.join(",", form.getReminderDays()).toLowerCase());
        habit.setRepeatReminder(cron);
        return HabitMapper.INSTANCE.toDto(habitRepository.save(habit));
    }

    public HabitDto changeCompleteHabit(UUID id) {
        Habit habit = habitRepository.findById(id).orElseThrow(() -> new NotFoundException("Habit with this id not found"));
        if (habit.isDone()) {
            habit.setNumReminder(habit.getNumReminder() - 1);
            habit.setDone(false);
        } else {
            habit.setNumReminder(habit.getNumReminder() + 1);
            habit.setDone(true);
        }
        return HabitMapper.INSTANCE.toDto(habitRepository.save(habit));
    }

    public HabitDto enableHabit(UUID id) {
        Habit habit = habitRepository.findById(id).orElseThrow(() -> new NotFoundException("Habit with this id not found"));
        habit.setDone(false);
        return HabitMapper.INSTANCE.toDto(habitRepository.save(habit));
    }

    public HabitDto changeEndingHabit(UUID id) {
        Habit habit = habitRepository.findById(id).orElseThrow(() -> new NotFoundException("Habit with this id not found"));
        habit.setNumReminder(habit.getNumReminder() * -1);
        return HabitMapper.INSTANCE.toDto(habitRepository.save(habit));
    }

    public HabitDto getHabit(UUID id) {
        return HabitMapper.INSTANCE.toDto(habitRepository.findById(id).orElseThrow(() -> new NotFoundException("Habit with this id not found")));
    }

    public List<HabitDto> getAllHabit(UUID userId, String day) {
        if (day == null) {
            return HabitMapper.INSTANCE.toDto(habitRepository.findAll(userId));
        } else {
            return HabitMapper.INSTANCE.toDto(habitRepository.findAllByDay(userId, day));
        }
    }

    public void deleteHabit(UUID id) {
        habitRepository.deleteById(id);
    }
}
