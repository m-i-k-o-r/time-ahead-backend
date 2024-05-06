package com.tp.timeAhead.data.mappers;

import com.tp.timeAhead.data.responses.UserDto;
import com.tp.timeAhead.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(User item);

    List<UserDto> toDto(List<User> items);
}
