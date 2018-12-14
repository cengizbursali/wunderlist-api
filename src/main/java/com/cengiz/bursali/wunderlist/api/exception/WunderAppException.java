package com.cengiz.bursali.wunderlist.api.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class WunderAppException extends RuntimeException {
    private final ExceptionInfo exceptionInfo;
}
