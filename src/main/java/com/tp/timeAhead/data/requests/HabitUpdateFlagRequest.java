package com.tp.timeAhead.data.requests;

public record HabitUpdateFlagRequest(
        boolean changeComplete,
        boolean resetDone,
        boolean changeEnd
) {

}
