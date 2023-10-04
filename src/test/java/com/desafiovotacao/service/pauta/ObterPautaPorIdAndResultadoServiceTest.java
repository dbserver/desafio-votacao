
package com.desafiovotacao.service.pauta;

import com.desafiovotacao.domain.Pauta;
import com.desafiovotacao.repository.PautaRepository;
import com.desafiovotacao.service.votacao.BuscarVotacaoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class ObterPautaPorIdAndResultadoServiceTest {

    @Mock
    private PautaRepository repository;

    @Mock
    private BuscarVotacaoService buscarVotacaoService;

    @InjectMocks
    private ObterPautaPorIdAndResultadoService service;

    @Test
    void execute_shouldCallRepository() throws Exception {
        String id = "1";
        Pauta pauta = new Pauta();
        pauta.setResultadoVerificado(false);
        pauta.setSessoesPautas(new ArrayList<>());

        when(repository.findById(any())).thenReturn(Optional.of(pauta));
        when(buscarVotacaoService.buscarTodosPorPauta(any())).thenReturn(new ArrayList<>());

        service.buscar(id);

        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(pauta);
        verify(buscarVotacaoService, times(1)).buscarTodosPorPauta(any());
    }
}
