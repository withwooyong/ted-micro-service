package com.ted.micro.util.http;

import com.ted.micro.util.exception.InvalidInputException;
import com.ted.micro.util.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Slf4j
@RestControllerAdvice
class GlobalControllerExceptionHandler {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public @ResponseBody
    HttpErrorInfo handleNotFoundExceptions(ServerHttpRequest request, Exception exception) {
        return createHttpErrorInfo(NOT_FOUND, request, exception);
    }

    @ResponseStatus(UNPROCESSABLE_ENTITY)
    @ExceptionHandler(InvalidInputException.class)
    public @ResponseBody
    HttpErrorInfo handleInvalidInputException(ServerHttpRequest request, Exception exception) {
        return createHttpErrorInfo(UNPROCESSABLE_ENTITY, request, exception);
    }

    private HttpErrorInfo createHttpErrorInfo(HttpStatus httpStatus, ServerHttpRequest request, Exception exception) {
        final String path = request.getPath().pathWithinApplication().value();
        final String message = exception.getMessage();
        log.debug("Returning HTTP status: {} for path: {}, message: {}", httpStatus, path, message);
        return new HttpErrorInfo(LocalDateTime.now(), path, httpStatus, message);
    }
}