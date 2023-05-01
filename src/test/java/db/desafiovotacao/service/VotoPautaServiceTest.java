package db.desafiovotacao.service;

import db.desafiovotacao.enums.EnumVoto;
import db.desafiovotacao.model.Pauta;
import db.desafiovotacao.model.Sessao;
import db.desafiovotacao.model.Voto;
import db.desafiovotacao.model.VotoPauta;
import db.desafiovotacao.repository.VotoPautaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class VotoPautaServiceTest {

    private VotoPautaService votoPautaService;

    @Mock
    private VotoPautaRepository votoPautaRepository;

    @Mock
    VotoPauta votoPauta;

    @Mock
    Pauta pauta;

    @Mock
    Voto voto;

    @Mock
    Sessao sessao;

    @Captor
    private ArgumentCaptor<Pauta> captor;


    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        votoPautaService = new VotoPautaService(votoPautaRepository);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        sessao = Sessao.builder()
                .inicioSessao(LocalDateTime.parse("2023-01-01 12:00:00", dateTimeFormatter))
                .finalSessao(LocalDateTime.parse("2023-01-01 12:05:00", dateTimeFormatter))
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

        votoPauta = VotoPauta.builder()
                .id(1L)
                .voto(voto)
                .pauta(pauta)
                .build();
    }

    @Test
    void deveCadastrarUmVotoPauta() {

        Mockito.when(votoPautaRepository.save(Mockito.any())).thenReturn(votoPauta);

        VotoPauta cadastrado = votoPautaService.cadastrarVotoPauta(votoPauta);

        assertAll("cadastro de voto na pauta",
                () -> assertNotNull(cadastrado),
                () -> assertEquals(votoPauta.getId(), cadastrado.getId()),
                () -> assertEquals(votoPauta.getPauta(), cadastrado.getPauta()),
                () -> assertEquals(votoPauta.getVoto(), cadastrado.getVoto())
        );
    }

    @Test
    void deveRetornarAQuantidadeTotalDeVotosEmUmaPauta() {

        pauta = Pauta.builder()
                .votos(List.of(votoPauta, votoPauta, votoPauta))
                .build();

        Mockito.when(votoPautaRepository.findByPauta(Mockito.any())).thenReturn(List.of(votoPauta, votoPauta, votoPauta));

        Integer votos = votoPautaService.contagemVotos(pauta);

        Mockito.verify(votoPautaRepository).findByPauta(captor.capture());

        List<VotoPauta> votosPauta = captor.getValue().getVotos();

        assertNotNull(votos);
        assertEquals(votosPauta.size(), votos);
    }

    @Test
    void deveRetornarQuantidadeDeVotosPositivosEmUmaPauta() {

        pauta = Pauta.builder()
                .votos(List.of(votoPauta, votoPauta))
                .build();

        Mockito.when(votoPautaRepository.findByPauta(Mockito.any())).thenReturn(List.of(votoPauta, votoPauta));

        Integer votos = votoPautaService.contagemVotosPositivos(pauta);

        Mockito.verify(votoPautaRepository).findByPauta(captor.capture());

        List<Voto> votoList = captor.getValue().getVotos()
                .stream()
                .map(VotoPauta::getVoto)
                .toList()
                .stream()
                .filter(v->v.getVoto() == EnumVoto.SIM)
                .toList();

        assertNotNull(votos);
        assertEquals(votoList.size(), votos);
    }
}