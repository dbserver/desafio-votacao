package com.desafiovotacao.entrypoint;

import com.desafiovotacao.dto.PautaDTO;
import com.desafiovotacao.service.pauta.ListarPautasService;
import com.desafiovotacao.service.pauta.ObterPautaPorIdAndResultadoService;
import com.desafiovotacao.service.pauta.SalvarPautaService;
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
public class PautaControllerTest {

    @Mock
    private SalvarPautaService salvarPautaService;

    @Mock
    private ListarPautasService listarPautaService;

    @Mock
    private ObterPautaPorIdAndResultadoService obterResultadoVotacaoService;

    @InjectMocks
    private PautaController controller;

    @Test
    void execute_shouldCallListarPautasService() throws Exception {
        Pageable pageable = PageRequest.of(1, 2);
        List<PautaDTO> pautas = Arrays.asList(new PautaDTO());

        when(listarPautaService.listar(pageable)).thenReturn( new PageImpl(pautas));

        ResponseEntity<Page<PautaDTO>> response = controller.list(pageable);


        verify(listarPautaService, times(1)).listar(any());
        assertEquals(1, response.getBody().getTotalElements());
    }

    @Test
    void execute_shouldCallSalvarPautasService() throws Exception {
        PautaDTO pautaDTO = new PautaDTO();
        when(salvarPautaService.salvar(pautaDTO)).thenReturn(pautaDTO);
        controller.salvar(pautaDTO);
        verify(salvarPautaService, times(1)).salvar(any());
    }

    @Test
    void execute_shouldCallObterResultadoVotacaoService() throws Exception {
        String pautaId = "1";
        PautaDTO pautaDTO = new PautaDTO();
        when(obterResultadoVotacaoService.buscar(pautaId)).thenReturn(pautaDTO);
        controller.obterPautaPorId(pautaId);
        verify(obterResultadoVotacaoService, times(1)).buscar(any());
    }


}
