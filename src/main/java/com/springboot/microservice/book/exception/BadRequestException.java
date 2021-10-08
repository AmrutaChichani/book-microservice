package com.springboot.microservice.book.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(){
        super("Bad request posted");
    }
}
