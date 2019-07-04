package com.eteosf.teammanager.exception.advice;

import com.eteosf.teammanager.exception.ErrorObject;
import com.eteosf.teammanager.exception.TeamNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class PlayerNotFoundExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TeamNotFoundException.class)
    public ResponseEntity<Object> handleTaskNotFoundException(TeamNotFoundException ex, WebRequest request) {
        ErrorObject errorObject = new ErrorObject(HttpStatus.NOT_FOUND, ex.getMessage(), request.toString());
        return handleExceptionInternal(ex, errorObject, new HttpHeaders(), errorObject.getStatus(), request);
    }

}
