package br.com.dbserver.voting.handler;

import br.com.dbserver.voting.exceptions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.sql.SQLIntegrityConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestExceptionHandlerTest {

    @InjectMocks
    private RestExceptionHandler restExceptionHandler;

    @Test
    void handleExistingResourceException() {
        ExistingResourceException exception = new ExistingResourceException("Resource already exists");
        ExceptionDetails exceptionDetails = restExceptionHandler.handlerExistingResourceException(exception);

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

    @Test
    void handlerVotingException() {
        VotingException exception = new VotingException("Exception voting");
        ExceptionDetails exceptionDetails = restExceptionHandler.handlerVotingException(exception);

        assertEquals(HttpStatus.BAD_REQUEST.value(), exceptionDetails.getStatus());
        assertEquals(exception.getMessage(), exceptionDetails.getDetails());
    }

    @Test
    void handlerUnableVoteException() {
        UnableVoteException exception = new UnableVoteException("Exception voting");
        UnableVoteExceptionDetails unableVoteExceptionDetails = restExceptionHandler.handlerUnableVoteException(exception);

        assertEquals(exception.getMessage(), unableVoteExceptionDetails.getStatus());
    }

    @Test
    void handlerUnavailableServiceException() {
        UnavailableServiceException exception = new UnavailableServiceException("Exception voting");
        ExceptionDetails exceptionDetails = restExceptionHandler.handlerUnavailableServiceException(exception);

        assertEquals(HttpStatus.SERVICE_UNAVAILABLE.value(), exceptionDetails.getStatus());
        assertEquals(exception.getMessage(), exceptionDetails.getDetails());
    }

}