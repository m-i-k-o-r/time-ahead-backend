package com.tp.timeAhead.data.requests.habit;

public record HabitUpdateFlagRequest(
        boolean changeComplete,
        boolean resetDone,
        boolean changeEnd
) {

}
