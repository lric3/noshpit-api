package com.lric3.noshpit.api.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApiError {
    private int status;
    private String message;
    private String path;
    private LocalDateTime timestamp = LocalDateTime.now();

    public ApiError(int status, String message, String path) {
        this.status = status;
        this.message = message;
        this.path = path;
    }
}
