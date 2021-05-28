package com.example.domain.parking.exception;

public class GlobalException extends RuntimeException {

    public GlobalException(String exception, Throwable cause) {
        super(exception);
    }

}
