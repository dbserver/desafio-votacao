
package com.desafiovotacao.service.associado;

import com.desafiovotacao.domain.Associado;
import com.desafiovotacao.domain.Pauta;
import com.desafiovotacao.repository.AssociadoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;



@ExtendWith(SpringExtension.class)
public class ListarAssociadosServiceTest {

    @Mock
    private AssociadoRepository repository;

    @InjectMocks
    private ListarAssociadosService service;

    @Test
    void execute_shouldCallRepository() {
        Associado associado = new Associado();
        associado.setId("id");
        associado.setCpf("cpf");
        associado.setNome("nome");

        PageImpl<Associado> page = new PageImpl<>(Collections.singletonList(associado));

        when(repository.findAll(Pageable.ofSize(1))).thenReturn(page);

        Pageable pageable = Pageable.ofSize(1);
        service.listar(pageable);

        verify(repository, times(1)).findAll(pageable);
    }
}
