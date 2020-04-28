package com.naranja.transactions.controllers;

import com.naranja.transactions.dto.ErrorResponse;
import com.naranja.transactions.enums.ValidationViolationType;
import com.naranja.transactions.exceptions.CustomValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    public final ResponseEntity<ErrorResponse> handleUserNotFoundException(CustomValidationException ex,
                                                                           WebRequest request) {
        List<String> violations = ex.getViolations().stream().map(ValidationViolationType::getValue).collect(Collectors.toList());

        ErrorResponse error = new ErrorResponse(400, "rules have not passed", violations);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
