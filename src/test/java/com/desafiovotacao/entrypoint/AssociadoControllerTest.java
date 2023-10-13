package com.desafiovotacao.entrypoint;

import com.desafiovotacao.dto.AssociadoDTO;
import com.desafiovotacao.service.associado.ListarAssociadosService;
import com.desafiovotacao.service.associado.SalvarAssociadoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class AssociadoControllerTest {

    @Mock
    private SalvarAssociadoService salvarAssociadoService;

    @Mock
    private ListarAssociadosService listarAssociadosService;

    @InjectMocks
    private AssociadoController controller;

    @Test
    void execute_shouldCallListarAssociadosService() throws Exception {
        Pageable pageable = PageRequest.of(1, 2);
        List<AssociadoDTO> associados = Arrays.asList(new AssociadoDTO());

        when(listarAssociadosService.listar(pageable)).thenReturn( new PageImpl(associados));

        ResponseEntity<Page<AssociadoDTO>> response = controller.list(pageable);


        verify(listarAssociadosService, times(1)).listar(any());
        assertEquals(1, response.getBody().getTotalElements());
    }

    @Test
    void execute_shouldCallSalvarAssociadosService() throws Exception {
        AssociadoDTO associadoDTO = new AssociadoDTO();
        when(salvarAssociadoService.salvar(associadoDTO)).thenReturn(associadoDTO);
        controller.salvar(associadoDTO);
        verify(salvarAssociadoService, times(1)).salvar(any());
    }



}
