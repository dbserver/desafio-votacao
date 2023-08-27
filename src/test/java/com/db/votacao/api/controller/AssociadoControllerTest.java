package com.db.votacao.api.controller;

import com.db.votacao.api.model.Associado;
import com.db.votacao.api.service.AssociadoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AssociadoControllerTest {

    @Mock
    private AssociadoService associadoService;

    private AssociadoController associadoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        associadoController = new AssociadoController(associadoService);
    }

    @Test
    public void deveCriarAssociado() {
        // Arrange
        Associado associadoRequest = new Associado();
        when(associadoService.criarAssociado(associadoRequest)).thenReturn(associadoRequest);

        // Act
        ResponseEntity<Associado> responseEntity = associadoController.criarAssociado(associadoRequest);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(associadoRequest, responseEntity.getBody());

        verify(associadoService, times(1)).criarAssociado(associadoRequest);
        verifyNoMoreInteractions(associadoService);
    }

    @Test
    public void deveVerificarCpfAssociadoExiste() {
        // Arrange
        String cpf = "12345678909";
        when(associadoService.isCpfAssociadoExiste(cpf)).thenReturn(true);

        // Act
        ResponseEntity<String> responseEntity = associadoController.verificarCpf(cpf);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("ABLE_TO_VOTE", responseEntity.getBody());

        verify(associadoService, times(1)).isCpfAssociadoExiste(cpf);
        verifyNoMoreInteractions(associadoService);
    }

    @Test
    public void deveVerificarCpfAssociadoNaoExiste() {
        // Arrange
        String cpf = "12345678909";
        when(associadoService.isCpfAssociadoExiste(cpf)).thenReturn(false);

        // Act
        ResponseEntity<String> responseEntity = associadoController.verificarCpf(cpf);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("UNABLE_TO_VOTE", responseEntity.getBody());

        verify(associadoService, times(1)).isCpfAssociadoExiste(cpf);
        verifyNoMoreInteractions(associadoService);
    }
}