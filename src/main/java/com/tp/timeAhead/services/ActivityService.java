package com.tp.timeAhead.services;

import com.tp.timeAhead.data.mappers.ActivityMapper;
import com.tp.timeAhead.data.requests.activity.ActivityRequest;
import com.tp.timeAhead.data.responses.ActivityDto;
import com.tp.timeAhead.exceptions.NotFoundException;
import com.tp.timeAhead.models.Activity;
import com.tp.timeAhead.models.User;
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

    private final JwtService jwtService;

    public ActivityDto createActivity(ActivityRequest form) {
        return ActivityMapper.INSTANCE.toDto(activityRepository.save(Activity.builder()
                .name(form.name())
                .description(form.description())
                .startTime(form.startTime())
                .endTime(form.endTime())
                .category(categoryRepository.findById(form.categoryId())
                        .orElseThrow(() -> new NotFoundException("Категория с этим id не найдена")))
                .user(userRepository.findById(((User) jwtService.extractUserDetails()).getId())
                        .orElseThrow(() -> new NotFoundException("Пользователь с этим id не найден")))
                .build()));
    }

    public ActivityDto updateActivity(UUID id, ActivityRequest form) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Активность с этим id не найдена"));

        activity.setName(form.name());
        activity.setDescription(form.description());
        activity.setStartTime(form.startTime());
        activity.setEndTime(form.endTime());
        activity.setCategory(categoryRepository.findById(form.categoryId())
                .orElseThrow(() -> new NotFoundException("Категория с этим id не найдена")));

        return ActivityMapper.INSTANCE.toDto(activityRepository.save(activity));
    }

    public ActivityDto getActivity(UUID id) {
        return ActivityMapper.INSTANCE.toDto(activityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Активность с этим id не найдена")));
    }

    public List<ActivityDto> getAllActivity(LocalDate data, UUID categoryId) {
        if (categoryId == null) {
            return ActivityMapper.INSTANCE.toDto(
                    activityRepository.findAllByTime(
                            ((User) jwtService.extractUserDetails()).getId(),
                            data
                    ));
        } else {
            return ActivityMapper.INSTANCE.toDto(
                    activityRepository.findAllByTimeAndCategoryId(
                            ((User) jwtService.extractUserDetails()).getId(),
                            data,
                            categoryId
                    ));
        }
    }

    public void deleteActivity(UUID id) {
        activityRepository.deleteById(id);
    }
}
