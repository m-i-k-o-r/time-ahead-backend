package com.tp.timeAhead.data.mappers;

import com.tp.timeAhead.data.dto.HabitDto;
import com.tp.timeAhead.models.Habit;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface HabitMapper {
    HabitMapper INSTANCE = Mappers.getMapper(HabitMapper.class);

    HabitDto toDto(Habit item);

    List<HabitDto> toDto(List<Habit> items);
}
