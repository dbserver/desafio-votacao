package br.com.occ.desafiovotacao.v1.service;

import br.com.occ.desafiovotacao.v1.model.Pauta;
import br.com.occ.desafiovotacao.v1.model.Sessao;
import br.com.occ.desafiovotacao.v1.repository.PautaRepository;
import br.com.occ.desafiovotacao.v1.repository.SessaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static br.com.occ.desafiovotacao.utils.EntityUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class SessaoServiceTest {

    @InjectMocks
    SessaoService service;

    @Mock
    SessaoRepository repository;

    @Mock
    PautaService pautaService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenFindByIdThenReturnAnSessaoInstance() {
        when(repository.findById(anyLong())).thenReturn(criarSessaoOptional());

        Sessao response = service.findById(ID);

        assertNotNull(response);

        assertEquals(Sessao.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(DATA_INICIO, response.getDataInicio());
        assertEquals(DATA_FIM_DEZ_MINUTOS, response.getDataFim());
    }

    @Test
    void whenFindAllThenReturnAnListOfAsSessao() {
        when(repository.findAll()).thenReturn(List.of(criarSessaoUmMinuto()));

        List<Sessao> response = service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Sessao.class, response.get(0).getClass());
        assertEquals(ID, response.get(0).getId());
        assertEquals(DATA_INICIO, response.get(0).getDataInicio());
        assertEquals(DATA_FIM_UM_MINUTO, response.get(0).getDataFim());
    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(repository.save(any(Sessao.class))).thenReturn(criarSessaoUmMinuto());
        when(pautaService.findById(anyLong())).thenReturn(criarPauta());

        Sessao response = service.save(criarSessaoDataFimNull(), ID);

        assertNotNull(response);

        assertEquals(Sessao.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(DATA_INICIO, response.getDataInicio());
        assertEquals(DATA_FIM_UM_MINUTO, response.getDataFim());
    }

    @Test
    void whenCreateThenReturnSuccessOnDateFinal() {
        when(repository.save(any(Sessao.class))).thenReturn(criarSessao());
        when(pautaService.findById(anyLong())).thenReturn(criarPauta());

        Sessao response = service.save(criarSessao(), ID_PAUTA);

        assertNotNull(response);

        assertEquals(Sessao.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(DATA_INICIO, response.getDataInicio());
        assertEquals(DATA_FIM_DEZ_MINUTOS, response.getDataFim());
    }

    @Test
    void whenFindAllThenReturnAnListOfAsSessaoAtivas() {
        when(repository.findAllSessoesAtivas(any())).thenReturn(List.of(criarSessaoUmMinuto()));

        List<Sessao> response = service.findAllAtivas();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Sessao.class, response.get(0).getClass());
        assertEquals(ID, response.get(0).getId());
        assertEquals(DATA_INICIO, response.get(0).getDataInicio());
        assertEquals(DATA_FIM_UM_MINUTO, response.get(0).getDataFim());
    }
}
