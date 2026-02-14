package com.archit.dev.gamemanager.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GameNotFoundException.class)
    public ResponseEntity<ApiError> handleGameNotFoundException(Exception e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiError apiError = new ApiError(
                status.value(),
                status.name(),
                e.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(apiError, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationException(MethodArgumentNotValidException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiError apiError = new ApiError(
                status.value(),
                status.name(),
                e.getMessage(),
                request.getRequestURI()
        );
        Map<String, String> validationErrors = new HashMap<>();
        for(FieldError fieldError : e.getBindingResult().getFieldErrors()){
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        apiError.setValidationErrors(validationErrors);
        return new ResponseEntity<>(apiError, status);
    }

    @ExceptionHandler(GameAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleGameAlreadyExistsException(Exception e, HttpServletRequest request){
        HttpStatus status = HttpStatus.CONFLICT;
        ApiError apiError = new ApiError(
                status.value(),
                status.name(),
                e.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(apiError, status);
    }
}
