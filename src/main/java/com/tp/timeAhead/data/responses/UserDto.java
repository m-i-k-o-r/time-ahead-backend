package com.tp.timeAhead.data.responses;

import java.util.Set;
import java.util.UUID;

public record UserDto(
        UUID id,
        String email,
        String password,
        Set<CategoryDto> categories
) {

}
