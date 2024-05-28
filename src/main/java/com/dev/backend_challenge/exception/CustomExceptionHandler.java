package com.dev.backend_challenge.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleValidationException(ValidationException ex) {
        String errorMessage = "Error: " + ex.getMessage();
        return ResponseEntity.status(ex.getError().getStatusCode()).body(errorMessage);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex){
        String errorMessage = "Error: " + ex.getMessage();
        return ResponseEntity.status(400).body(errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        String errorMessage = "Error: Missing or wrong parameters";
        return ResponseEntity.status(400).body(errorMessage);
    }

    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<String> handleJsonMappingException(JsonMappingException ex){
        String  errorMessage = "Error: " + ex.getMessage();
        return ResponseEntity.status(400).body(errorMessage);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity.internalServerError().body("Error to handle request!");
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleException(BadCredentialsException ex){
        String  errorMessage = "Error: " + ex.getMessage();
        return ResponseEntity.status(401).body(errorMessage);
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<String> handleException(InternalAuthenticationServiceException ex){
        String errorMessage = "Error: Missing cpf or password in body.";
        return ResponseEntity.status(400).body(errorMessage);
    }

}