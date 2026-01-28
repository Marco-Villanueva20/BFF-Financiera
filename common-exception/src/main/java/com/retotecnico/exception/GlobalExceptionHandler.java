package com.retotecnico.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException exception){
        var errors = new HashMap<String, String>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            var fieldName = error.getField();
            var errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        log.warn("Validation errors: {}", exception.toString());
        return ResponseEntity.badRequest().body(new ErrorResponse(errors));
    }


    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ErrorResponse> handleException(Exception exception){
        var error = new HashMap<String, String>();
        var fieldName= "message";
        var erroMessage = "Se a producido un error. Por favor contacte al administrador o intente más tarde";
        error.put(fieldName, erroMessage);

        log.error("Error: {}", exception.toString());
        return ResponseEntity.internalServerError().body(new ErrorResponse(error));
    }
}
