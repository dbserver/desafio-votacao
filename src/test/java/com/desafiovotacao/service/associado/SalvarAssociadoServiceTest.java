package com.desafiovotacao.service.associado;

import com.desafiovotacao.domain.Associado;
import com.desafiovotacao.dto.AssociadoDTO;
import com.desafiovotacao.repository.AssociadoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class SalvarAssociadoServiceTest {

    @Mock
    private AssociadoRepository repository;

    @Mock
    private ValidadorCPF validadorCPF;

    @InjectMocks
    private SalvarAssociadoService service;

    @Test
    void execute_shouldCallRepositoryAndValidator() throws Exception {
        AssociadoDTO associadoDTO = new AssociadoDTO();

        when(validadorCPF.validar(any())).thenReturn(true);
        when(repository.save(any())).thenReturn(new Associado());

        service.salvar(associadoDTO);

        verify(validadorCPF, times(1)).validar(any());
        verify(repository, times(1)).save(any());
    }
}
