package com.jujuu.ourlog.common.response;

import lombok.Getter;

@Getter
public class OurlogException extends RuntimeException {
    private final OurlogErrorCode ourlogErrorCode;

    public OurlogException(OurlogErrorCode errorCode) {
        super(errorCode.getMessage());
        this.ourlogErrorCode = errorCode;
    }
}
