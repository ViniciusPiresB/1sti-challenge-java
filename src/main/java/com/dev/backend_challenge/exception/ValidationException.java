package com.dev.backend_challenge.exception;

import com.dev.backend_challenge.enums.ErrorEnum;
import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException{
    private ErrorEnum error;

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(ErrorEnum err) {
        super(err.getMessage());
        this.error = err;
    }
}