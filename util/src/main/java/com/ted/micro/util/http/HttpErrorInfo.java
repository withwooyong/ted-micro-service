package com.ted.micro.util.http;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Slf4j
@Getter
@ToString
public class HttpErrorInfo {
    private final LocalDateTime timestamp;
    private final String path;
    private final HttpStatus httpStatus;
    private final String message;

    @Builder
    public HttpErrorInfo(LocalDateTime timestamp, String path, HttpStatus httpStatus, String message) {
        this.timestamp = timestamp;
        this.path = path;
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
