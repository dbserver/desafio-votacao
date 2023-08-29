package com.db.desafio.service;

import com.db.desafio.dto.PautaResultadoDto;
import com.db.desafio.entity.Pauta;
import com.db.desafio.exception.PautaException;
import com.db.desafio.repository.PautaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;
import java.util.Optional;

import static com.db.desafio.util.factory.PautaFactory.ListaDePautasFactory;
import static com.db.desafio.util.factory.PautaFactory.pautaFactory;
import static com.db.desafio.util.factory.PautaResultadoDtoFactory.pautaResultadoDtoFactory;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PautaServiceTest {

    @InjectMocks
    private PautaService pautaService;
    @Mock
    private PautaRepository pautaRepository;
    private static final Long PAUTA_ID = 1L;


    @Test
    @DisplayName("Deve retornar uma lista com todas as pautas")
    void deveRetornarListaDePautas() {
        List<Pauta> resultadoEsperado = ListaDePautasFactory();
        when(pautaRepository.findAll()).thenReturn(ListaDePautasFactory());

        List<Pauta> resultadoAtual = pautaService.obterPautas();

        assertThat(resultadoAtual).usingRecursiveComparison().isEqualTo(resultadoEsperado);
        verify(pautaRepository).findAll();
    }

    @Test
    @DisplayName("Deve obter um  pauta passando um id")
    void deveobterPautaPorId() {
        Pauta resultadoEsperado = pautaFactory();
        when(pautaRepository.findById(PAUTA_ID)).thenReturn(Optional.of(pautaFactory()));

        Pauta resultadoAtual = pautaService.obterPautaPorId(PAUTA_ID);

        assertThat(resultadoAtual).isEqualTo(resultadoEsperado);
        verify(pautaRepository).findById(PAUTA_ID);
    }

    @Test
    @DisplayName("Deve retornar uma exception quando pauta não existir")
    void deveRetornarExceptionQuandoPautaInexistente() {
        when(pautaRepository.findById(PAUTA_ID)).thenReturn(Optional.empty());

        assertThrows(PautaException.class, () ->
                pautaService.obterPautaPorId(PAUTA_ID));

    }

    @Test
    @DisplayName("Deve criar uma pauta")
    void deveCriarAssociado() {
        when(pautaRepository.findByTitulo(any())).thenReturn(Optional.empty());
        when(pautaRepository.save(pautaFactory())).thenReturn(pautaFactory());

        pautaService.criarPauta(pautaFactory());

        verify(pautaRepository, times(1)).save(pautaFactory());
       verify(pautaRepository, times(1)).findByTitulo(any());
    }

    @Test
    @DisplayName("Deve retornar uma exception quando pauta já existir")
    void deveRetornarExceptionQuandoPautaExistente() {
        when(pautaRepository.findByTitulo(pautaFactory().getTitulo())).thenReturn(Optional.of(pautaFactory()));

        assertThrows(PautaException.class, () ->
                pautaService.criarPauta(pautaFactory()));

    }

    @Test
    @DisplayName("Deve deletar uma Pauta existente")
    void deveDeletarPautaexistente() {
        when(pautaRepository.findById(PAUTA_ID)).thenReturn(Optional.of(pautaFactory()));
        doNothing().when(pautaRepository).deleteById(PAUTA_ID);

        pautaService.deletarPauta(PAUTA_ID);

        verify(pautaRepository).deleteById(PAUTA_ID);
    }
    @Test
    @DisplayName("Deve retornar uma exception quando tentar deletar Pauta não existente")
    void deveRetornarExceptionQuandoDeletarPautaInexistente() {
        when(pautaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(PautaException.class, () ->
                pautaService.deletarPauta(PAUTA_ID));
    }

    @Test
    @DisplayName("Deve obter um  pauta passando um id")
    void deveobterPautaPId() {
        PautaResultadoDto resultadoEsperado = pautaResultadoDtoFactory();
        when(pautaRepository.findById(PAUTA_ID)).thenReturn(Optional.of(pautaFactory()));

        PautaResultadoDto resultadoAtual = pautaService.obterResultadoPauta(PAUTA_ID);

        assertThat(resultadoAtual.getTitulo()).isEqualTo(resultadoEsperado.getTitulo());
        assertThat(resultadoAtual.getResultado()).isEqualTo(resultadoEsperado.getResultado());
        verify(pautaRepository).findById(PAUTA_ID);
    }

}
