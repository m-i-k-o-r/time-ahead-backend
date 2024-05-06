package com.tp.timeAhead.data.requests.category;

import java.util.UUID;

public record CategoryCreateRequest(
        String name,
        UUID userId
) {

}
