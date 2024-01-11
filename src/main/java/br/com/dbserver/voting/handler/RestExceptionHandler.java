package br.com.dbserver.voting.handler;

import br.com.dbserver.voting.exceptions.ExceptionDetails;
import br.com.dbserver.voting.exceptions.ExistingResourceException;
import br.com.dbserver.voting.exceptions.ValidationExceptionDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ExistingResourceException.class)
    @ResponseStatus(CONFLICT)
    public ExceptionDetails handlerNotFoundException(ExistingResourceException exception) {
        return new ExceptionDetails(
                CONFLICT.name(),
                CONFLICT.value(),
                exception.getMessage(),
                exception.getClass().getName(),
                LocalDateTime.now()
        );
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        ResponseEntity responseEntity = new ResponseEntity<>(new ValidationExceptionDetails(
                "Bad Request Exception, Campos Inválidos",
                BAD_REQUEST.value(),
                "Verifique o erro do(s) campo(s)",
                ex.getClass().getName(),
                LocalDateTime.now(),
                fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", ")),
                fieldErrors.stream().map(FieldError::getDefaultMessage)
                        .collect(Collectors.joining(", "))
        ), BAD_REQUEST);


        return this.handleExceptionInternal(ex, responseEntity, headers, status, request);
    }

}
