package com.tp.timeAhead.data.forms.habit;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HabitForm {
    private String name;
    private String description;
    private String repeatReminder;
    private int numReminder;
    private boolean isDone;
}
