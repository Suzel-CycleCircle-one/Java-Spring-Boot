package com.suzel.production.ready.features.Production.ready.advice;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiError {

    private LocalDateTime localDateTime;
    private String error;
    private HttpStatus statusCode;


    public ApiError() {
        this.localDateTime = LocalDateTime.now();
    }

    public ApiError(String error, HttpStatus statusCode) {
        this();
        this.error = error;
        this.statusCode = statusCode;
    }
}