package com.jujuu.ourlog.common.response;

import lombok.Getter;

@Getter
public class OurlogException extends RuntimeException {
    private final OurlogErrorCode ourlogErrorCode;
    private String logMessage;

    public OurlogException(OurlogErrorCode errorCode) {
        super(errorCode.getMessage());
        this.ourlogErrorCode = errorCode;
    }

    public OurlogException(OurlogErrorCode errorCode, String logMessage) {
        super(errorCode.getMessage());
        this.ourlogErrorCode = errorCode;
        this.logMessage = logMessage;
    }
}
