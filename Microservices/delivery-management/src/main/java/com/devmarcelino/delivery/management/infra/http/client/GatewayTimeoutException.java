package com.devmarcelino.delivery.management.infra.http.client;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.GATEWAY_TIMEOUT)
public class GatewayTimeoutException extends RuntimeException{

    public GatewayTimeoutException() {

    }

    public GatewayTimeoutException(Throwable cause) {
        super(cause);        
    }

    
    
}
