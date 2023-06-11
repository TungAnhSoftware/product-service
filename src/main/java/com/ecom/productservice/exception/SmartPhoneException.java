package com.ecom.productservice.exception;

import org.springframework.http.HttpStatus;

public class SmartPhoneException extends RuntimeException{
    private final HttpStatus status;

    public SmartPhoneException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

}
