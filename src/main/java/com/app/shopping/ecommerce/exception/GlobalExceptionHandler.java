package com.app.shopping.ecommerce.exception;

import com.app.shopping.ecommerce.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.nio.file.AccessDeniedException;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDetails> handleAccessDeniedException(AccessDeniedException ex,WebRequest webRequest){
        return new ResponseEntity<>(new ErrorDetails(new Date(), ex.getMessage(), webRequest.getDescription(false)), HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(ECommerceApiException.class)
    public ResponseEntity<ErrorDetails>  handleBlogApiException(ECommerceApiException exception,
                                                                WebRequest request){
        ErrorDetails errorDetails=new ErrorDetails(new Date(),exception.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleException(Exception exception, WebRequest webRequest){
        ErrorDetails errorDetails=new ErrorDetails(new Date(),exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
