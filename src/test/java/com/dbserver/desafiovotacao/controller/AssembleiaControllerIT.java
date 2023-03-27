
package com.dbserver.desafiovotacao.controller;

import com.dbserver.desafiovotacao.dto.AssembleiaRequest;
import com.dbserver.desafiovotacao.dto.AssembleiaResponse;
import com.dbserver.desafiovotacao.dto.ClienteRequest;
import com.dbserver.desafiovotacao.enums.PautaAndamentoEnum;
import com.dbserver.desafiovotacao.enums.VotoEnum;
import com.dbserver.desafiovotacao.model.Assembleia;
import com.dbserver.desafiovotacao.model.Pauta;
import com.dbserver.desafiovotacao.model.Votante;
import com.dbserver.desafiovotacao.service.AssembleiaServiceImplementacao;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testar AssembleiaController")
public class AssembleiaControllerIT {
    
    @Autowired
    MockMvc mockito;

    @MockBean
    private AssembleiaServiceImplementacao assembleiaService;

    AssembleiaRequest assembleiaRequest = new AssembleiaRequest("teste unitario");
    AssembleiaResponse assembleiaResponse;
    Assembleia assembleia;
    Pauta pauta = new Pauta();
    Votante votante = new Votante();
    Votante votanteAutor = new Votante();
    
    @BeforeEach
    public void setUp() {
        votante = Votante.builder().id(UUID.randomUUID()).idVotante("votante1").voto(VotoEnum.SIM).build();
        votanteAutor = Votante.builder().id(UUID.randomUUID()).idVotante("autoria1").voto(VotoEnum.AUTORIA).build();
        pauta = Pauta.builder().id(UUID.randomUUID()).titulo("Teste").descricao("Esse é um teste unitário").autorPauta(votanteAutor).hash("2h-23bh5").build();
        pauta.setAssociados(new ArrayList<>());
        pauta.getAssociados().add(votante);
        
        assembleia = Assembleia.builder().id(UUID.randomUUID()).nomeAssembleia(assembleiaRequest.nomeAssembleia()).build();
        assembleia.setListaPauta(new ArrayList<>());
        assembleia.getListaPauta().add(pauta);
        assembleiaResponse = new AssembleiaResponse(assembleia);
    }
    
