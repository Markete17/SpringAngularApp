package com.marcos.exams.validations;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserFoundException extends RuntimeException {

	private static final long serialVersionUID = -479913571714866881L;
	
    public UserFoundException() {
        super("The user name already exists.");
    }

}
