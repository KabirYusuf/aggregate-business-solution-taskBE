package com.kyaa.aggregatebusinesssolutiontaskbackend.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Setter
@Getter
@Builder
public class ApiResponse {
    private Object data;
    private final ZonedDateTime timeStamp = ZonedDateTime.now();
    private int statusCode;
    private HttpStatus httpStatus;
    private boolean isSuccessful;
}
