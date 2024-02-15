package com.mphasis.EmployeePayroll.Controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mphasis.EmployeePayroll.Exception.*;
@ControllerAdvice
public class ItemExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ItemErrorResponse> handleException(ItemNotFoundException ine){
        ItemErrorResponse errorResponse = new ItemErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(ine.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<ItemErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ItemErrorResponse> handleException(Exception ex){
        ItemErrorResponse errorResponse = new ItemErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<ItemErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);
    }
}
