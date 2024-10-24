package com.xmen.challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MutantNotFoundException extends RuntimeException{
    public MutantNotFoundException(String message){
        super(message);
    }
}
