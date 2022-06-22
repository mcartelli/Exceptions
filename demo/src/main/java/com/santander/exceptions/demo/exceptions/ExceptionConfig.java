package com.santander.exceptions.demo.exceptions;

import com.santander.exceptions.demo.dto.StatusDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class ExceptionConfig {
    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public StatusDto handlerException(UserAlreadyExistException userAlreadyExistException){
        StatusDto statusDto = new StatusDto();
        statusDto.setCode(400);
        statusDto.setMessage(userAlreadyExistException.getMessage());
        return statusDto;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<StatusDto> handleValidationExceptions(MethodArgumentNotValidException e) {
        StatusDto error = new StatusDto(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage(),400);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<StatusDto> handleValidationExceptions(HttpMessageNotReadableException e) {
        StatusDto error = new StatusDto(e.getMessage(), 400);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoConectionException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public StatusDto handleNoConectionExceptions(NoConectionException e){
        return new StatusDto(e.getMessage(),404);
    }

}
