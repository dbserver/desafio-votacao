package db.desafiovotacao.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import db.desafiovotacao.dto.AssociadoRequest;
import db.desafiovotacao.exceptions.ConflictException;
import db.desafiovotacao.model.Associado;
import db.desafiovotacao.service.AssociadoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AssociadoController.class)
class AssociadoControllerTest{

    @MockBean
    private AssociadoService associadoService;

    @Autowired
    MockMvc mockMvc;

    public static final String PATH = "/associado";

    @BeforeEach
    void init(){

    }

    @Test
    void deveCadastrarAssociadoSucesso() throws Exception {

        Associado associado = new Associado();

        associado.setCPF("036.397.700-72");

        Mockito.when(associadoService.criarAssociado(Mockito.any())).thenReturn(associado);

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(new AssociadoRequest("036.397.700-72"));

        this.mockMvc.perform(post(PATH)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void deveRetornarConflictExceptionAoTentarCadastrarAssociadoJaCadastrado() throws Exception {

        Associado associado = new Associado();

        associado.setCPF("036.397.700-72");

        Mockito.when(associadoService.criarAssociado(Mockito.any())).thenThrow(ConflictException.class);

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(new AssociadoRequest("036.397.700-72"));

        this.mockMvc.perform(post(PATH)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }
}