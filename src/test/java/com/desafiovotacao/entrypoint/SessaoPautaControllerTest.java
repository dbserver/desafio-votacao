package com.desafiovotacao.entrypoint;

import com.desafiovotacao.dto.PautaDTO;
import com.desafiovotacao.dto.SessaoPautaDTO;
import com.desafiovotacao.service.interfaces.ICriarSessaoPautaService;
import com.desafiovotacao.service.interfaces.IListarPautaService;
import com.desafiovotacao.service.interfaces.IObterPautaPorIdAndResultado;
import com.desafiovotacao.service.interfaces.ISalvarPautaService;
import com.desafiovotacao.service.sessaopauta.CriarSessaoPautaService;
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
public class SessaoPautaControllerTest {

    @Mock
    private CriarSessaoPautaService criarSessaoPautaService;

    @InjectMocks
    private SessaoPautaController controller;

    @Test
    void execute_shouldCallCriarSessaoPautaService() throws Exception {
        SessaoPautaDTO sessaoPautaDTO = new SessaoPautaDTO();
        when(criarSessaoPautaService.criar(sessaoPautaDTO)).thenReturn(sessaoPautaDTO);
        controller.salvar(sessaoPautaDTO);
        verify(criarSessaoPautaService, times(1)).criar(any());
    }

}
