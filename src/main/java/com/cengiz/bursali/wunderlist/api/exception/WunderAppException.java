package com.cengiz.bursali.wunderlist.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class WunderAppException extends RuntimeException {
    private ExceptionInfo exceptionInfo;
}
