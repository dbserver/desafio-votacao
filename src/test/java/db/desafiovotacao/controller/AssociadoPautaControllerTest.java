package db.desafiovotacao.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import db.desafiovotacao.dto.AssociadoPautaRequest;

import db.desafiovotacao.exceptions.ConflictException;
import db.desafiovotacao.model.Associado;
import db.desafiovotacao.model.AssociadoPauta;
import db.desafiovotacao.model.Pauta;
import db.desafiovotacao.model.Sessao;
import db.desafiovotacao.service.AssociadoPautaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AssociadoPautaController.class)
class AssociadoPautaControllerTest {

    @MockBean
    private AssociadoPautaService associadoPautaService;

    @Autowired
    MockMvc mockMvc;

    @Mock
    Pauta pauta;

    @Mock
    AssociadoPauta associadoPauta;

    @Mock
    Associado associado;

    @Mock
    Sessao sessao;


    public static final String PATH = "/associado/pauta";

    @BeforeEach
    void init(){

        MockitoAnnotations.openMocks(this);

        associado = new Associado();
        pauta = new Pauta();
        sessao = new Sessao();
        associadoPauta = new AssociadoPauta();

        associado.setCPF("036.397.700-72");

        sessao.setInicioSessao(LocalDateTime.now());
        sessao.setFinalSessao(sessao.getInicioSessao().plusMinutes(5));

        pauta.setId(1L);
        pauta.setTitulo("Pauta Teste");
        pauta.setDescricao("Teste");
        pauta.setDataCriacao(LocalDateTime.now());
        pauta.setSessao(sessao);

        associadoPauta.setPauta(pauta);
        associadoPauta.setVotou(false);
        associadoPauta.setAssociado(associado);
    }

    @Test
    void deveCadastrarAssociadoNaPautaSucesso() throws Exception{

        Mockito.when(associadoPautaService.cadastarAssociadoNaPauta(Mockito.any())).thenReturn(associadoPauta);

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(new AssociadoPautaRequest(associadoPauta));

        this.mockMvc.perform(post(PATH)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void deveRetornarConflictExceptionAoTentarCadastrarAssociadoJaCadastrado() throws Exception{

        Mockito.when(associadoPautaService.cadastarAssociadoNaPauta(Mockito.any())).thenThrow(ConflictException.class);

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(new AssociadoPautaRequest(associadoPauta));

        this.mockMvc.perform(post(PATH)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }
}