package db.desafiovotacao.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import db.desafiovotacao.dto.PautaAtualizarRequest;
import db.desafiovotacao.dto.PautaRequest;
import db.desafiovotacao.exceptions.NoContentException;
import db.desafiovotacao.exceptions.NotFoundException;
import db.desafiovotacao.model.Pauta;
import db.desafiovotacao.model.Sessao;
import db.desafiovotacao.service.PautaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PautaController.class)
class PautaControllerTest {

    @MockBean
    private PautaService pautaService;

    @Autowired
    private MockMvc mockMvc;


    @Mock
    private Pauta pauta;

    @Mock
    private Sessao sessao;

    @Mock
    private Page<Pauta> pautas;

    public static final String PATH = "/pauta";

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);

        pauta = new Pauta();
        sessao = new Sessao();

        sessao.setInicioSessao(LocalDateTime.now());
        sessao.setFinalSessao(sessao.getInicioSessao().plusMinutes(5));

        pauta.setId(1L);
        pauta.setTitulo("Pauta Teste");
        pauta.setDescricao("Teste");
        pauta.setDataCriacao(LocalDateTime.now());
        pauta.setSessao(sessao);
    }

    @Test
    void deveCadastrarPautaSucesso() throws Exception {

        Mockito.when(pautaService.cadastrarPauta(Mockito.any())).thenReturn(pauta);

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(new PautaRequest(pauta));

        this.mockMvc.perform(post(PATH)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void deveListarPautasSucesso() throws Exception {

        Mockito.when(pautaService.listarPautas(Mockito.any())).thenReturn(pautas);

        this.mockMvc.perform(get(PATH)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deveRetornarNoContentExceptionSeNaoExistiremPautas() throws Exception {

        Mockito.when(pautaService.listarPautas(Mockito.any())).thenThrow(NoContentException.class);

        this.mockMvc.perform(get(PATH)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void deveAtualizarPautaSucesso() throws Exception{

        Mockito.when(pautaService.atualizarPauta(Mockito.any())).thenReturn(pauta);

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(new PautaAtualizarRequest(pauta));

        this.mockMvc.perform(put(PATH)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deveRetornarNotFoundExceptionSePautaParaAtualizarNaoExistir() throws Exception{

        Mockito.when(pautaService.atualizarPauta(Mockito.any())).thenThrow(NotFoundException.class);

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(new PautaAtualizarRequest(pauta));

        this.mockMvc.perform(put(PATH)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveBuscarPautaSucesso() throws Exception{

        Mockito.when(pautaService.buscarPautaPorID(Mockito.any())).thenReturn(pauta);

        String id = "/" + pauta.getId();

        this.mockMvc.perform(get(PATH+id)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deveRetornarNotFoundExceptionSePautaNaoExistir() throws Exception{

        Mockito.when(pautaService.buscarPautaPorID(Mockito.any())).thenThrow(NotFoundException.class);

        String id = "/" + pauta.getId();

        this.mockMvc.perform(get(PATH+id)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveDeletarPautaSucesso() throws Exception{

        Mockito.when(pautaService.deletarPauta(Mockito.any())).thenReturn(pauta);

        String id = "/" + pauta.getId();

        this.mockMvc.perform(delete(PATH+id)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deveRetornarNotFoundExceptionSePautaNaoExistirParaDeletar() throws Exception{

        Mockito.when(pautaService.deletarPauta(Mockito.any())).thenThrow(NotFoundException.class);

        String id = "/" + pauta.getId();

        this.mockMvc.perform(delete(PATH+id)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}