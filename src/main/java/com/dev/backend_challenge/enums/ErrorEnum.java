package com.dev.backend_challenge.enums;

import lombok.Getter;

@Getter
public enum ErrorEnum {
    NOT_FOUND(404, "Entity not found"),
    DELETED_ENTITY(400, "Entity deleted");

    private final Integer statusCode;
    private final String message;

    ErrorEnum(Integer statusCode, String message){
        this.statusCode = statusCode;
        this.message = message;
    }
}
