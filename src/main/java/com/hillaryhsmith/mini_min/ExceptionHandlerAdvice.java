// Based on https://stackoverflow.com/questions/32441919/how-return-error-message-in-spring-mvc-controller

package com.hillaryhsmith.mini_min;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity handleException(IllegalStateException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
    }

}
