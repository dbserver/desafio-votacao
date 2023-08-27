package com.db.votacao.api.controller;

import com.db.votacao.api.model.Pauta;
import com.db.votacao.api.service.PautaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PautaControllerTest {

    @Mock
    private PautaService pautaService;

    private PautaController pautaController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        pautaController = new PautaController(pautaService);
    }

    @Test
    public void deveCriarPauta() {
        // Arrange
        Pauta pautaRequest = new Pauta();
        when(pautaService.criarPauta(pautaRequest)).thenReturn(pautaRequest);

        // Act
        ResponseEntity<Pauta> responseEntity = pautaController.criarPauta(pautaRequest);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(pautaRequest, responseEntity.getBody());

        verify(pautaService, times(1)).criarPauta(pautaRequest);
        verifyNoMoreInteractions(pautaService);
    }

    @Test
    public void deveConsultarPautaPorNome() {
        // Arrange
        String nomePauta = "Título da Pauta";
        Pauta pauta = new Pauta();

        when(pautaService.consultarPautaPorNome(nomePauta)).thenReturn(pauta);

        // Act
        ResponseEntity<Pauta> responseEntity = pautaController.consultarPautaPorNome(nomePauta);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(pauta, responseEntity.getBody());

        verify(pautaService, times(1)).consultarPautaPorNome(nomePauta);
        verifyNoMoreInteractions(pautaService);
    }

    @Test
    public void deveConsultarPautaPorId() {
        // Arrange
        UUID idPauta = UUID.randomUUID();
        Pauta pauta = new Pauta();

        when(pautaService.consultarPautaPorId(idPauta)).thenReturn(pauta);

        // Act
        ResponseEntity<Pauta> responseEntity = pautaController.consultarPautaPorId(idPauta);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(pauta, responseEntity.getBody());

        verify(pautaService, times(1)).consultarPautaPorId(idPauta);
        verifyNoMoreInteractions(pautaService);
    }

    @Test
    public void deveConsultarPautaPorNomeRetornandoNaoEncontrada() {
        // Arrange
        String nomePauta = "Pauta Inexistente";
        when(pautaService.consultarPautaPorNome(nomePauta)).thenReturn(null);

        // Act
        ResponseEntity<Pauta> responseEntity = pautaController.consultarPautaPorNome(nomePauta);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(pautaService, times(1)).consultarPautaPorNome(nomePauta);
        verifyNoMoreInteractions(pautaService);
    }

    @Test
    public void deveConsultarPautaPorIdRetornandoNaoEncontrada() {
        // Arrange
        UUID idPauta = UUID.randomUUID();
        when(pautaService.consultarPautaPorId(idPauta)).thenReturn(null);

        // Act
        ResponseEntity<Pauta> responseEntity = pautaController.consultarPautaPorId(idPauta);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(pautaService, times(1)).consultarPautaPorId(idPauta);
        verifyNoMoreInteractions(pautaService);
    }
}