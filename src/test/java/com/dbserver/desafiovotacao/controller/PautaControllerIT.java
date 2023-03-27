
package com.dbserver.desafiovotacao.controller;

import com.dbserver.desafiovotacao.dto.ClienteRequest;
import com.dbserver.desafiovotacao.dto.PautaRequest;
import com.dbserver.desafiovotacao.dto.PautaResponse;
import com.dbserver.desafiovotacao.enums.PautaAndamentoEnum;
import com.dbserver.desafiovotacao.enums.PautaResultadoEnum;
import com.dbserver.desafiovotacao.enums.VotoEnum;
import com.dbserver.desafiovotacao.model.Assembleia;
import com.dbserver.desafiovotacao.model.Pauta;
import com.dbserver.desafiovotacao.model.Votante;
import com.dbserver.desafiovotacao.service.PautaServiceImplementacao;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testando o PautaController")
public class PautaControllerIT {
    
    @Autowired
    MockMvc mockito;

    @MockBean
    PautaServiceImplementacao pautaService;

    Pauta pauta = new Pauta();
    Votante votante = new Votante();
    Votante votanteAutor = new Votante();

    PautaResponse pautaResponse;
    
    @BeforeEach
    public void setUp() {
        votante = Votante.builder().id(UUID.randomUUID()).idVotante("votante1").voto(VotoEnum.SIM).build();
        votanteAutor = Votante.builder().id(UUID.randomUUID()).idVotante("autoria1").voto(VotoEnum.AUTORIA).build();
        pauta = Pauta.builder().id(UUID.randomUUID()).titulo("Teste").descricao("Esse é um teste unitário").autorPauta(votanteAutor).hash("2h-23bh5").build();
        pauta.setAssociados(new ArrayList<>());
        pauta.getAssociados().add(votante);
        pautaResponse = new PautaResponse(pauta);

    }
    @Test
    @DisplayName("Teste de Criar uma pauta")
    public void testCriarPauta() throws Exception {
        PautaRequest pautaRequest = new PautaRequest("Titulo Teste", votanteAutor.getId(), "newhash");
        given(pautaService.salvarPauta(pautaRequest)).willReturn(pauta);

        ObjectMapper mapper = new ObjectMapper();
        String novaPauta = mapper.writeValueAsString(pautaResponse);
        this.mockito.perform(post("/pauta")
                .content(novaPauta)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());
    }
    @Test
    @DisplayName("Teste verificar o total de votantes em uma pauta")
    public void testTotalPautas() throws Exception {
        Integer result = pauta.getAssociados().size();
        given(pautaService.totalVotantes(pauta.getId())).willReturn(result);
        this.mockito.perform(get("/pauta/numeroassociados/" + pauta.getId())
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("Teste para exibir todas as pautas")
    public void testaMostrarTodasPautas() throws Exception {
        List<Pauta> listaPauta = Arrays.asList(pauta);
        given(pautaService.mostraPautas()).willReturn(listaPauta);
        ObjectMapper mapper = new ObjectMapper();
        String encontrarPautaJSON = mapper.writeValueAsString(pautaResponse);
        this.mockito.perform(get("/pauta/mostrartodas")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(encontrarPautaJSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Teste de procurar uma pauta por id")
    public void testEncontrarPautaPorId() throws Exception {
        given(pautaService.encontrarPautaPorID(pauta.getId())).willReturn(Optional.of(pauta));

        ObjectMapper mapper = new ObjectMapper();
        String encontrarPautaJSON = mapper.writeValueAsString(pautaResponse);
        this.mockito.perform(get("/pauta/" + pauta.getId())
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(encontrarPautaJSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Teste de procurar uma pauta invalida")
    public void testEncontrarPautaInvalida() throws Exception {
        UUID idInvalido = UUID.randomUUID();
        given(pautaService.encontrarPautaPorID(idInvalido)).willReturn(Optional.empty());

        this.mockito.perform(get("/pauta/" + pauta.getId())
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Teste de mostrar votantes")
    public void testaMostrarVotantes() throws Exception {

        given(pautaService.encontrarPautaPorID(pauta.getId())).willReturn(Optional.of(pauta));
        ObjectMapper mapper = new ObjectMapper();
        String encontrarPautaJSON = mapper.writeValueAsString(pautaResponse);
        this.mockito.perform(get("/pauta/associados/" + pauta.getId())
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(encontrarPautaJSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Teste de mostrar votantes em pauta invalida")
    public void testaMostrarVotantesEmPautaInexistente() throws Exception {
        UUID idInvalido = UUID.randomUUID();
        given(pautaService.encontrarPautaPorID(idInvalido)).willReturn(Optional.empty());
        ObjectMapper mapper = new ObjectMapper();
        String encontrarPautaJSON = mapper.writeValueAsString(pautaResponse);
        this.mockito.perform(get("/pauta/associados/" + pauta.getId())
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(encontrarPautaJSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Teste de adicionar um votante a uma pauta")
    public void testaAdicionarVotante() throws Exception {
        Votante novoVotante = Votante.builder().id(UUID.randomUUID()).idVotante("db2023").voto(VotoEnum.SIM).build();
        ClienteRequest clienteRequest  = new ClienteRequest(novoVotante.getId());
        given(pautaService.adicionarAssociado(pauta.getHash(),clienteRequest)).willReturn(pauta);
        ObjectMapper mapper = new ObjectMapper();
        String encontrarPautaJSON = mapper.writeValueAsString(clienteRequest);
        this.mockito.perform(post("/pauta/adicionavotante/" + pauta.getHash())
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(encontrarPautaJSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Teste de finalizar uma Pauta com sucesso")
    public void testaFinalizaPautaSucesso() throws Exception {
        given(pautaService.encontrarPautaPorHash(pauta.getHash())).willReturn(Optional.of(pauta));
        this.mockito.perform(get("/pauta/finaliza/" + pauta.getHash())
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("Teste de finalizar uma Pauta com falha")
    public void testaFinalizaPautaFalha() throws Exception {
        String hashInvalido = "invalido";
        given(pautaService.finalizarPauta(hashInvalido)).willReturn(pauta);
        ObjectMapper mapper = new ObjectMapper();
        String encontrarPautaJSON = mapper.writeValueAsString(pautaResponse);
        this.mockito.perform(get("/pauta/finaliza/" + hashInvalido)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(encontrarPautaJSON))
                .andExpect(status().isNotFound());
    }
    
}
