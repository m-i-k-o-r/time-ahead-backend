package com.tp.timeAhead.data.forms.category;

import java.util.UUID;

public record CategoryCreateForm(
        String name,
        UUID userId
) {

}
