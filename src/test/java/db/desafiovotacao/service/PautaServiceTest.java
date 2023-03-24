package db.desafiovotacao.service;

import db.desafiovotacao.exceptions.NoContentException;
import db.desafiovotacao.exceptions.NotFoundException;
import db.desafiovotacao.model.Pauta;
import db.desafiovotacao.model.Sessao;
import db.desafiovotacao.repository.PautaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;

class PautaServiceTest {

    private PautaService pautaService;

    @Mock
    private PautaRepository pautaRepository;

    @Mock
    private SessaoService sessaoService;

    @Mock
    private Pauta pauta;

    @Mock
    private Sessao sessao;

    @Mock
    private Pauta atualizar;

    @Mock
    private Page<Pauta> pautas;

    @BeforeEach
    void init(){

        MockitoAnnotations.openMocks(this);

        pautaService = new PautaService(pautaRepository, sessaoService);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        pauta = Pauta.builder()
                .id(1L)
                .titulo("Pauta 1")
                .descricao("Pauta Teste")
                .dataCriacao(LocalDateTime.parse("2023-01-01 12:00:00", dateTimeFormatter))
                .sessao(sessao)
                .ativo(true)
                .build();

        sessao = Sessao.builder()
                .inicioSessao(LocalDateTime.parse("2023-01-01 12:00:00", dateTimeFormatter))
                .finalSessao(LocalDateTime.parse("2023-01-01 12:01:00", dateTimeFormatter))
                .build();

        atualizar = Pauta.builder()
                .id(1L)
                .titulo("Novo Titulo")
                .descricao("Nova Descrição")
                .build();


    }


    @Test
    void deveCadastrarUmaPauta(){

        Mockito.when(pautaRepository.save(Mockito.any())).thenReturn(pauta);

        Pauta cadastrada = pautaService.cadastrarPauta(pauta);

        assertAll("cadastro de pauta",
                () -> assertNotNull(cadastrada),
                () -> assertEquals(pauta.getId(), cadastrada.getId()),
                () -> assertEquals(pauta.getTitulo(), cadastrada.getTitulo()),
                () -> assertEquals(pauta.getDescricao(), cadastrada.getDescricao()),
                () -> assertEquals(pauta.getDataCriacao(), cadastrada.getDataCriacao()),
                () -> assertEquals(pauta.getSessao(), cadastrada.getSessao()),
                () -> assertTrue(cadastrada.getAtivo())
        );
    }

    @Test
    void deveBuscarUmaPautaPorIDSucesso(){

        Mockito.when(pautaRepository.findById(Mockito.any())).thenReturn(Optional.of(pauta));

        Pauta encontrada = pautaService.buscarPautaPorID(Mockito.any());

        assertAll("busca de pauta por ID",
                () ->  assertNotNull(encontrada),
                () -> assertEquals(pauta.getId(), encontrada.getId()),
                () -> assertEquals(pauta.getTitulo(), encontrada.getTitulo()),
                () -> assertEquals(pauta.getDescricao(), encontrada.getDescricao()),
                () -> assertEquals(pauta.getDataCriacao(), encontrada.getDataCriacao()),
                () -> assertEquals(pauta.getSessao(), encontrada.getSessao()),
                () -> assertTrue(encontrada.getAtivo())
        );
    }

    @Test
    void deveBuscarUmaPautaPorIDException(){

        Mockito.when(pautaRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                () -> pautaService.buscarPautaPorID(Mockito.any())
        );
    }

    @Test
    void deveRetornarUmaListaDePautasSucesso(){

        Mockito.when(pautaRepository.findAllByAtivoTrue(Mockito.any())).thenReturn(Optional.of(pautas));

        Page<Pauta> encontradas = pautaService.listarPautas(Mockito.mock(Pageable.class));

        assertNotNull(encontradas);
        assertEquals(pautas, encontradas);
    }

    @Test
    void deveRetornarUmaListaDePautasException(){

        Mockito.when(pautaRepository.findAllByAtivoTrue(Mockito.any())).thenReturn(Optional.empty());

        assertThrows(NoContentException.class,
                () -> pautaService.listarPautas(Mockito.mock(Pageable.class))
        );
    }

    @Test
    void deveDeletarUmaPautaSucesso(){

        Mockito.when(pautaRepository.findById(Mockito.any())).thenReturn(Optional.of(pauta));

        Pauta deletada = pautaService.deletarPauta(Mockito.any());

        assertAll("deletar de pauta por ID",
                () ->  assertNotNull(deletada),
                () -> assertEquals(pauta.getId(), deletada.getId()),
                () -> assertEquals(pauta.getTitulo(), deletada.getTitulo()),
                () -> assertEquals(pauta.getDescricao(), deletada.getDescricao()),
                () -> assertEquals(pauta.getDataCriacao(), deletada.getDataCriacao()),
                () -> assertEquals(pauta.getSessao(), deletada.getSessao()),
                () -> assertFalse(deletada.getAtivo())
        );
    }

    @Test
    void deveDeletarUmaPautaException(){

        Mockito.when(pautaRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                () -> pautaService.deletarPauta(Mockito.any())
        );
    }

    @Test
    void deveAtualizarUmaPautaSucesso(){

        Mockito.when(pautaRepository.findById(Mockito.any())).thenReturn(Optional.of(pauta));

        Pauta pautaAtualizada = pautaService.atualizarPauta(atualizar);

        assertAll("atualizar pauta",
                () -> assertNotNull(pautaAtualizada),
                () -> assertEquals(atualizar.getTitulo(), pautaAtualizada.getTitulo()),
                () -> assertEquals(atualizar.getDescricao(), pautaAtualizada.getDescricao())
        );
    }

    @Test
    void deveAtualizarUmaPautaException(){

        Mockito.when(pautaRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                () -> pautaService.atualizarPauta(pauta)
        );
    }
}