    @Test
    @DisplayName("Teste de Criar uma assembleia")
    public void testCriarAssembleia() throws Exception {

        given(assembleiaService.salvarAssembleia(assembleiaRequest)).willReturn(assembleia);

        ObjectMapper mapper = new ObjectMapper();
        String novaAssembleia = mapper.writeValueAsString(assembleia);
        this.mockito.perform(post("/api")
                .content(novaAssembleia)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Teste de falhar ao criar uma assembleia")
    public void testCriarAssembleiaInvalida() throws Exception {
        AssembleiaRequest novaAssembleiaRequest = new AssembleiaRequest("");
        Assembleia novaAssembleia = Assembleia.builder().nomeAssembleia(novaAssembleiaRequest.nomeAssembleia()).build();
        given(assembleiaService.salvarAssembleia(novaAssembleiaRequest)).willReturn(novaAssembleia);

        ObjectMapper mapper = new ObjectMapper();
        String falha = mapper.writeValueAsString(novaAssembleiaRequest);
        this.mockito.perform(post("/api")
                        .content(falha)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Teste de adicionar uma nova pauta a assembleia")
    public void testaAdicionarPauta() throws Exception {
        Pauta novaPauta = Pauta.builder().id(UUID.randomUUID()).titulo("Nova Pauta").autorPauta(votanteAutor).hash("378763").build();
        ClienteRequest clienteRequest  = new ClienteRequest(novaPauta.getId());
        given(assembleiaService.adicionarPauta(assembleia.getId(),clienteRequest)).willReturn(assembleia);
        ObjectMapper mapper = new ObjectMapper();
        String encontrarAssembleiaJSON = mapper.writeValueAsString(clienteRequest);
        this.mockito.perform(post("/api/adicionapauta/" + assembleia.getId())
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(encontrarAssembleiaJSON))
                        .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Teste de finalizar uma Assembleia com Pauta em Movimento")
    public void testaFinalizaAssembleiaFalha() throws Exception {

        given(assembleiaService.finalizarAssembleia(assembleia.getId())).willReturn(assembleia);
        ObjectMapper mapper = new ObjectMapper();
        String encontrarAssembleiaJSON = mapper.writeValueAsString(assembleiaRequest);
        this.mockito.perform(get("/api/finalizarassembleia/" + assembleia.getId())
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(encontrarAssembleiaJSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Teste de finalizar uma Assembleia com Pauta finalizada")
    public void testaFinalizaAssembleiaSucesso() throws Exception {
        assembleia.getListaPauta().get(0).setAndamento(PautaAndamentoEnum.CONCLUIDO);
        given(assembleiaService.finalizarAssembleia(assembleia.getId())).willReturn(assembleia);
        ObjectMapper mapper = new ObjectMapper();
        String encontrarAssembleiaJSON = mapper.writeValueAsString(assembleiaRequest);
        this.mockito.perform(get("/api/finalizarassembleia/" + assembleia.getId())
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(encontrarAssembleiaJSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Teste de mostrar pautas")
    public void testaMostrarPautas() throws Exception {

        given(assembleiaService.mostraPautas(assembleia.getId())).willReturn(assembleia.getListaPauta());
        ObjectMapper mapper = new ObjectMapper();
        String encontrarAssembleiaJSON = mapper.writeValueAsString(assembleiaRequest);
        this.mockito.perform(get("/api/mostrarpautas/" + assembleia.getId())
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(encontrarAssembleiaJSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Teste de todas as assembleias")
    public void testaMostrarTodasAsAssembleias() throws Exception {
        List<AssembleiaResponse> listaAssembleias = Arrays.asList(assembleiaResponse);
        given(assembleiaService.mostrarAssembleias()).willReturn(listaAssembleias);
        ObjectMapper mapper = new ObjectMapper();
        String encontrarAssembleiaJSON = mapper.writeValueAsString(assembleiaRequest);
        this.mockito.perform(get("/api/todas")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(encontrarAssembleiaJSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Teste para exibir toda a assembleia")
    public void testaMostrarTudo() throws Exception {
        List<Assembleia> listaAssembleias = Arrays.asList(assembleia);
        given(assembleiaService.mostraTudo()).willReturn(listaAssembleias);
        ObjectMapper mapper = new ObjectMapper();
        String encontrarAssembleiaJSON = mapper.writeValueAsString(assembleia);
        this.mockito.perform(get("/api/tudo")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(encontrarAssembleiaJSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Teste de procurar uma assembleia existente")
    public void testProcuraAssembleia() throws Exception {

        given(assembleiaService.encontrarAssembleiaPorID(assembleia.getId())).willReturn(Optional.of(assembleia));
        ObjectMapper mapper = new ObjectMapper();
        String encontrarAssembleiaJSON = mapper.writeValueAsString(assembleiaResponse);
        this.mockito.perform(get("/api/" + assembleia.getId())
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(encontrarAssembleiaJSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Teste de procurar uma assembleia inexistente")
    public void testProcuraAssembleiaInexistente() throws Exception {
        UUID assembleiaNula = UUID.randomUUID();
        given(assembleiaService.encontrarAssembleiaPorID(assembleiaNula)).willReturn(Optional.empty());
        this.mockito.perform(get("/api/" + assembleiaNula)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Teste para retornar o total de pautas de uma assembleia")
    public void testTotalPautas() throws Exception {
        Integer result = assembleia.getListaPauta().size();
        given(assembleiaService.totalPautas(assembleia.getId())).willReturn(result);
        this.mockito.perform(get("/api/numeropautas/" + assembleia.getId())
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

    }

}
    

