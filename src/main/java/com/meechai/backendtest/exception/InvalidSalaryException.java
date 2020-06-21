package com.meechai.backendtest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidSalaryException extends RuntimeException{

    public InvalidSalaryException(String message){
        super(message);
    }
}
