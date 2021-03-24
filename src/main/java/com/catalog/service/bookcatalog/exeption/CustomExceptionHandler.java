package com.catalog.service.bookcatalog.exeption;

import java.util.NoSuchElementException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(NoSuchElementException.class)
  public Object handleNoSuchElementException(RuntimeException ex, WebRequest request) {
    return handleExceptionInternal(ex, ex.getMessage(),
        new HttpHeaders(), HttpStatus.CONFLICT, request);
  }
}
