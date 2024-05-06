package com.tp.timeAhead.data.mappers;

import com.tp.timeAhead.data.responses.TaskDto;
import com.tp.timeAhead.models.Task;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    TaskDto toDto(Task item);

    List<TaskDto> toDto(List<Task> items);
}

