package com.dev.backend_challenge.enums;

public enum Status {
    ACTIVE("ACTIVE"),
    DELETED("DELETED");

    private final String message;

    Status(String message){
        this.message = message;
    }
}
