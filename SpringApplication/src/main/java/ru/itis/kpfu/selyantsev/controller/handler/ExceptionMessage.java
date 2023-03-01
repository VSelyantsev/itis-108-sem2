package ru.itis.kpfu.selyantsev.controller.handler;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class ExceptionMessage {

    /** Сообщение исключения */
    private String message;

    /** Наименование исключения */
    private String exceptionName;
}
