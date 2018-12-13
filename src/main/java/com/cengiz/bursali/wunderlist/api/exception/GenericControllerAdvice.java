package com.cengiz.bursali.wunderlist.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class GenericControllerAdvice {
    @ExceptionHandler(WunderException.class)
    private ResponseEntity<ExceptionInfo> handle(WunderException ex) {

        final ExceptionInfo exceptionInfo = ex.getExceptionInfo();
        return new ResponseEntity<>(exceptionInfo, HttpStatus.valueOf(exceptionInfo.getNumber()));
    }

}
