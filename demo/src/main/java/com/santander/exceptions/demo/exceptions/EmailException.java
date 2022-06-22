package com.santander.exceptions.demo.exceptions;

public class EmailException extends RuntimeException{
    public EmailException(String message) {
        super(message);
    }
}
