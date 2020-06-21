package com.meechai.backendtest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UsernameAlreadyExistException extends RuntimeException {
    public UsernameAlreadyExistException(String message){
        super(message);
    }
}
