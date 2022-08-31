package com.graduation.votingSystem;

import com.graduation.votingSystem.util.exception.ErrorInfo;
import com.graduation.votingSystem.util.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice(annotations = RestController.class)
public class AppExceptionHandler {

    private static Logger log = LoggerFactory.getLogger(AppExceptionHandler.class);

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorInfo errorInfo(HttpServletRequest request, BindingResult result) {
        result.getAllErrors().forEach(e -> log.warn(e.getDefaultMessage()));
        StringBuilder message = new StringBuilder();
        for (ObjectError error : result.getAllErrors()) {
            message.append(error.getDefaultMessage() + " ");
        }
        return new ErrorInfo(request.getRequestURL().toString(), message.toString());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorInfo errorInfo(HttpServletRequest request, NotFoundException exception) {
        log.warn(exception.getMessage());
        return new ErrorInfo(request.getRequestURL().toString(), exception.getMessage());
    }
}
