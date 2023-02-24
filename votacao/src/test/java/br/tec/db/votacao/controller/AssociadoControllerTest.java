package br.tec.db.votacao.controller;

import br.tec.db.votacao.dto.AssociadoDTO;
import br.tec.db.votacao.service.AssociadoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AssociadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssociadoService associadoService;

    @Test
    public void deveSalvarUmNovoAssociado() throws Exception {
        AssociadoDTO associadoDTO = new AssociadoDTO("12345678901", "João da Silva");
        when(associadoService.salvarAssociado(any(AssociadoDTO.class))).thenReturn(associadoDTO);

        mockMvc.perform(post("/associados")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"cpf\": \"12345678901\", \"nome\": \"João da Silva\"}"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void deveImpedirSalvarAssociadoComCPFInvalido() throws Exception {
        AssociadoDTO associadoDTO = new AssociadoDTO("abc", "João da Silva");
        when(associadoService.salvarAssociado(any(AssociadoDTO.class))).thenReturn(null);

        mockMvc.perform(post("/associados")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deveRetornarBadRequestAoSalvarAssociadoComDTONull() throws Exception {
        when(associadoService.salvarAssociado(any(AssociadoDTO.class))).thenReturn(null);

        mockMvc.perform(post("/associados")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void devebuscarAssociadoPorID() throws Exception {
        AssociadoDTO associadoDTO = new AssociadoDTO("12345678901", "João da Silva");
        when(associadoService.buscarAssociadoPorId(any(Long.class))).thenReturn(associadoDTO);

        mockMvc.perform(get("/associados/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deveRetornarNotFoundAoBuscarAssociadoPorIDInexistente() throws Exception {
        mockMvc.perform(get("/associados/99")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deveRetornarBadRequestAoBuscarAssociadoPorIdInvalido() throws Exception {

        mockMvc.perform(get("/associados/abc")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deveBuscarTodosOsAssociados() throws Exception {
        List<AssociadoDTO> associados = new ArrayList<>();
        associados.add(new AssociadoDTO("12345678901", "João da Silva"));
        associados.add(new AssociadoDTO("12345678902", "Maria da Silva"));
        when(associadoService.buscarTodosOsAssociados()).thenReturn(associados);

        mockMvc.perform(get("/associados")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void deveRetornarNotFoundAoBuscarTodosOsAssociadosVazio() throws Exception {
        List<AssociadoDTO> associados = new ArrayList<>();
        when(associadoService.buscarTodosOsAssociados()).thenReturn(associados);

        mockMvc.perform(get("/associados")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
