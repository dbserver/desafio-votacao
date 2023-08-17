package com.db.votacao.api.controller;

import com.db.votacao.api.enums.EnumOpcoesVoto;
import com.db.votacao.api.model.Voto;
import com.db.votacao.api.service.VotoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class VotoControllerTest {

    @Mock
    private VotoService votoService;

    private VotoController votoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        votoController = new VotoController(votoService);
    }

    @Test
    public void deveCriarVoto() {
        // Arrange
        Voto votoRequest = new Voto();
        when(votoService.criarVoto(votoRequest)).thenReturn(votoRequest);

        // Act
        ResponseEntity<Voto> responseEntity = votoController.criarVoto(votoRequest);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(votoRequest, responseEntity.getBody());

        verify(votoService, times(1)).criarVoto(votoRequest);
        verifyNoMoreInteractions(votoService);
    }

    @Test
    public void deveConsultarTotalizadorVotos() {
        // Arrange
        UUID pautaId = UUID.randomUUID();
        Map<EnumOpcoesVoto, Long> totalizador = new HashMap<>();
        totalizador.put(EnumOpcoesVoto.SIM, 10L);
        totalizador.put(EnumOpcoesVoto.NAO, 5L);

        when(votoService.calcularTotalizadorVotos(pautaId)).thenReturn(totalizador);

        // Act
        ResponseEntity<Map<EnumOpcoesVoto, Long>> responseEntity = votoController.consultarTotalizadorVotos(pautaId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(totalizador, responseEntity.getBody());

        verify(votoService, times(1)).calcularTotalizadorVotos(pautaId);
        verifyNoMoreInteractions(votoService);
    }

    @Test
    public void deveRetornarErroAoCriarVotoComFalha() {
        // Arrange
        Voto votoRequest = new Voto();
        when(votoService.criarVoto(votoRequest)).thenReturn(null);

        // Act
        ResponseEntity<Voto> responseEntity = votoController.criarVoto(votoRequest);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        verify(votoService, times(1)).criarVoto(votoRequest);
        verifyNoMoreInteractions(votoService);
    }
}