package com.serviceworker.exception;

import org.springframework.http.HttpStatus;

public class ServiceWorkerApiException extends RuntimeException {

    private HttpStatus status;
    private String message;


    public ServiceWorkerApiException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ServiceWorkerApiException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

}

