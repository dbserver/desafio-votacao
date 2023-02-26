package br.tec.db.votacao.controller;

import br.tec.db.votacao.dto.SessaoDeVotacaoDTO;
import br.tec.db.votacao.service.SessaoDeVotacaoService;
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
public class SessaoDeVotacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SessaoDeVotacaoService sessaoDeVotacaoService;

    @Test
    public void deveCriarUmaSessaoDeVotacaoEmUmaPauta() throws Exception {
        SessaoDeVotacaoDTO sessaoDeVotacaoDTO = new SessaoDeVotacaoDTO(LocalDateTime.now(), 1L);
        when(sessaoDeVotacaoService.criarSessaoDeVotacao(any(SessaoDeVotacaoDTO.class))).thenReturn(sessaoDeVotacaoDTO);

        mockMvc.perform(post("/sessao-de-votacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"inicio\": \"2023-06-01T10:00:00\", \"idPauta\": \"1\"}"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void deveRetornarBadRequestAoCriarSessaoDeVotacaoComDTONull() throws Exception {
        when(sessaoDeVotacaoService.criarSessaoDeVotacao(any(SessaoDeVotacaoDTO.class))).thenReturn(null);

        mockMvc.perform(post("/sessao-de-votacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"inicio\": \"null\", \"idPauta\": \"null\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deveBuscarTodasAsSessoesDeVotacao() throws Exception {
        List<SessaoDeVotacaoDTO> sessaoDeVotacaoDTO = new ArrayList<>();
        sessaoDeVotacaoDTO.add(new SessaoDeVotacaoDTO(LocalDateTime.now(), 1L));
        sessaoDeVotacaoDTO.add(new SessaoDeVotacaoDTO(LocalDateTime.now(), 2L));
        sessaoDeVotacaoDTO.add(new SessaoDeVotacaoDTO(LocalDateTime.now(), 3L));
        when(sessaoDeVotacaoService.buscarTodasAsSessoesDeVotacao()).thenReturn(sessaoDeVotacaoDTO);

        mockMvc.perform(get("/sessao-de-votacao")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void deveRetornarNotFoundAoBuscarTodasAsSessoesDeVotacaoVazio() throws Exception {
        List<SessaoDeVotacaoDTO> sessaoDeVotacaoDTO = new ArrayList<>();
        when(sessaoDeVotacaoService.buscarTodasAsSessoesDeVotacao()).thenReturn(sessaoDeVotacaoDTO);

        mockMvc.perform(get("/sessao-de-votacao")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deveBuscarSessaoDeVotacaoPorId() throws Exception {
        SessaoDeVotacaoDTO sessaoDeVotacaoDTO = new SessaoDeVotacaoDTO(LocalDateTime.now(), 3L);
        when(sessaoDeVotacaoService.buscarSessaoDeVotacaoPorId(1L)).thenReturn(sessaoDeVotacaoDTO);

        mockMvc.perform(get("/sessao-de-votacao/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deveRetornarNotFoundAoBuscarSessaoDeVotacaoPorIdInexistente() throws Exception {

        mockMvc.perform(get("/sessao-de-votacao/10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deveRetornarBadRequestAoBuscarSessaoDeVotacaoPorIdInvalido() throws Exception {

        mockMvc.perform(get("/sessao-de-votacao/abc")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deveBuscarSessaoDeVotacaoPorPauta() throws Exception {
        SessaoDeVotacaoDTO sessaoDeVotacaoDTO = new SessaoDeVotacaoDTO(LocalDateTime.now(), 1L);
        when(sessaoDeVotacaoService.buscarSessaoDeVotacaoPorPauta(1L)).thenReturn(sessaoDeVotacaoDTO);

        mockMvc.perform(get("/sessao-de-votacao/pauta/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deveRetornarNotFoundAoBuscarSessaoDeVotacaoPorPautaInexistente() throws Exception {

        mockMvc.perform(get("/sessao-de-votacao/pauta/10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deveRetornarBadRequestAoBuscarSessaoDeVotacaoPorPautaInvalido() throws Exception {

        mockMvc.perform(get("/sessao-de-votacao/pauta/abc")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deveFinalizarUmaSessaoDeVotacao() throws Exception {
        List<SessaoDeVotacaoDTO> sessao = new ArrayList<>();
        sessao.add(new SessaoDeVotacaoDTO(LocalDateTime.now(), 1L));
        when(sessaoDeVotacaoService.buscarTodasAsSessoesDeVotacao()).thenReturn(sessao);

        mockMvc.perform(put("/sessao-de-votacao/encerrar/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deveCalcularResultadoDaSessaoDeVotacao() throws Exception {
        List<SessaoDeVotacaoDTO> sessao = new ArrayList<>();
        sessao.add(new SessaoDeVotacaoDTO(LocalDateTime.now(), 1L));
        when(sessaoDeVotacaoService.buscarTodasAsSessoesDeVotacao()).thenReturn(sessao);

        mockMvc.perform(put("/sessao-de-votacao/resultado/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}



