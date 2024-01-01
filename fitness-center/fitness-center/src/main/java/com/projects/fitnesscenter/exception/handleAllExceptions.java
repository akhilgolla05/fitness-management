package com.projects.fitnesscenter.exception;

import com.projects.fitnesscenter.errorMessage.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class handleAllExceptions {

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleTrainerNotFoundException(TrainerNotFoundException ex){

        ErrorMessage message = new ErrorMessage();
        message.setStatusCode(HttpStatus.NOT_FOUND.value());
        message.setMessage(ex.getMessage());
        message.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleMemberNotFoundException(MemberNotFoundException ex){

        ErrorMessage message = new ErrorMessage();
        message.setStatusCode(HttpStatus.NOT_FOUND.value());
        message.setMessage(ex.getMessage());
        message.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);

    }

}
