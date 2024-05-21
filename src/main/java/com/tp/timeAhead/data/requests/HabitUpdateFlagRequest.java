package com.tp.timeAhead.data.requests;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Запрос на изменение флагов привычки")
public record HabitUpdateFlagRequest(
        @Schema(description = "Изменение флага done на противоположный и меняет numReminder")
        boolean changeComplete,

        @Schema(description = "Присваивание флагу done значение false")
        boolean resetDone,

        @Schema(description = "Изменение флага end на противоположный")
        boolean changeEnd
) {

}
