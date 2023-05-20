package com.kyaa.aggregatebusinesssolutiontaskbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.ZonedDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BankAccountNumberException.class)
    public ResponseEntity<ApiResponse> bankAccountExceptionHandler(BankAccountNumberException bankAccountNumberException){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiResponse apiResponse = ApiResponse.builder()
                .httpStatus(status)
                .isSuccessful(false)
                .message(bankAccountNumberException.getMessage())
                .timeStamp(ZonedDateTime.now())
                .statusCode(status.value())
                .build();
        return new ResponseEntity<>(apiResponse, status);

    }
}
