package com.desafiovotacao.service.votacao;

import com.desafiovotacao.domain.Associado;
import com.desafiovotacao.domain.Pauta;
import com.desafiovotacao.domain.SessaoPauta;
import com.desafiovotacao.domain.VotoAssociado;
import com.desafiovotacao.dto.VotoAssociadoDTO;
import com.desafiovotacao.repository.VotoRepository;
import com.desafiovotacao.service.associado.BuscarAssociadoPorIdService;
import com.desafiovotacao.service.sessaopauta.BuscarSessaoPorIdService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class CriarVotoServiceTest{

    @Mock
    private VotoRepository votoRepository;

    @Mock
    private BuscarVotacaoService buscarVotacaoService;

    @Mock
    private BuscarAssociadoPorIdService buscarAssociadoPorIdService;

    @Mock
    private BuscarSessaoPorIdService buscarSessaoPorIdService;


    @InjectMocks
    private CriarVotoService service;

    @Test
    void execute_shouldCallRepository() throws Exception {
        SessaoPauta sessaoPauta = new SessaoPauta();
        sessaoPauta.setPauta(new Pauta());
        sessaoPauta.setDataFim(LocalDateTime.MAX);

        VotoAssociado votoAssociado = new VotoAssociado();
        votoAssociado.setAssociado(new Associado());
        votoAssociado.setSessaoPauta(sessaoPauta);


        when(buscarVotacaoService.buscarPorAssociadoAndSessao(any(), any())).thenReturn(null);
        when(buscarAssociadoPorIdService.buscar(any())).thenReturn(new Associado());
        when(buscarSessaoPorIdService.buscar(any())).thenReturn(sessaoPauta);
        when(votoRepository.save(any())).thenReturn(votoAssociado);

        service.criar(new VotoAssociadoDTO());

        verify(buscarVotacaoService, times(1)).buscarPorAssociadoAndSessao(any(), any());
        verify(buscarAssociadoPorIdService, times(1)).buscar(any());
        verify(buscarSessaoPorIdService, times(1)).buscar(any());
    }
}
