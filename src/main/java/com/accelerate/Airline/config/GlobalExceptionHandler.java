package com.accelerate.Airline.config;


import com.accelerate.Airline.dto.ErrorMessageDto;
import com.accelerate.Airline.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException
            (MethodArgumentNotValidException e){
        Map<String, String> map = new HashMap<>();
        BindingResult result = e.getBindingResult();
        List<FieldError> list = result.getFieldErrors();
        list.forEach(fieldError -> {
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        });

        return ResponseEntity.badRequest()
                .body(map);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(
            ResourceNotFoundException e) {

        ErrorMessageDto errorMessageDto = new ErrorMessageDto(
                e.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorMessageDto);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(
            Exception e) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessageDto(e.getMessage()));
    }
}