package com.tp.timeAhead.service;

import com.tp.timeAhead.data.dto.ActivityDto;
import com.tp.timeAhead.data.forms.activity.ActivityCreateForm;
import com.tp.timeAhead.data.forms.activity.ActivityForm;
import com.tp.timeAhead.data.mappers.ActivityMapper;
import com.tp.timeAhead.exception.NotFoundException;
import com.tp.timeAhead.models.Activity;
import com.tp.timeAhead.repos.ActivityRepository;
import com.tp.timeAhead.repos.CategoryRepository;
import com.tp.timeAhead.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public ActivityDto createActivity(ActivityCreateForm form) {
        return ActivityMapper.INSTANCE.toDto(activityRepository.save(Activity.builder()
                .name(form.getName())
                .description(form.getDescription())
                .startTime(form.getStartTime())
                .endTime(form.getEndTime())
                .category(categoryRepository.findById(form.getCategoryId()).orElseThrow(() -> new NotFoundException("Category with this id not found")))
                .user(userRepository.findById(form.getUserId()).orElseThrow(() -> new NotFoundException("User with this id not found")))
                .build()));
    }

    public ActivityDto updateActivity(UUID id, ActivityForm form) {
        Activity activity = activityRepository.findById(id).orElseThrow(() -> new NotFoundException("Activity with this id not found"));
        activity.setName(form.getName());
        activity.setDescription(form.getDescription());
        activity.setStartTime(form.getStartTime());
        activity.setEndTime(form.getEndTime());
        activity.setCategory(categoryRepository.findById(form.getCategoryId()).orElseThrow(() -> new NotFoundException("Category with this id not found")));
        return ActivityMapper.INSTANCE.toDto(activityRepository.save(activity));
    }

    public ActivityDto getActivity(UUID id) {
        return ActivityMapper.INSTANCE.toDto(activityRepository.findById(id).orElseThrow(() -> new NotFoundException("Activity with this id not found")));
    }

    public List<ActivityDto> getAllActivity(LocalDate data, UUID categoryId) {
        if (categoryId == null) {
            return ActivityMapper.INSTANCE.toDto(activityRepository.findAllByTime(data));
        } else {
            return ActivityMapper.INSTANCE.toDto(activityRepository.findAllByTimeAndCategoryId(data, categoryId));
        }
    }

    public void deleteActivity(UUID id) {
        activityRepository.deleteById(id);
    }
}
