package com.example.tinkoffapiservice.exception;

import com.example.tinkoffapiservice.DTO.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler
    public ResponseError incorrectData(UncorrectData ex){
        return new ResponseError(new ErrorDTO(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({StockNotFoundException.class})
    public ResponseEntity<ErrorDTO> handleNotFound (Exception error){
        return new ResponseEntity<ErrorDTO>(new ErrorDTO(error.getLocalizedMessage()), HttpStatus.NOT_FOUND);
    }


}

