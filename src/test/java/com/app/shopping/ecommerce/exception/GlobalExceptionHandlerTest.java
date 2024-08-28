package com.app.shopping.ecommerce.exception;

import com.app.shopping.ecommerce.payload.ErrorDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @Mock
    private WebRequest webRequest;

    @Mock
    private MethodArgumentNotValidException methodArgumentNotValidException;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    public void handleResourceNotFoundExceptionTest() {
        ResourceNotFoundException exception = new ResourceNotFoundException("Resource", "name","id");
        when(webRequest.getDescription(false)).thenReturn("Description");

        ResponseEntity<ErrorDetails> response = globalExceptionHandler.handleResourceNotFoundException(exception, webRequest);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody().getMessage()).isEqualTo("Resource not found with name : 'id'");
        assertThat(response.getBody().getDetails()).isEqualTo("Description");
    }

    @Test
    public void handleECommerceApiExceptionTest() {
        ECommerceApiException exception = new ECommerceApiException(HttpStatus.BAD_REQUEST, "API exception");
        when(webRequest.getDescription(false)).thenReturn("Description");

        ResponseEntity<ErrorDetails> response = globalExceptionHandler.handleBlogApiException(exception, webRequest);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getMessage()).isEqualTo("API exception");
        assertThat(response.getBody().getDetails()).isEqualTo("Description");
    }

    @Test
    public void handleMethodArgumentNotValidTest() {
        when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);
        FieldError fieldError = new FieldError("objectName", "fieldName", "defaultMessage");
        when(bindingResult.getAllErrors()).thenReturn(List.of(fieldError));

        ResponseEntity<Object> response = globalExceptionHandler.handleMethodArgumentNotValid(methodArgumentNotValidException, null, HttpStatus.BAD_REQUEST, webRequest);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        Map<String, String> errors = (Map<String, String>) response.getBody();
        assertThat(errors.get("fieldName")).isEqualTo("defaultMessage");
    }

    @Test
    public void handleAccessDeniedExceptionTest() {
        AccessDeniedException exception = new AccessDeniedException("Access denied");
        when(webRequest.getDescription(false)).thenReturn("Description");

        ResponseEntity<ErrorDetails> response = globalExceptionHandler.handleAccessDeniedException(exception, webRequest);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
        assertThat(response.getBody().getMessage()).isEqualTo("Access denied");
        assertThat(response.getBody().getDetails()).isEqualTo("Description");
    }

    @Test
    public void handleGlobalExceptionTest() {
        Exception exception = new Exception("Internal server error");
        when(webRequest.getDescription(false)).thenReturn("Description");

        ResponseEntity<ErrorDetails> response = globalExceptionHandler.handleGlobalException(exception, webRequest);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody().getMessage()).isEqualTo("Internal server error");
        assertThat(response.getBody().getDetails()).isEqualTo("Description");
    }
}
