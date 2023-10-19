
package com.desafiovotacao.service.pauta;

import com.desafiovotacao.domain.Pauta;
import com.desafiovotacao.repository.PautaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;

import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
public class ListarPautasServiceTest {

    @Mock
    private PautaRepository repository;

    @InjectMocks
    private ListarPautasService service;

    @Test
    void execute_shouldCallRepository() {
        Pauta pauta = new Pauta();
        pauta.setId("id");
        pauta.setVotosAFavor(0L);
        pauta.setVotosContra(0L);
        pauta.setDescricao("pauta");

        PageImpl<Pauta> page = new PageImpl<>(Collections.singletonList(pauta));
        when(repository.findAll(Pageable.ofSize(1))).thenReturn(page);


        Pageable pageable = Pageable.ofSize(1);
        service.listar(pageable);

        verify(repository, times(1)).findAll(pageable);
    }
}
