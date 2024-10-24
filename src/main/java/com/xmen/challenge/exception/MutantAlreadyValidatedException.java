package com.xmen.challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class MutantAlreadyValidatedException extends RuntimeException{
    public MutantAlreadyValidatedException(String message){
        super(message);
    }
}
