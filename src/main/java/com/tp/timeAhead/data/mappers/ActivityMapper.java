package com.tp.timeAhead.data.mappers;

import com.tp.timeAhead.data.responses.ActivityDto;
import com.tp.timeAhead.models.Activity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ActivityMapper {
    ActivityMapper INSTANCE = Mappers.getMapper(ActivityMapper.class);

    ActivityDto toDto(Activity item);

    List<ActivityDto> toDto(List<Activity> items);
}
