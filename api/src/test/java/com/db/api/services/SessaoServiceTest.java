package com.db.api.services;

import com.db.api.exceptions.RegistroNaoEncontradoException;
import com.db.api.models.Pauta;
import com.db.api.models.Sessao;
import com.db.api.repositories.PautaRepository;
import com.db.api.repositories.SessaoRepository;
import com.db.api.repositories.VotoRepository;
import com.db.api.stubs.PautaStub;
import com.db.api.stubs.SessaoStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class SessaoServiceTest {

    private SessaoService sessaoService;

    @Mock
    private SessaoRepository sessaoRepository;

    @Mock
    private PautaRepository pautaRepository;
    @Mock
    private VotoRepository votoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sessaoService = new SessaoService(sessaoRepository, pautaRepository, votoRepository);
    }

    @Test
    @DisplayName("Ao criar iniciar uma nova sessão de votação com valores válidos, o método save do repositório deve ser chamado\"")
    void testIniciarSessaoVotacaComDadosValidos() {

        Pauta pauta = PautaStub.gerarPautaDtoValida();
        when(pautaRepository.findByTitulo(pauta.getTitulo())).thenReturn(Optional.of(pauta));

        sessaoService.iniciarSessaoVotacao(pauta.getTitulo(), SessaoStub.gerarSessaoDtoValida().getDataEncerramento());

        verify(sessaoRepository, times(1)).save(any(Sessao.class));
    }

    @Test
    @DisplayName("Deve retorna uma exceção caso tente iniciar sessão de votação de uma pauta não cadastrada")
    void testIniciarSessaoVotacaoPautaInexiste() {
        assertThrows(RegistroNaoEncontradoException.class, () -> sessaoService.iniciarSessaoVotacao("Planejamento segundo semestre", null));

        verify(sessaoRepository, never()).save(any(Sessao.class));
    }
}
