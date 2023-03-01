package ru.itis.kpfu.selyantsev.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final ExceptionMessage onAllExceptions(Exception exception) {
        return ExceptionMessage.builder()
                .message(exception.getMessage())
                .exceptionName(exception.getClass().getName())
                .build();
    }
}
