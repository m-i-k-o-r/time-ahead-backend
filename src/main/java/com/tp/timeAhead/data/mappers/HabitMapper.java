package com.tp.timeAhead.data.mappers;

import com.tp.timeAhead.data.responses.HabitDto;
import com.tp.timeAhead.models.Habit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Mapper
public interface HabitMapper {
    HabitMapper INSTANCE = Mappers.getMapper(HabitMapper.class);

    @Mapping(source = "repeatReminder", target = "reminderTime", qualifiedByName = "cronToTime")
    @Mapping(source = "repeatReminder", target = "reminderDays", qualifiedByName = "cronToDays")
    HabitDto toDto(Habit item);

    List<HabitDto> toDto(List<Habit> items);

    @Named("cronToTime")
    default LocalTime cronToTime(String cronExpression) {
        String[] list = cronExpression.split(" ");
        int hour = Integer.parseInt(list[2]);
        int minute = Integer.parseInt(list[1]);
        return LocalTime.of(hour, minute);
    }

    @Named("cronToDays")
    default List<String> cronToDays(String cronExpression) {
        String[] list = cronExpression.split(" ");
        return Arrays.asList(list[5].split(","));
    }
}
