package com.thoughtworks.springbootemployee.config;

import com.thoughtworks.springbootemployee.Exception.IllegalOperationException;
import com.thoughtworks.springbootemployee.Exception.NoSuchDataException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author XIEDR2
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String NO_SUCH_DATA = "No such data!";
    public static final String ILLEGAL_OPERATION = "Illegal operation!";

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchDataException.class)
    String handleNoSuchDataException() {
        return NO_SUCH_DATA;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalOperationException.class)
    String handleIllegalOperationException() {
        return ILLEGAL_OPERATION;
    }
}