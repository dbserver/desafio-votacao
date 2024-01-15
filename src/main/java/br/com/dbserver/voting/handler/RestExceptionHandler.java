package br.com.dbserver.voting.handler;

import br.com.dbserver.voting.exceptions.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ExistingResourceException.class)
    @ResponseStatus(CONFLICT)
    public ExceptionDetails handlerExistingResourceException(ExistingResourceException exception) {
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
        ResponseEntity<ValidationExceptionDetails> responseEntity = new ResponseEntity<>(new ValidationExceptionDetails(
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

    @ExceptionHandler(InvalidCpfException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionDetails handlerInvalidCpfException(InvalidCpfException exception) {
        return new ExceptionDetails(
                BAD_REQUEST.name(),
                BAD_REQUEST.value(),
                exception.getMessage(),
                exception.getClass().getName(),
                LocalDateTime.now()
        );
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionDetails handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException exception) {
        return new ExceptionDetails(
                BAD_REQUEST.name(),
                BAD_REQUEST.value(),
                exception.getMessage(),
                exception.getClass().getName(),
                LocalDateTime.now()
        );
    }

    @ExceptionHandler(VotingException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionDetails handlerVotingException(VotingException exception) {
        return new ExceptionDetails(
                BAD_REQUEST.name(),
                BAD_REQUEST.value(),
                exception.getMessage(),
                exception.getClass().getName(),
                LocalDateTime.now()
        );
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionDetails handlerExistingResourceException(NotFoundException exception) {
        return new ExceptionDetails(
                NOT_FOUND.name(),
                NOT_FOUND.value(),
                exception.getMessage(),
                exception.getClass().getName(),
                LocalDateTime.now()
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionDetails handlerIllegalArgumentException(IllegalArgumentException exception) {
        return new ExceptionDetails(
                BAD_REQUEST.name(),
                BAD_REQUEST.value(),
                exception.getMessage(),
                exception.getClass().getName(),
                LocalDateTime.now()
        );
    }

    @ExceptionHandler(UnableVoteException.class)
    @ResponseStatus(NOT_FOUND)
    public UnableVoteExceptionDetails handlerUnableVoteException(UnableVoteException exception) {
        return new UnableVoteExceptionDetails(exception.getMessage());
    }

    @ExceptionHandler(UnavailableServiceException.class)
    @ResponseStatus(SERVICE_UNAVAILABLE)
    public ExceptionDetails handlerUnavailableServiceException(UnavailableServiceException exception) {
        return new ExceptionDetails(
                SERVICE_UNAVAILABLE.name(),
                SERVICE_UNAVAILABLE.value(),
                exception.getMessage(),
                exception.getClass().getName(),
                LocalDateTime.now()
        );
    }



}


