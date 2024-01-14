package br.com.dbserver.voting.handler;

import br.com.dbserver.voting.exceptions.ExceptionDetails;
import br.com.dbserver.voting.exceptions.ExistingResourceException;
import br.com.dbserver.voting.exceptions.InvalidCpfException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.sql.SQLIntegrityConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RestExceptionHandlerTest {

    @InjectMocks
    private RestExceptionHandler restExceptionHandler;

    @Test
    void handleExistingResourceException() {
        ExistingResourceException exception = new ExistingResourceException("Resource already exists");
        ExceptionDetails exceptionDetails = restExceptionHandler.handlerNotFoundException(exception);

        assertEquals(HttpStatus.CONFLICT.value(), exceptionDetails.getStatus());
        assertEquals(exception.getMessage(), exceptionDetails.getDetails());
    }

    @Test
    void handleInvalidCpfException() {
        InvalidCpfException exception = new InvalidCpfException("Invalid CPF");
        ExceptionDetails exceptionDetails = restExceptionHandler.handlerInvalidCpfException(exception);

        assertEquals(HttpStatus.BAD_REQUEST.value(), exceptionDetails.getStatus());
        assertEquals(exception.getMessage(), exceptionDetails.getDetails());
    }

    @Test
    void handleSQLIntegrityConstraintViolationException() {
        SQLIntegrityConstraintViolationException exception = new SQLIntegrityConstraintViolationException("Integrity constraint violation");
        ExceptionDetails exceptionDetails = restExceptionHandler.handleSQLIntegrityConstraintViolationException(exception);

        assertEquals(HttpStatus.BAD_REQUEST.value(), exceptionDetails.getStatus());
        assertEquals(exception.getMessage(), exceptionDetails.getDetails());
    }

}