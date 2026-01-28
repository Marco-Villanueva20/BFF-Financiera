package com.retotecnico.mscliente.web.exception;

import java.util.HashMap;

import com.retotecnico.exception.ErrorResponse;
import com.retotecnico.exception.GlobalExceptionHandler;

import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice(basePackages = "com.retotecnico.msclient")
@Primary
@Slf4j
public class ClienteExceptionHandler extends GlobalExceptionHandler {


    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(CustomerNotFoundException exception){
        var error = new HashMap<String, String>();
        var fieldName = "customer";
        var erroMessage = exception.getMessage();
        error.put(fieldName, erroMessage);
        log.warn("Customer not found: {}", exception.toString());
        return ResponseEntity.badRequest().body(new ErrorResponse(error));
    }

    

}
