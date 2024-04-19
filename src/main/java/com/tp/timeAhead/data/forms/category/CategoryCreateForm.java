package com.tp.timeAhead.data.forms.category;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CategoryCreateForm {
    private String name;
    private UUID userId;
}

