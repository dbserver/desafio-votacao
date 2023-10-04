
package com.desafiovotacao.service.votacao;


import com.desafiovotacao.repository.VotoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class BuscarVotacaoServiceTest {

    @Mock
    private VotoRepository repository;

    @InjectMocks
    private BuscarVotacaoService service;

    @Test
    void execute_shouldCallMethodFindByAssociadoIdAndSessaoPautaId() {
        String id = "1";
        service.buscarPorAssociadoAndSessao(id, id);

        verify(repository, times(1)).findByAssociadoIdAndSessaoPautaId(any(), any());
    }

    @Test
    void execute_shouldCallMethodfindAllByPautaId() {
        String id = "1";
        service.buscarTodosPorPauta(id);

        verify(repository, times(1)).findAllByPautaId(any());
    }
}
