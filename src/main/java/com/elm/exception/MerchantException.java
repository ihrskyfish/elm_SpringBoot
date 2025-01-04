package com.elm.exception;

import com.elm.common.ErrorCode;
import lombok.Getter;

@Getter
public class MerchantException extends RuntimeException {
    private final int code;

    public MerchantException(int code, String message) {
        super(message);
        this.code = code;
    }

    public MerchantException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public MerchantException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }

}
