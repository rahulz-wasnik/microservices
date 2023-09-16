package com.microservices.order.service.exceptions;

import com.microservices.order.service.vo.ErrorVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorVO> handleAllUncaughtException(
            RuntimeException exception,
            WebRequest request
    ){
        ErrorVO errorVO = new ErrorVO(exception.getMessage());
        return new ResponseEntity<ErrorVO>(errorVO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
