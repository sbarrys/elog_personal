package com.kry.elog_personal.exception;

import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class CustomizeResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception e,/*어디서발생했는지*/WebRequest request){
        ExceptionResponse exceptionResponse=
                new ExceptionResponse(new Date(), e.getMessage(),request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);//서버에러
    }

    @ExceptionHandler(NotFound.class)
    public final ResponseEntity<Object> handleUserNotFoundExceptions(Exception e,/*어디서발생했는지*/WebRequest request){
        ExceptionResponse exceptionResponse=
                new ExceptionResponse(new Date(), e.getMessage(),request.getDescription(false));

        return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);//서버에러
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public final ResponseEntity<Object> handleUserInvalidDataAccessApiUsageExceptionExceptions(Exception e,/*어디서발생했는지*/WebRequest request){
        ExceptionResponse exceptionResponse=
                new ExceptionResponse(new Date(), e.toString(),request.getDescription(false));

        return new ResponseEntity<>( exceptionResponse.toString(),HttpStatus.NOT_FOUND);//서버에러
    }
}

