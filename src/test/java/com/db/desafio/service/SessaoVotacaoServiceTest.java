package com.db.desafio.service;

import com.db.desafio.entity.SessaoVotacao;
import com.db.desafio.exception.SessaoVotacaoException;
import com.db.desafio.repository.SessaoVotacaoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.db.desafio.util.factory.PautaFactory.pautaFactory;
import static com.db.desafio.util.factory.SessaoFactory.ListaDeSessoesFactory;
import static com.db.desafio.util.factory.SessaoFactory.sessaoFactory;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SessaoVotacaoServiceTest {
    @InjectMocks
    private SessaoVotacaoService sessaoVotacaoService;
    @Mock
    private SessaoVotacaoRepository sessaoVotacaoRepository;
    @Mock
    private PautaService pautaService;
    private static final Long ID = 1L;


    @Test
    @DisplayName("Deve Abrir uma nova sessao")
    void deveAbrirNovaSessao() {
        when(pautaService.obterPautaPorId(ID)).thenReturn(pautaFactory());
        when(sessaoVotacaoRepository.save(any())).thenReturn(sessaoFactory());

        sessaoVotacaoService.abrirSessao(ID);

        verify(sessaoVotacaoRepository, times(1)).save(any());
        verify(pautaService, times(1)).obterPautaPorId(ID);
    }

    @Test
    @DisplayName("Deve retornar uma lista de sessoes")
    void deveRetornarListaDeSessoes() {
        List<SessaoVotacao> resultadoEsperado = ListaDeSessoesFactory();
        when(sessaoVotacaoRepository.findAll()).thenReturn(ListaDeSessoesFactory());

        List<SessaoVotacao> resultadoAtual = sessaoVotacaoService.obterSessoes();

        assertThat(resultadoAtual).usingRecursiveComparison().isEqualTo(resultadoEsperado);
        verify(sessaoVotacaoRepository).findAll();
    }

    @Test
    @DisplayName("Deve obter uma sessao passando um id")
    void deveobterSessaoPorId() {
        SessaoVotacao resultadoEsperado = sessaoFactory();
        when(sessaoVotacaoRepository.findById(ID)).thenReturn(Optional.of(sessaoFactory()));

        SessaoVotacao resultadoAtual = sessaoVotacaoService.obterSessao(ID);

        assertThat(resultadoAtual.getPauta().getTitulo()).isEqualTo(resultadoEsperado.getPauta().getTitulo());
        assertThat(resultadoAtual.getPauta()).isEqualTo(resultadoEsperado.getPauta());
        assertThat(resultadoAtual.getInicioSessao()).isEqualTo(resultadoEsperado.getInicioSessao());
        verify(sessaoVotacaoRepository).findById(ID);
    }

    @Test
    @DisplayName("Deve retornar uma exception quando sessao não existir")
    void deveRetornarExceptionQuandoSessaoInexistente() {
        when(sessaoVotacaoRepository.findById(ID)).thenReturn(Optional.empty());

        assertThrows(SessaoVotacaoException.class, () ->
                sessaoVotacaoService.obterSessao(ID));
    }
}
