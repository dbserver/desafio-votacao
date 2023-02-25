package br.tec.db.votacao.controller;

import br.tec.db.votacao.dto.AssembleiaDTO;
import br.tec.db.votacao.service.AssembleiaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AssembleiaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssembleiaService assembleiaService;

    @Test
    public void deveCriarAssembleia() throws Exception {
        AssembleiaDTO assembleiaDTO = new AssembleiaDTO(LocalDateTime.now());
        when(assembleiaService.criarAssembleia(any(AssembleiaDTO.class))).thenReturn(assembleiaDTO);

        mockMvc.perform(post("/assembleias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"inicio\": \"2023-09-01T10:00:00\"}"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void deveRetornarBadRequestAoCriarAssembleiaComDTONull() throws Exception {
        when(assembleiaService.criarAssembleia(any(AssembleiaDTO.class))).thenReturn(null);

        mockMvc.perform(post("/assembleias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deveBuscarTodasAssembleias() throws Exception {
        AssembleiaDTO assembleiaDTO = new AssembleiaDTO(LocalDateTime.now());
        when(assembleiaService.buscarTodasAssembleias()).thenReturn(List.of(assembleiaDTO));

        mockMvc.perform(get("/assembleias"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void deveRetornarNotFoundSeEstiverVazioAoBuscarTodasAssembleias() throws Exception {
        when(assembleiaService.buscarTodasAssembleias()).thenReturn(List.of());

        mockMvc.perform(get("/assembleias"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deveBuscarAssembleiaPorId() throws Exception {
        AssembleiaDTO assembleiaDTO = new AssembleiaDTO(LocalDateTime.now());
        when(assembleiaService.buscarAssembleiaPorId(any(Long.class))).thenReturn(assembleiaDTO);

        mockMvc.perform(get("/assembleias/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deveRetornarNotFoundAoBuscarAssembleiaPorIdInexistente() throws Exception {
        when(assembleiaService.buscarAssembleiaPorId(any(Long.class))).thenReturn(null);

        mockMvc.perform(get("/assembleias/85"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deveRetornarNotFoundAoBuscarAssembleiaPorIdNegativo() throws Exception {
        mockMvc.perform(get("/assembleias/-1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deveRetornarBadRequestAoBuscarAssembleiaPorIdInvalido() throws Exception {
        mockMvc.perform(get("/assembleias/abc"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deveFinalizarAssembleia() throws Exception {
        List<AssembleiaDTO> assembleias = new ArrayList<>();
        assembleias.add(new AssembleiaDTO(LocalDateTime.now()));
        when(assembleiaService.buscarTodasAssembleias()).thenReturn(assembleias);

        mockMvc.perform(put("/assembleias/1"))
                .andExpect(status().isOk());
    }

}
