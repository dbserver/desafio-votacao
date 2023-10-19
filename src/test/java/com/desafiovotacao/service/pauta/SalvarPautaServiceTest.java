package com.desafiovotacao.service.pauta;

import com.desafiovotacao.domain.Pauta;
import com.desafiovotacao.dto.PautaDTO;
import com.desafiovotacao.repository.PautaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class SalvarPautaServiceTest{

    @Mock
    private PautaRepository repository;

    @InjectMocks
    private SalvarPautaService service;

    @Test
    void execute_shouldCallRepository() throws Exception {
        PautaDTO pautaDTO = new PautaDTO();

        when(repository.save(any())).thenReturn(new Pauta());

        service.salvar(pautaDTO);

        verify(repository, times(1)).save(any());
    }
}
