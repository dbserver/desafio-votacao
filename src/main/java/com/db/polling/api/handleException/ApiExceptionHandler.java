package com.db.polling.api.handleException;

import com.db.polling.domain.exception.NotFoundException;
import com.db.polling.domain.exception.UnprocessableException;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

  @Autowired
  private MessageSource messageSource;

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ApiError> handleException(NotFoundException ex, Locale locale) {

    return buildApiError(ex.getCode(), ex.getMessage(), locale, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(UnprocessableException.class)
  public ResponseEntity<ApiError> handleException(UnprocessableException ex, Locale locale) {

    return buildApiError(ex.getCode(), ex.getMessage(), locale, HttpStatus.UNPROCESSABLE_ENTITY);
  }

  private ResponseEntity<ApiError> buildApiError(String code, String message, Locale locale,
      HttpStatus status) {
    String messageSourceMessage = messageSource.getMessage(code, null, message, locale);
    ApiError error = new ApiError(code, messageSourceMessage);
    return ResponseEntity.status(status).body(error);
  }

}
