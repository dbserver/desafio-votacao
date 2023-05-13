package com.db.polling.api.handleException;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {

  private final static String DEFAULT_MESSAGE = "One or more required fields have not been filled out. Please fill out all required fields and try again.";

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {

    Map<String, String> errorMap = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

    HttpStatus descriptionStatus = HttpStatus.resolve(status.value());
    ServletWebRequest servletWebRequest = (ServletWebRequest) request;
    String uri = servletWebRequest.getRequest().getRequestURI();

    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setTimestamp(LocalDateTime.now());
    errorResponse.setMessage(DEFAULT_MESSAGE);
    errorResponse.setStatus(status.value());
    errorResponse.setError(descriptionStatus.name());
    errorResponse.setErrors(errorMap);
    errorResponse.setPath(uri);

    return ResponseEntity.badRequest().body(errorResponse);
  }
}