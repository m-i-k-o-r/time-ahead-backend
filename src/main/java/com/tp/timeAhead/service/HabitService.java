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
        return HabitMapper.INSTANCE.toDto(habitRepository.save(Habit.builder()
                .name(form.getName())
                .description(form.getDescription())
                .repeatReminder(form.getRepeatReminder())
                .numReminder(0)
                .isDone(false)
                .user(userRepository.findById(form.getUserId()).orElseThrow(() -> new NotFoundException("User with this id not found")))
                .build()));
    }

    public HabitDto updateHabit(UUID id, HabitForm form) {
        Habit habit = habitRepository.findById(id).orElseThrow(() -> new NotFoundException("Habit with this id not found"));
        habit.setName(form.getName());
        habit.setDescription(form.getDescription());
        habit.setRepeatReminder(form.getRepeatReminder());
        habit.setNumReminder(form.getNumReminder());
        habit.setDone(form.isDone());
        return HabitMapper.INSTANCE.toDto(habitRepository.save(habit));
    }

    public HabitDto getHabit(UUID id) {
        return HabitMapper.INSTANCE.toDto(habitRepository.findById(id).orElseThrow(() -> new NotFoundException("Habit with this id not found")));
    }

    public List<HabitDto> getAllHabit() {
        return HabitMapper.INSTANCE.toDto(habitRepository.findAll());
    }

    public void deleteHabit(UUID id) {
        habitRepository.deleteById(id);
    }
}
