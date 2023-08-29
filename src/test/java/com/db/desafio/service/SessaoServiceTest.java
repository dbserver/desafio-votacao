package com.db.desafio.service;

import com.db.desafio.entity.Associado;
import com.db.desafio.entity.Pauta;
import com.db.desafio.entity.Sessao;
import com.db.desafio.exception.AssociadoException;
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
import java.util.Optional;

import static com.db.desafio.util.factory.AssociadoFactory.ListaDeAssociadosFactory;
import static com.db.desafio.util.factory.AssociadoFactory.associadoFactory;
import static com.db.desafio.util.factory.PautaFactory.ListaDePautasFactory;
import static com.db.desafio.util.factory.PautaFactory.pautaFactory;
import static com.db.desafio.util.factory.SessaoFactory.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    void deveAbrirNovaSessao() {
        when(pautaService.obterPautaPorId(ID)).thenReturn(pautaFactory());
        when(sessaoRepository.save(any())).thenReturn(sessaoFactory());

        sessaoService.abrirSessao(ID);

        verify(sessaoRepository, times(1)).save(any());
        verify(pautaService, times(1)).obterPautaPorId(ID);
    }

    @Test
    @DisplayName("Deve retornar uma lista de sessoes")
    void deveRetornarListaDeSessoes() {
        List<Sessao> resultadoEsperado = ListaDeSessoesFactory();
        when(sessaoRepository.findAll()).thenReturn(ListaDeSessoesFactory());

        List<Sessao> resultadoAtual = sessaoService.obterSessoes();

        assertThat(resultadoAtual).usingRecursiveComparison().isEqualTo(resultadoEsperado);
        verify(sessaoRepository).findAll();
    }

    @Test
    @DisplayName("Deve obter uma sessao passando um id")
    void deveobterSessaoPorId() {
        Sessao resultadoEsperado = sessaoFactory();
        when(sessaoRepository.findById(ID)).thenReturn(Optional.of(sessaoFactory()));

        Sessao resultadoAtual = sessaoService.obterSessao(ID);

        assertThat(resultadoAtual.getPauta().getTitulo()).isEqualTo(resultadoEsperado.getPauta().getTitulo());
        assertThat(resultadoAtual.getPauta()).isEqualTo(resultadoEsperado.getPauta());
        assertThat(resultadoAtual.getInicioSessao()).isEqualTo(resultadoEsperado.getInicioSessao());
        verify(sessaoRepository).findById(ID);
    }

    @Test
    @DisplayName("Deve retornar uma exception quando sessao não existir")
    void deveRetornarExceptionQuandoSessaoInexistente() {
        when(sessaoRepository.findById(ID)).thenReturn(Optional.empty());

        assertThrows(SessaoException.class, () ->
                sessaoService.obterSessao(ID));
    }
}
