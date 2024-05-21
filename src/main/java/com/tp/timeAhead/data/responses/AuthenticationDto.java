package com.tp.timeAhead.data.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.UUID;

@Builder
public record AuthenticationDto(
        @JsonProperty("access_token")
        String accessToken,
        UUID id
) {

}
