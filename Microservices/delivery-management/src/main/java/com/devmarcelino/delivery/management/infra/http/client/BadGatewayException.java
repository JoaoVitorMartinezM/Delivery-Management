package com.devmarcelino.delivery.management.infra.http.client;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_GATEWAY)
public class BadGatewayException extends RuntimeException{

     public BadGatewayException() {

    }

    public BadGatewayException(Throwable cause) {
        super(cause);        
    }
    
}
