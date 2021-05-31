package com.example.domain.parking.exception;

public class GlobalException extends RuntimeException {

    public GlobalException(String message, Exception exception) {
        super(message, exception.getCause());
    }

}
