package com.cengiz.bursali.wunderlist.api.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExceptionInfo {
    private int number;
    private String message;
}
