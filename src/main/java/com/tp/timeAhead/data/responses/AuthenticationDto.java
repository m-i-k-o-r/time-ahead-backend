package com.tp.timeAhead.data.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record AuthenticationDto(
        @JsonProperty("access_token")
        String accessToken
) {

}
