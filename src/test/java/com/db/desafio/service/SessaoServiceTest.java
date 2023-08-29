package com.db.desafio.service;

import com.db.desafio.entity.Pauta;
import com.db.desafio.entity.Sessao;
import com.db.desafio.exception.SessaoException;
import com.db.desafio.repository.PautaRepository;
import com.db.desafio.repository.SessaoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.db.desafio.util.factory.PautaFactory.ListaDePautasFactory;
import static com.db.desafio.util.factory.PautaFactory.pautaFactory;
import static com.db.desafio.util.factory.SessaoFactory.sessaoFactory;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SessaoServiceTest {
    @InjectMocks
    private SessaoService sessaoService;
    @Mock
    private SessaoRepository sessaoRepository;
    @Mock
    private PautaService pautaService;
    private static final Long ID = 1L;


    @Test
    @DisplayName("Deve Abrir uma nova sessao")
    void deveAbrirNovaSessao()  {
        when(pautaService.obterPautaPorId(ID)).thenReturn(pautaFactory());
        when(sessaoRepository.save(any())).thenReturn(sessaoFactory());

        sessaoService.abrirSessao(ID);

        verify(sessaoRepository, times(1)).save(any());
        verify(pautaService, times(1)).obterPautaPorId(ID);
    }
}
