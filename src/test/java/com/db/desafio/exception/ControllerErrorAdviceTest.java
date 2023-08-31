package com.db.desafio.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ControllerErrorAdviceTest {

    @InjectMocks
    ControllerErrorAdvice controllerErrorAdvice;

    @Test
    @DisplayName("Deve retornar messagem e HttpStatus corretamente para AssociadoException")
    void deveRetornarExceptionParaAssociadoExistente() {
        AssociadoException exception = new AssociadoException("Associado inexistente");

        ResponseEntity<ErrorResponse> errorResponseResponseEntity = controllerErrorAdvice.handleAssociadoException(exception);

        assertEquals("Associado inexistente", errorResponseResponseEntity.getBody().getMensagem());
        assertEquals(HttpStatus.NOT_FOUND, errorResponseResponseEntity.getStatusCode());
    }
    @Test
    @DisplayName("Deve retornar messagem e HttpStatus corretamente para PautaException")
    void deveRetornarExceptionParaPautaInexistente() {
        PautaException exception = new PautaException("Pauta inexistente");

        ResponseEntity<ErrorResponse> errorResponseResponseEntity = controllerErrorAdvice.handlePautaException(exception);

        assertEquals("Pauta inexistente", errorResponseResponseEntity.getBody().getMensagem());
        assertEquals(HttpStatus.NOT_FOUND, errorResponseResponseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Deve retornar messagem e HttpStatus corretamente para SessaoVotacaoException")
    void deveRetornarExceptionParaSessaoInexistente() {
        SessaoVotacaoException exception = new SessaoVotacaoException("Sessão de Votação inexistente");

        ResponseEntity<ErrorResponse> errorResponseResponseEntity = controllerErrorAdvice.handleSessaoVotacaoException(exception);

        assertEquals("Sessão de Votação inexistente", errorResponseResponseEntity.getBody().getMensagem());
        assertEquals(HttpStatus.NOT_FOUND, errorResponseResponseEntity.getStatusCode());
    }

}
