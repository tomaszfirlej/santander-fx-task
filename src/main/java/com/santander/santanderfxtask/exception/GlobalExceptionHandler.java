package com.santander.santanderfxtask.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler({
            CurrencyPairUnrecognizedException.class
    })

    public final ResponseEntity<ApiErrorResponse> handleGameHandshakeExceptions(Exception ex) {

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                "WRONG CURRENCY PAIR",
                "Please correct currency pair key."
        );
        return new ResponseEntity<ApiErrorResponse>(apiErrorResponse, HttpStatus.valueOf(400));

    }


}
