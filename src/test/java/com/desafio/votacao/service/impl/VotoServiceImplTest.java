package com.desafio.votacao.service.impl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.desafio.votacao.dto.request.VotoRequest;
import com.desafio.votacao.dto.response.MessageResponse;
import com.desafio.votacao.entity.Associado;
import com.desafio.votacao.entity.Pauta;
import com.desafio.votacao.enums.PautaStatusEnum;
import com.desafio.votacao.enums.VotoEnum;
import com.desafio.votacao.exception.BusinessException;
import com.desafio.votacao.repository.VotoRepository;
import com.desafio.votacao.service.AssociadoService;
import com.desafio.votacao.service.PautaService;
import com.desafio.votacao.service.VotacaoService;

public class VotoServiceImplTest {

    @InjectMocks
    private VotoServiceImpl votoService;

    @Mock
    private VotoRepository votoRepository;

    @Mock
    private PautaService pautaService;

    @Mock
    private AssociadoService associadoService;

    @Mock
    private VotacaoService votacaoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSalvar_ValidVoto() {
        VotoRequest request = new VotoRequest();
        request.setValor(VotoEnum.NAO);
        Pauta pauta = new Pauta();
        pauta.setStatus(PautaStatusEnum.AGUARDANDO_RESULTADO);
        Associado associado = new Associado();
        when(pautaService.buscarPorId(request.getPautaId())).thenReturn(Optional.of(pauta));
        when(votacaoService.validarVotacaoAtiva(pauta)).thenReturn(true);
        when(associadoService.buscarPorCPF(request.getCpf())).thenReturn(associado);
        ResponseEntity<MessageResponse> response = votoService.salvar(request);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("Voto Realizado com Sucesso!", response.getBody().getMessage());
    }

    @Test
    public void testSalvar_VotacaoFinalizada() {
        VotoRequest request = new VotoRequest();
        Pauta pauta = new Pauta();
        when(pautaService.buscarPorId(request.getPautaId())).thenReturn(Optional.of(pauta));
        when(votacaoService.validarVotacaoAtiva(pauta)).thenReturn(false);

        assertThrows(BusinessException.class, () -> votoService.salvar(request));
    }

    @Test
    public void testSalvar_PautaNaoExiste() {
        VotoRequest request = new VotoRequest();
        when(pautaService.buscarPorId(request.getPautaId())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> votoService.salvar(request));
    }

    @Test
    public void testSalvar_PautaStatusInvalido() {
        VotoRequest request = new VotoRequest();
        Pauta pauta = new Pauta();
        when(pautaService.buscarPorId(request.getPautaId())).thenReturn(Optional.of(pauta));
        when(votacaoService.validarVotacaoAtiva(pauta)).thenReturn(true);
        assertThrows(NullPointerException.class, () -> votoService.salvar(request));
    } 

    @Test
    public void testSalvar_InactiveVotacaoService() {
        VotoRequest validVotoRequest = new VotoRequest();
        Pauta existingPauta = new Pauta();
        when(pautaService.buscarPorId(validVotoRequest.getPautaId())).thenReturn(Optional.of(existingPauta));
        when(votacaoService.validarVotacaoAtiva(existingPauta)).thenReturn(false);
        assertThrows(BusinessException.class, () -> votoService.salvar(validVotoRequest));
    }

    @Test
    public void testIniciarApuracaoDeVotos() {
        List<Pauta> pautasAguardandoApuracao = new ArrayList<>();
        Pauta existingPauta = new Pauta();
        when(pautaService.listarPautaAguardandoApuracao()).thenReturn(pautasAguardandoApuracao);
        when(votoRepository.countByVotoAndPauta(true, existingPauta)).thenReturn(1L);
        when(votoRepository.countByVotoAndPauta(false, existingPauta)).thenReturn(1L);
        votoService.iniciarApuracaoDeVotos();
        Mockito.verify(pautaService, Mockito.times(pautasAguardandoApuracao.size())).salvarVotos(1L, 1L, existingPauta);
    }

    @Test
    public void testContarVotos() {
        Pauta pauta = new Pauta();
        boolean voto = true;
        when(votoRepository.countByVotoAndPauta(voto, pauta)).thenReturn(3L);
        Long result = votoService.contarVotos(voto, pauta);
        assertEquals(3L, result);
    }
}
