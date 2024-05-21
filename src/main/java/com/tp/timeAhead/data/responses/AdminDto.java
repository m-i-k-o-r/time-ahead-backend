package com.tp.timeAhead.data.responses;

import java.util.UUID;

public record AdminDto(
        UUID id,
        String login,
        String password
) {

}
