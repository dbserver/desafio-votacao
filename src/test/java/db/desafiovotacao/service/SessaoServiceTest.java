package db.desafiovotacao.service;

import db.desafiovotacao.exceptions.ConflictException;
import db.desafiovotacao.model.Sessao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class SessaoServiceTest {

    private SessaoService sessaoService;

    @Mock
    Sessao sessao30Seg;

    @Mock
    Sessao sessaoInicioIgualFinal;

    @Mock
    Sessao sessaoFinalAntesInicio;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);

        sessaoService = new SessaoService();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        sessao30Seg = Sessao.builder()
                .inicioSessao(LocalDateTime.parse("2023-01-01 12:00:00", dateTimeFormatter))
                .finalSessao(LocalDateTime.parse("2023-01-01 12:00:30", dateTimeFormatter))
                .build();

        sessaoInicioIgualFinal = Sessao.builder()
                .inicioSessao(LocalDateTime.parse("2023-01-01 12:00:00", dateTimeFormatter))
                .finalSessao(LocalDateTime.parse("2023-01-01 12:00:00", dateTimeFormatter))
                .build();

        sessaoFinalAntesInicio = Sessao.builder()
                .inicioSessao(LocalDateTime.parse("2023-01-01 12:00:00", dateTimeFormatter))
                .finalSessao(LocalDateTime.parse("2023-01-01 11:00:00", dateTimeFormatter))
                .build();
    }

    @Test
    void deveRetornarSessaoComDuracaoPadraoParaSessaoComDuracaoInferiorA60Segundos(){

        Sessao sessao = sessaoService.validarSessao(sessao30Seg);

        assertAll("validar duração de sessão",
                () -> assertNotNull(sessao),
                () -> assertEquals(sessao30Seg.getInicioSessao(), sessao.getInicioSessao()),
                () -> assertEquals(sessao30Seg.getInicioSessao().plusMinutes(1), sessao.getFinalSessao())
        );
    }

    @Test
    void deveRetornarSessaoComDuracaoPadraoParaSessaoComHoraInicioIgualAHoraFinal(){

        Sessao sessao = sessaoService.validarSessao(sessaoInicioIgualFinal);

        assertAll("validar duração de sessão",
                () -> assertNotNull(sessao),
                () -> assertEquals(sessaoInicioIgualFinal.getInicioSessao(), sessao.getInicioSessao()),
                () -> assertEquals(sessaoInicioIgualFinal.getInicioSessao().plusMinutes(1), sessao.getFinalSessao())
        );
    }

    @Test
    void deveRetornarSessaoComDuracaoPadraoException(){
        assertThrows(ConflictException.class,
                () -> sessaoService.validarSessao(sessaoFinalAntesInicio)
        );
    }
}