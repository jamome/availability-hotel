package com.ruso.apihotel.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.ruso.apihotel.config.ApiLogger;
import com.ruso.apihotel.util.ApiBookingValidator;

@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler{

  @ExceptionHandler(ApiHotelException.class)
  public ResponseEntity<Object> dataBaseException(ApiHotelException ex, WebRequest request) {
    ApiLogger.logError(ex);
    
    APIErrorDetail error = new APIErrorDetail();
    error.setTimestamp(LocalDateTime.now());
    error.setErrors(ex.getErrors());
    error.setPath(((ServletWebRequest) request).getRequest().getRequestURI().toString());
    
    return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<Object> genericException(RuntimeException ex, WebRequest request) {
    ApiLogger.logError(ex);
    
    APIErrorDetail error = new APIErrorDetail();
    error.setTimestamp(LocalDateTime.now());
    Map<String, String> errors = new HashMap<>();
    errors.put("internalError", ex.getLocalizedMessage());
    error.setErrors(errors);
    error.setPath(((ServletWebRequest) request).getRequest().getRequestURI().toString());
    
    return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    ApiLogger.logError(ex);
    
    APIErrorDetail apiError = new APIErrorDetail();
    apiError.setTimestamp(LocalDateTime.now());
    apiError.setPath(((ServletWebRequest) request).getRequest().getRequestURI().toString());
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    apiError.setErrors(errors);

    return new ResponseEntity<Object>(apiError, HttpStatus.BAD_REQUEST);
  }

}
