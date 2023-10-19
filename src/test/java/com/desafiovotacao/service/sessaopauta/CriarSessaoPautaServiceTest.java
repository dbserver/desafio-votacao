package com.desafiovotacao.service.sessaopauta;

import com.desafiovotacao.domain.Pauta;
import com.desafiovotacao.domain.SessaoPauta;
import com.desafiovotacao.dto.SessaoPautaDTO;
import com.desafiovotacao.repository.SessaoPautaRepository;
import com.desafiovotacao.service.pauta.BuscarPautaPorIdService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class CriarSessaoPautaServiceTest {

    @Mock
    private SessaoPautaRepository repository;

    @Mock
    private BuscarPautaPorIdService buscarPautaPorIdService;

    @InjectMocks CriarSessaoPautaService service;

    @Test
    void execute_shouldCallRepository() throws Exception {
        SessaoPautaDTO sessaoPautaDTO = new SessaoPautaDTO();
        sessaoPautaDTO.setDuracaoMinutos(1L);

        SessaoPauta sessaoPauta = new SessaoPauta();
        sessaoPauta.setPauta(new Pauta());

        when(buscarPautaPorIdService.buscar(any())).thenReturn(new Pauta());
        when(repository.save(any())).thenReturn(sessaoPauta);

        service.criar(sessaoPautaDTO);

        verify(buscarPautaPorIdService, times(1)).buscar(any());
        verify(repository, times(1)).save(any());
    }
}
