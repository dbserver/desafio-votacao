package br.com.aplicationvotacao.aplicationvotacao;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.aplicationvotacao.aplicationvotacao.dto.PautaDTO;
import br.com.aplicationvotacao.aplicationvotacao.dto.ResultadoPautaDTO;
import br.com.aplicationvotacao.aplicationvotacao.mapper.PautaMapper;
import br.com.aplicationvotacao.aplicationvotacao.model.Pauta;
import br.com.aplicationvotacao.aplicationvotacao.model.SessaoVotacao;
import br.com.aplicationvotacao.aplicationvotacao.repository.PautaRepository;
import br.com.aplicationvotacao.aplicationvotacao.repository.SessaoVotacaoRepository;
import br.com.aplicationvotacao.aplicationvotacao.service.PautaService;
import br.com.aplicationvotacao.aplicationvotacao.service.utils.ResultadoPautaDTOUtils;

@ExtendWith(MockitoExtension.class)
public class PautaServiceTest {

    @InjectMocks
    private PautaService pautaService;

    @Mock
    private PautaRepository pautaRepository;

    @Mock
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    @Mock
    private ResultadoPautaDTOUtils resultadoPautaDTOUtils;

    @Mock
    private PautaMapper pautaMapper;

    @Test
    void resultadoPautaIsOk() {

        var pauta = Pauta.builder().descricao("pauta 01").id(1L).build();

        var sessaoVotacao = SessaoVotacao.builder().id(1L).pauta(pauta).dataInicio(LocalDateTime.now()).dataFim(LocalDateTime.now().minusHours(1)).build();

        var resultadoPautaDto = ResultadoPautaDTO.builder().build();

        when(pautaRepository.findById(any())).thenReturn(Optional.of(pauta));
        when(sessaoVotacaoRepository.findByPautaId(any())).thenReturn(Optional.of(sessaoVotacao));
        when(resultadoPautaDTOUtils.getResultadoPautaDTOFromSessaoVotacao(any())).thenReturn(resultadoPautaDto);

        ResultadoPautaDTO dto =  pautaService.resultadoPauta(1L);

        assertNotNull(dto);

    }


    @Test
    void resultadoPautaIsExceptionPautaNaoExiste() {

        var pauta = Pauta.builder().descricao("pauta 01").id(1L).build();

        when(pautaRepository.findById(any())).thenReturn(Optional.empty());

        IllegalArgumentException entityNotFoundException = assertThrows(IllegalArgumentException.class,
                () -> pautaService.resultadoPauta(1L));

        assertNotNull(entityNotFoundException);
        assertEquals("Pauta não existe.", entityNotFoundException.getMessage());

    }

    @Test
    void resultadoPautaIsExceptionSessaoDeVotacaoNaoExiste() {

        var pauta = Pauta.builder().descricao("pauta 01").id(1L).build();

        var sessaoVotacao = SessaoVotacao.builder().id(1L).pauta(pauta).dataInicio(LocalDateTime.now())
                .dataFim(LocalDateTime.now().minusHours(1)).build();

        when(pautaRepository.findById(any())).thenReturn(Optional.of(pauta));
        when(sessaoVotacaoRepository.findByPautaId(any())).thenReturn(Optional.empty());

        IllegalArgumentException entityNotFoundException = assertThrows(IllegalArgumentException.class,
                () -> pautaService.resultadoPauta(1L));

        assertNotNull(entityNotFoundException);
        assertEquals("Sessão de votação não existe.", entityNotFoundException.getMessage());

    }


    @Test
    void getPautaByIdIsOk() {

        var pauta = Pauta.builder().descricao("pauta 01").id(1L).build();

        when(pautaRepository.findById(any())).thenReturn(Optional.of(pauta));

        Optional<Pauta> pautaObject = pautaService.getPautaById(1L);

        assertNotNull(pautaObject);
    }

    @Test
    void getbuscarPautasIsOk() {

        var pauta = Pauta.builder().descricao("pauta 01").id(1L).build();

        when(pautaRepository.findAll()).thenReturn(List.of(pauta));

        List<Pauta> pautaObject = pautaService.buscarPautas();

        assertNotNull(pautaObject);
    }

    @Test
    void salvarPautaIsOk() {

        var pauta = Pauta.builder().descricao("pauta 01").id(1L).build();

        var pautaDTO =  PautaDTO.builder().descricao("Pauta 01").build();

        when(pautaRepository.save(any())).thenReturn(pauta);

        Pauta pautaSaved = pautaService.salvarPauta(pautaDTO);

        assertNotNull(pautaSaved);
    }



}