package db.desafiovotacao.service;

import db.desafiovotacao.enums.EnumVoto;
import db.desafiovotacao.exceptions.ConflictException;
import db.desafiovotacao.exceptions.NotFoundException;
import db.desafiovotacao.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class VotoServiceTest {

    private VotoService votoService;

    @Mock
    private VotoPautaService votoPautaService;

    @Mock
    private AssociadoService associadoService;

    @Mock
    private AssociadoPautaService associadoPautaService;

    @Mock
    Associado associado;

    @Mock
    VotoPauta votoPauta;

    @Mock
    Pauta pauta;

    @Mock
    Sessao sessao;

    @Mock
    Voto voto;

    @Mock
    Voto votoForaDoHorario;

    @BeforeEach
    void init(){

        MockitoAnnotations.openMocks(this);
        votoService = new VotoService(votoPautaService, associadoService, associadoPautaService);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        sessao = Sessao.builder()
                .inicioSessao(LocalDateTime.parse("2023-01-01 12:00:00", dateTimeFormatter))
                .finalSessao(LocalDateTime.parse("2023-01-01 12:05:00", dateTimeFormatter))
                .build();

        associado = Associado.builder()
                .id(1L)
                .CPF("000.000.000-00")
                .build();

        pauta = Pauta.builder()
                .id(1L)
                .titulo("Pauta Teste")
                .descricao("Teste")
                .ativo(true)
                .sessao(sessao)
                .dataCriacao(LocalDateTime.parse("2023-01-01 11:00:00", dateTimeFormatter))
                .build();

        voto = Voto.builder()
                .dataHoraVoto(LocalDateTime.parse("2023-01-01 12:02:00", dateTimeFormatter))
                .voto(EnumVoto.SIM)
                .build();


        votoForaDoHorario = Voto.builder()
                .dataHoraVoto(LocalDateTime.parse("2023-01-01 12:05:01", dateTimeFormatter))
                .voto(EnumVoto.SIM)
                .build();

        votoPauta = VotoPauta.builder()
                .id(1L)
                .voto(voto)
                .pauta(pauta)
                .build();
    }

    @Test
    void deveCadastrarVotoSucesso(){

        AssociadoPauta associadoPauta = AssociadoPauta.builder()
                .id(1L)
                .associado(associado)
                .pauta(pauta)
                .votou(false)
                .build();

        Mockito.when(associadoService.buscarPorCPF(Mockito.any())).thenReturn(associado);

        votoService.validarDataHoraVoto(votoPauta);

        Mockito.when(associadoPautaService.associadoEstaCadastrado(associado, pauta)).thenReturn(true);

        Mockito.when(associadoPautaService.buscarAssociadoPauta(associado, pauta)).thenReturn(associadoPauta);

        Mockito.when(votoPautaService.cadastrarVotoPauta(votoPauta)).thenReturn(votoPauta);

        VotoPauta votoCadastrado = votoService.cadastrarVoto(votoPauta, associado.getCPF());

        assertAll("cadastro de voto",
                () -> assertNotNull(votoCadastrado),
                () -> assertEquals(votoPauta.getId(),votoCadastrado.getId()),
                () -> assertEquals(votoPauta.getPauta(), votoCadastrado.getPauta()),
                () -> assertEquals(votoPauta.getVoto(), votoCadastrado.getVoto())
        );
    }

    @Test
    void deveRetornarExceptionSeVotoForForaDoHorarioDeVotacao(){

        votoPauta = VotoPauta.builder()
                .id(1L)
                .voto(votoForaDoHorario)
                .pauta(pauta)
                .build();

        Mockito.when(associadoService.buscarPorCPF(Mockito.any())).thenReturn(associado);

        votoService.validarDataHoraVoto(votoPauta);

        assertThrows(ConflictException.class,
                () -> votoService.cadastrarVoto(votoPauta, associado.getCPF()));
    }

    @Test
    void deveRetornarExceptionSeAssociadoNaoCadastradoNaPauta(){

        Mockito.when(associadoService.buscarPorCPF(Mockito.any())).thenReturn(associado);

        votoService.validarDataHoraVoto(votoPauta);

        Mockito.when(associadoPautaService.associadoEstaCadastrado(associado, pauta)).thenReturn(false);

        assertThrows(NotFoundException.class,
                () -> votoService.cadastrarVoto(votoPauta, associado.getCPF()));
    }


    @Test
    void deveRetornarExceptionSeAssociadoJaVotouNaPauta(){

        AssociadoPauta associadoPauta = AssociadoPauta.builder()
                .id(1L)
                .associado(associado)
                .pauta(pauta)
                .votou(true)
                .build();

        Mockito.when(associadoService.buscarPorCPF(Mockito.any())).thenReturn(associado);

        votoService.validarDataHoraVoto(votoPauta);

        Mockito.when(associadoPautaService.associadoEstaCadastrado(associado, pauta)).thenReturn(true);

        Mockito.when(associadoPautaService.buscarAssociadoPauta(associado, pauta)).thenReturn(associadoPauta);

        assertThrows(ConflictException.class,
                () -> votoService.cadastrarVoto(votoPauta, associado.getCPF()));
    }

    @Test
    void deveRetornarVerdadeiroSeDataHoraVotoEstiverDentroDoHorarioDeVotacao(){

        Boolean estaNoHorario = votoService.validarDataHoraVoto(votoPauta);

        assertTrue(estaNoHorario);
    }

    @Test
    void deveRetornarFalsoSeDataHoraVotoEstiverForaDoHorarioDeVotacao(){

        votoPauta = VotoPauta.builder()
                .id(1L)
                .voto(votoForaDoHorario)
                .pauta(pauta)
                .build();

        Boolean estaNoHorario = votoService.validarDataHoraVoto(votoPauta);

        assertFalse(estaNoHorario);
    }


    @Test
    void deveRetornarOResultadoDeUmaVotacao(){

        Mockito.when(votoPautaService.contagemVotos(pauta)).thenReturn(1);

        Mockito.when(votoPautaService.contagemVotosPositivos(pauta)).thenReturn(1);

        Resultado resultado = votoService.resultadoVotacao(pauta);

        assertAll("resultado votacao",
                () -> assertNotNull(resultado),
                () -> assertEquals(1, resultado.getVotosPositivos()),
                () -> assertEquals(0, resultado.getVotosNegativos())
        );

    }


}