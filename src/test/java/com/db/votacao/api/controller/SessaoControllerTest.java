package com.db.votacao.api.controller;

import com.db.votacao.api.model.Sessao;
import com.db.votacao.api.service.SessaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class SessaoControllerTest {

    @Mock
    private SessaoService sessaoService;

    @InjectMocks
    private SessaoController sessaoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveIncluirSessao() {
        // Arrange
        Sessao sessaoRequest = new Sessao();
        when(sessaoService.criarSessao(sessaoRequest)).thenReturn(sessaoRequest);

        // Act
        ResponseEntity<Sessao> responseEntity = sessaoController.incluirSessao(sessaoRequest);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(sessaoRequest, responseEntity.getBody());
        verify(sessaoService, times(1)).criarSessao(sessaoRequest);
        verifyNoMoreInteractions(sessaoService);
    }

    @Test
    void deveRetornarErroAoIncluirSessaoComFalha() {
        // Arrange
        when(sessaoService.criarSessao(any())).thenReturn(null);

        // Act
        ResponseEntity<Sessao> responseEntity = sessaoController.incluirSessao(new Sessao());

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }

    @Test
    void deveConsultarSessaoPorId() {
        // Arrange
        UUID idSessao = UUID.randomUUID();
        Sessao sessao = new Sessao();
        when(sessaoService.consultarSessaoPorId(idSessao)).thenReturn(sessao);

        // Act
        ResponseEntity<Sessao> responseEntity = sessaoController.consultarSessaoPorId(idSessao);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(sessao, responseEntity.getBody());
    }

    @Test
    void deveConsultarSessaoPorIdNaoExistenteRetornandoNotFound() {
        // Arrange
        UUID idSessao = UUID.randomUUID();
        when(sessaoService.consultarSessaoPorId(idSessao)).thenReturn(null);

        // Act
        ResponseEntity<Sessao> responseEntity = sessaoController.consultarSessaoPorId(idSessao);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }

    @Test
    void deveConsultarSessoesPorFiltros() {
        // Arrange
        LocalDateTime dataCriacao = LocalDateTime.now();
        LocalDateTime inicioSessao = LocalDateTime.now().plusHours(1);
        LocalDateTime finalSessao = LocalDateTime.now().plusHours(2);
        List<Sessao> sessoes = new ArrayList<>();
        when(sessaoService.consultarSessoesPorFiltros(dataCriacao, inicioSessao, finalSessao)).thenReturn(sessoes);

        // Act
        ResponseEntity<List<Sessao>> responseEntity = sessaoController.consultarSessoesPorFiltros(dataCriacao, inicioSessao, finalSessao);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(sessoes, responseEntity.getBody());
    }
}