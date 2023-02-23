package br.tec.db.votacao.controller;

import br.tec.db.votacao.dto.PautaDTO;
import br.tec.db.votacao.service.PautaService;
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
public class PautaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PautaService pautaService;

    @Test
    public void deveCriarUmaPautaEmUmaAssembleia() throws Exception {
        PautaDTO pautaDTO = new PautaDTO("pauta 1", 1L);
        when(pautaService.criarPauta(any(PautaDTO.class))).thenReturn(pautaDTO);

        mockMvc.perform(post("/pautas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"pauta 1\", \"idSessao\": \"1\"}"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

/*    @Test
    public void deveRetornarBadRequestAoCriarPautaEmAssembleiaInexistente() throws Exception {
        PautaDTO pautaDTO = new PautaDTO("pauta 1", 10L);
        when(pautaService.criarPauta(any(PautaDTO.class))).thenReturn(pautaDTO);

        mockMvc.perform(post("/pautas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"pauta 1\", \"idSessao\": \"10\"}"))
                .andExpect(status().isBadRequest());
    }*/

    @Test
    public void deveRetornarBadRequestAoCriarPautaComDTONull() throws Exception {
        when(pautaService.criarPauta(any(PautaDTO.class))).thenReturn(null);

        mockMvc.perform(post("/pautas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deveBuscarPautaPorId() throws Exception {
        PautaDTO pautaDTO = new PautaDTO("pauta 1", 1L);
        when(pautaService.buscarPautaPorId(any(Long.class))).thenReturn(pautaDTO);

        mockMvc.perform(get("/pautas/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deveRetornarNotFoundAoBuscarPautaPorIdInexistente() throws Exception {

        mockMvc.perform(get("/pautas/10"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deveRetornarNotFoundAoBuscarPautaPorIdNegativo() throws Exception {

        mockMvc.perform(get("/pautas/-1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deveRetornarBadRequestAoBuscarPautaPorIdInvalido() throws Exception {

        mockMvc.perform(get("/pautas/abc"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deveBuscarTodasAsPautas() throws Exception {
        List<PautaDTO> pautas = new ArrayList<>();
        pautas.add(new PautaDTO("pauta 1", 1L));
        pautas.add(new PautaDTO("pauta 2", 2L));
        when(pautaService.buscarTodasAsPautas()).thenReturn(pautas);

        mockMvc.perform(get("/pautas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void deveRetornarNotFoundAoBuscarTodasAsPautasVazio() throws Exception {
        List<PautaDTO> pautas = new ArrayList<>();
        when(pautaService.buscarTodasAsPautas()).thenReturn(pautas);

        mockMvc.perform(get("/pautas"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deveBuscarPautasPorAssembleia() throws Exception {
        List<PautaDTO> pautas = new ArrayList<>();
        pautas.add(new PautaDTO("pauta 1", 1L));
        pautas.add(new PautaDTO("pauta 2", 1L));
        when(pautaService.buscarPautasPorAssembleia(any(Long.class))).thenReturn(pautas);

        mockMvc.perform(get("/pautas/assembleia/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void deveRetornarNotFoundAoBuscarPautasPorAssembleiaVazio() throws Exception {
        List<PautaDTO> pautas = new ArrayList<>();
        when(pautaService.buscarPautasPorAssembleia(any(Long.class))).thenReturn(pautas);

        mockMvc.perform(get("/pautas/assembleia/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deveRetornarNotFoundAoBuscarPautasPorAssembleiaInexistente() throws Exception {
        mockMvc.perform(get("/pautas/assembleia/10"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deveRetornarBadRequestAoBuscarPautasPorAssembleiaInvalido() throws Exception {
        mockMvc.perform(get("/pautas/assembleia/abc"))
                .andExpect(status().isBadRequest());
    }

}