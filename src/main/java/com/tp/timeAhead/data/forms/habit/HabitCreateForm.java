package com.tp.timeAhead.data.forms.habit;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class HabitCreateForm {
    private String name;
    private String description;
    private String repeatReminder;
    private UUID userId;
}