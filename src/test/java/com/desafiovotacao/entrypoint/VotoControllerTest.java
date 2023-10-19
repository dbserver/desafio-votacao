package com.desafiovotacao.entrypoint;

import com.desafiovotacao.dto.PautaDTO;
import com.desafiovotacao.dto.VotoAssociadoDTO;
import com.desafiovotacao.service.interfaces.ICriarVotoService;
import com.desafiovotacao.service.pauta.ListarPautasService;
import com.desafiovotacao.service.pauta.ObterPautaPorIdAndResultadoService;
import com.desafiovotacao.service.pauta.SalvarPautaService;
import com.desafiovotacao.service.votacao.CriarVotoService;
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
public class VotoControllerTest {

    @Mock
    private CriarVotoService criarVotoService;

    @InjectMocks
    private VotoController controller;

    @Test
    void execute_shouldCallCriarVotoService() throws Exception {
        VotoAssociadoDTO votoAssociadoDTO = new VotoAssociadoDTO();
        when(criarVotoService.criar(votoAssociadoDTO)).thenReturn(votoAssociadoDTO);
        controller.votar(votoAssociadoDTO);
        verify(criarVotoService, times(1)).criar(any());
    }
}
