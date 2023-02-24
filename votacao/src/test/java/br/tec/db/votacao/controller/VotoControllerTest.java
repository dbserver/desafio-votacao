package br.tec.db.votacao.controller;

import br.tec.db.votacao.dto.VotoDTO;
import br.tec.db.votacao.enums.VotoStatusEnum;
import br.tec.db.votacao.service.VotoService;
import org.junit.jupiter.api.Disabled;
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
class VotoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VotoService votoService;

    @Test
    public void deveCriarUmVotoEmUmaSessao() throws Exception {
        VotoDTO votoDTO = new VotoDTO(VotoStatusEnum.SIM, 1L, 1L);
        when(votoService.votar(any(VotoDTO.class))).thenReturn(votoDTO);

        mockMvc.perform(post("/votos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"voto\": \"SIM\", \"idSessaoDeVotacao\": \"1\", \"idAssociado\": \"1\"}"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    @Test
    public void deveRetornarVotoPorId() throws Exception {
        VotoDTO votoDTO = new VotoDTO(VotoStatusEnum.SIM, 1L, 1L);
        when(votoService.buscarVotoPorId(any(Long.class))).thenReturn(votoDTO);

        mockMvc.perform(get("/votos/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Disabled
    @Test
    public void deveRetornarNotFoundAoBuscarVotoPorIdInexistente() throws Exception {

        mockMvc.perform(get("/votos/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deveRetornarBadRequestAoBuscarVotoPorIdInvalido() throws Exception {

        mockMvc.perform(get("/votos/abc"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deveRetornarTodosOsVotos() throws Exception {
        List<VotoDTO> votos = new ArrayList<>();
        votos.add(new VotoDTO(VotoStatusEnum.SIM, 1L, 1L));
        votos.add(new VotoDTO(VotoStatusEnum.NAO, 2L, 2L));
        when(votoService.buscarTodosOsVotos()).thenReturn(votos);

        mockMvc.perform(get("/votos"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void deveRetornarNotFoundAoBuscarTodosOsVotosVazio() throws Exception {
        List<VotoDTO> votos = new ArrayList<>();
        when(votoService.buscarTodosOsVotos()).thenReturn(votos);

        mockMvc.perform(get("/votos"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deveBuscarVotosPorSessaoDeVotacao() throws Exception {
        List<VotoDTO> votos = new ArrayList<>();
        votos.add(new VotoDTO(VotoStatusEnum.SIM, 1L, 1L));
        votos.add(new VotoDTO(VotoStatusEnum.NAO, 1L, 2L));
        when(votoService.buscarVotosPorSessaoDeVotacao(any(Long.class))).thenReturn(votos);

        mockMvc.perform(get("/votos/sessao/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void deveRetornarNotFoundAoBuscarVotosPorSessaoDeVotacaoVazio() throws Exception {
        List<VotoDTO> votos = new ArrayList<>();
        when(votoService.buscarVotosPorSessaoDeVotacao(any(Long.class))).thenReturn(votos);

        mockMvc.perform(get("/votos/sessao/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deveRetornarBadRequestAoBuscarVotosPorSessaoDeVotacaoComIdInvalido() throws Exception {

        mockMvc.perform(get("/votos/sessao/abc"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deveRetornarNotFoundAoBuscarVotosPorSessaoDeVotacaoComIdInexistente() throws Exception {

        mockMvc.perform(get("/votos/sessao/99"))
                .andExpect(status().isNotFound());
    }

}