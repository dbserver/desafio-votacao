
package com.desafiovotacao.service.pauta;

import com.desafiovotacao.repository.PautaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class BuscarPautaPorIdServiceTest {

    @Mock
    private PautaRepository repository;

    @InjectMocks
    private BuscarPautaPorIdService service;

    @Test
    void execute_shouldCallRepository() {
        String id = "1";
        service.buscar(id);

        verify(repository, times(1)).findById(id);
    }
}
