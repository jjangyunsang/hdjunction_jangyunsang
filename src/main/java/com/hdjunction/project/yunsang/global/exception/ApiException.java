package com.hdjunction.project.yunsang.global.exception;

import com.hdjunction.project.yunsang.global.enums.ErrorCodes;

public class ApiException extends RuntimeException {
    public ApiException(ErrorCodes errorCodes) {
        super(errorCodes.getMessage());
    }
}
