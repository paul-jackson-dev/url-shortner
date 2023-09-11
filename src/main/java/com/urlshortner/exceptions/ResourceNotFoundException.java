package com.urlshortner.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND) // when resource not found, Not Found exception will be given to the API
public class ResourceNotFoundException extends RuntimeException{

//    @Serial
//    private static final long serialVersionUID = -7034897190745766939L; not sure if we need to serialize the exception?

    public ResourceNotFoundException(String message){
        super(message);
    }
}
