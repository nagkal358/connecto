package com.trinet.connecto.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class SequenceException extends RuntimeException{
    private static final long serialVersionId = 1L;
    private String errCode;
    private String errMsg;
}
