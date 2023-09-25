package com.desafio.votacao.service.impl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.desafio.votacao.dto.request.VotacaoRequest;
import com.desafio.votacao.dto.response.MessageResponse;
import com.desafio.votacao.entity.Pauta;
import com.desafio.votacao.entity.Votacao;
import com.desafio.votacao.enums.PautaStatusEnum;
import com.desafio.votacao.exception.NotFoundException;
import com.desafio.votacao.repository.VotacaoRepository;
import com.desafio.votacao.service.PautaService;

public class VotacaoServiceImplTest {

    @InjectMocks
    private VotacaoServiceImpl votacaoService;

    @Mock
    private VotacaoRepository votacaoRepository;

    @Mock
    private PautaService pautaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFecharVotacao_NoExpiredVotacao() {
        when(votacaoRepository.findByAtivoExpirada()).thenReturn(new ArrayList<>());
        votacaoService.fecharVotacao();
    }

    @Test
    public void testFecharVotacao_WithExpiredVotacao() {
        List<Votacao> expiredVotacaoList = new ArrayList<>();
        when(votacaoRepository.findByAtivoExpirada()).thenReturn(expiredVotacaoList);
        votacaoService.fecharVotacao();
    }

    @Test
    public void testIniciarVotacao_ValidRequest() {
        VotacaoRequest validVotacaoRequest = new VotacaoRequest();
        Pauta existingPauta = new Pauta();
        existingPauta.setStatus(PautaStatusEnum.AGUARDANDO_RESULTADO);
        when(pautaService.buscarPorId(existingPauta.getId())).thenReturn(Optional.of(existingPauta));
        when(votacaoRepository.save(Mockito.any(Votacao.class))).thenReturn(new Votacao());
        ResponseEntity<MessageResponse> response = votacaoService.iniciarVotacao(validVotacaoRequest);
        assertEquals("200 OK", response.getStatusCode().toString());
        assertNotNull(response.getBody());
        assertEquals("Votação incia com sucesso.", response.getBody().getMessage());
    }

    @Test
    public void testIniciarVotacao_InvalidPauta() {
        VotacaoRequest validVotacaoRequest = new VotacaoRequest();
        when(pautaService.buscarPorId(validVotacaoRequest.getPautaId())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> votacaoService.iniciarVotacao(validVotacaoRequest));
    }

    @Test
    public void testIniciarVotacao_InvalidPautaStatus() {
        VotacaoRequest validVotacaoRequest = new VotacaoRequest();
        Pauta invalidPauta = new Pauta();
        when(pautaService.buscarPorId(validVotacaoRequest.getPautaId())).thenReturn(Optional.of(invalidPauta));
        assertThrows(NullPointerException.class, () -> votacaoService.iniciarVotacao(validVotacaoRequest));
    }

    @Test
    public void testValidarVotacaoAtiva_ActiveVotacao() {
        Votacao activeVotacao = new Votacao();
        activeVotacao.setAtivo(true);
        activeVotacao.setDthFim(LocalDateTime.now().plusMinutes(30));
        when(votacaoRepository.findFirstByPauta(Mockito.any(Pauta.class))).thenReturn(Optional.of(activeVotacao));
        boolean result = votacaoService.validarVotacaoAtiva(new Pauta());
        assertTrue(result);
    }

    @Test
    public void testValidarVotacaoAtiva_InactiveVotacao() {
        Votacao inactiveVotacao = new Votacao();
        inactiveVotacao.setAtivo(false);
        inactiveVotacao.setDthFim(LocalDateTime.now().minusMinutes(30));
        when(votacaoRepository.findFirstByPauta(Mockito.any(Pauta.class))).thenReturn(Optional.of(inactiveVotacao));
        boolean result = votacaoService.validarVotacaoAtiva(new Pauta());
        assertFalse(result);
    }
}
