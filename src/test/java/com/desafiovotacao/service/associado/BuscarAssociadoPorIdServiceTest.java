
package com.desafiovotacao.service.associado;

import com.desafiovotacao.repository.AssociadoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(SpringExtension.class)
public class BuscarAssociadoPorIdServiceTest  {

    @Mock
    private AssociadoRepository repository;

    @InjectMocks
    private BuscarAssociadoPorIdService service;

    @Test
    void execute_shouldCallRepository() {
        String id = "1";
        service.buscar(id);

        verify(repository, times(1)).findById(id);
    }
}
