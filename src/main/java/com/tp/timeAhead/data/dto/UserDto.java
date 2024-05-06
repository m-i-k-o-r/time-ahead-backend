package com.tp.timeAhead.data.dto;

import java.util.Set;
import java.util.UUID;

public record UserDto(
        UUID id,
        String email,
        String password,
        Set<CategoryDto> categories
) {

}
