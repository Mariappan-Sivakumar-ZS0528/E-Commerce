package com.app.shopping.ecommerce.exception;
import org.springframework.http.HttpStatus;

public class ECommerceApiException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public ECommerceApiException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

