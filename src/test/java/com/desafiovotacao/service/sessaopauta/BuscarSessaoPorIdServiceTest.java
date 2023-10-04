
package com.desafiovotacao.service.sessaopauta;

import com.desafiovotacao.repository.SessaoPautaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class BuscarSessaoPorIdServiceTest {

    @Mock
    private SessaoPautaRepository repository;

    @InjectMocks
    private BuscarSessaoPorIdService service;

    @Test
    void execute_shouldCallRepository() {
        String id = "1";
        service.buscar(id);

        verify(repository, times(1)).findById(id);
    }
}
