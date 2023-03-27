/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.dbserver.desafiovotacao.service;

import com.dbserver.desafiovotacao.dto.*;
import com.dbserver.desafiovotacao.enums.PautaAndamentoEnum;
import com.dbserver.desafiovotacao.enums.PautaResultadoEnum;
import com.dbserver.desafiovotacao.enums.VotoEnum;
import com.dbserver.desafiovotacao.model.Pauta;
import com.dbserver.desafiovotacao.model.Votante;
import com.dbserver.desafiovotacao.repository.PautaRepositorio;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testar Pauta Service")
public class PautaServiceImplementacaoIT {
    
    @Mock
    private PautaRepositorio pautaRepositorio;
    @Mock
    private VotanteService votanteService;
    @InjectMocks
    PautaServiceImplementacao pautaService;
    Pauta pauta;
    Votante votante;
    Votante votanteAutor;
    PautaRequest pautaRequest;
    PautaResponse pautaResponse;
    
    @BeforeEach
    public void setUp() {
        votante = Votante.builder().id(UUID.randomUUID()).idVotante("votante1").voto(VotoEnum.SIM).build();
        votanteAutor = Votante.builder().id(UUID.randomUUID()).idVotante("autoria1").voto(VotoEnum.AUTORIA).build();
        pauta = Pauta.builder().id(UUID.randomUUID()).titulo("Teste").descricao("Esse é um teste unitário").autorPauta(votanteAutor).hash("2h-23bh5").build();
        pauta.setAssociados(new ArrayList<>());
        pauta.getAssociados().add(votante);
        pautaResponse = new PautaResponse(pauta);
        pautaRequest = new PautaRequest("Teste", votanteAutor.getId(), "2h-23bh5");

    }

    @Test
    @DisplayName("Testar encontrar pauta existente por ID")
    public void testEncontrarPautaPorIDSucesso() {
        given(pautaService.encontrarPautaPorID(pauta.getId())).willReturn(Optional.of(pauta));
		Optional<Pauta> resposta = pautaRepositorio.findById(pauta.getId());
		assertEquals(Optional.of(pauta), resposta);
    }
    
    @Test
    @DisplayName("Testar encontrar pauta inexistente por ID")
    public void testEncontrarPautaPorIDFalha() {
        UUID idRandom = UUID.randomUUID();
        given(pautaService.encontrarPautaPorID(idRandom)).willReturn(Optional.empty());
		Optional<Pauta> resposta = pautaRepositorio.findById(idRandom);
		assertEquals(Optional.empty(), resposta);
    }

    @Test
    @DisplayName("Testar encontrar pauta existente por hash")
    public void testEncontrarPautaPorHashSucesso() {
        given(pautaService.encontrarPautaPorHash(pauta.getHash())).willReturn(Optional.of(pauta));
		Optional<Pauta> resposta = pautaRepositorio.findByHash(pauta.getHash());
		assertEquals(pauta, resposta.get());
    }
    
    @Test
    @DisplayName("Testar encontrar pauta inexistente por hash")
    public void testEncontrarPautaPorHashFalha() {
        
        String hashTeste = "TesteDeHash";
        when(pautaRepositorio.findByHash(hashTeste)).thenReturn(Optional.of(pauta));
		Optional<Pauta> resposta = pautaRepositorio.findByHash(pauta.getHash());
		assertEquals(Optional.empty(), resposta);
    }

    @Test
    @DisplayName("Testar o salvamento de uma pauta")
    public void testSalvarPauta() {
        VotanteService votanteService = Mockito.mock(VotanteService.class);
        when(votanteService.encontrarVotantePorID(pautaRequest.idAutor())).thenReturn(Optional.of(votanteAutor));
        pautaService = new PautaServiceImplementacao(votanteService, pautaRepositorio);
        Pauta novaPauta = Pauta.builder().titulo(pautaRequest.titulo())
                .autorPauta(votanteAutor).hash(pautaRequest.hash())
                .andamento(PautaAndamentoEnum.APURANDO).build();
        when(pautaRepositorio.save(novaPauta)).thenReturn(novaPauta);
		pautaService.salvarPauta(pautaRequest);
		verify(pautaRepositorio, times(1)).save(novaPauta);
    }

    @Test
    @DisplayName("Teste de mostrar todas as pautas")
    public void testaMostrarPautas(){
        List<Pauta> listaPautas = Arrays.asList(pauta);
        given(pautaService.mostraPautas()).willReturn(listaPautas);
        assertEquals(1,listaPautas.size());
        assertEquals(pauta,listaPautas.get(0));

    }

    @Test
    @DisplayName("Teste para retornar o total de votantes em uma pauta")
    public void testTotalVotantes(){
        given(pautaService.encontrarPautaPorID(pauta.getId())).willReturn(Optional.of(pauta));
        Integer result = pautaService.totalVotantes(pauta.getId());
        assertEquals(1,result);
    }

    @Test
    @DisplayName("Teste para retornar o total de votantes de uma pauta vazia")
    public void testTotalVotantesVazio(){
        UUID idInvalido = UUID.randomUUID();
        given(pautaService.encontrarPautaPorID(idInvalido)).willReturn(Optional.empty());
        Integer result = pautaService.totalVotantes(idInvalido);
        assertEquals(0,result);
    }

    @Test
    @DisplayName("Teste de adicionar uma novo votante a pauta")
    public void testaAdicionarAssociado() {
        Votante novoVotante = Votante.builder().id(UUID.randomUUID()).idVotante("testeVot").voto(VotoEnum.SIM).build();
        ClienteRequest clienteRequest  = new ClienteRequest(novoVotante.getId());
        Pauta novaPauta = Pauta.builder().id(UUID.randomUUID()).titulo("Nova Pauta").autorPauta(votanteAutor).hash("378763").associados(new ArrayList<>()).build();
        given(pautaService.encontrarPautaPorHash(novaPauta.getHash())).willReturn(Optional.of(novaPauta));
        given(votanteService.encontrarVotantePorID(clienteRequest.id())).willReturn(Optional.of(novoVotante));
        pautaService.adicionarAssociado(novaPauta.getHash(),clienteRequest);
        assertEquals(1,novaPauta.getAssociados().size());
        assertEquals(novoVotante,novaPauta.getAssociados().get(0));
        verify(pautaRepositorio, times(1)).save(novaPauta);

    }

    @Test
    @DisplayName("Teste de adicionar um votante nulo a uma pauta")
    public void testaAdicionarAssociadoFalho() {
        UUID idInvalido = UUID.randomUUID();
        ClienteRequest clienteRequest  = new ClienteRequest(idInvalido);
        Pauta novaPauta = Pauta.builder().id(UUID.randomUUID()).titulo("Nova Pauta").autorPauta(votanteAutor).hash("378763").associados(new ArrayList<>()).build();
        given(pautaService.encontrarPautaPorHash(novaPauta.getHash())).willReturn(Optional.of(novaPauta));
        given(votanteService.encontrarVotantePorID(clienteRequest.id())).willReturn(Optional.empty());
        pautaService.adicionarAssociado(novaPauta.getHash(),clienteRequest);
        assertEquals(0,novaPauta.getAssociados().size());
        assertTrue(novaPauta.getAssociados().isEmpty());
        verify(pautaRepositorio, times(0)).save(novaPauta);

    }

    @Test
    @DisplayName("Teste de finalizar uma Pauta aprovada com sucesso")
    public void testaFinalizaPautaAprovada(){
        Pauta novaPauta = Pauta.builder().id(UUID.randomUUID()).titulo("Nova Pauta").autorPauta(votanteAutor).hash("378763").associados(new ArrayList<>()).build();
        novaPauta.getAssociados().add(new Votante(UUID.randomUUID(), "votante1", VotoEnum.SIM));
        novaPauta.getAssociados().add(new Votante(UUID.randomUUID(), "votante2", VotoEnum.SIM));
        novaPauta.getAssociados().add(new Votante(UUID.randomUUID(), "votante3", VotoEnum.NAO));
        given(pautaService.encontrarPautaPorHash(novaPauta.getHash())).willReturn(Optional.of(novaPauta));
        pautaService.finalizarPauta(novaPauta.getHash());
        assertTrue(novaPauta.getResultado().equals(PautaResultadoEnum.APROVADO));
        assertTrue(novaPauta.getAndamento().equals(PautaAndamentoEnum.CONCLUIDO));
    }

    @Test
    @DisplayName("Teste de finalizar uma Pauta indeferida")
    public void testaFinalizaPautaIndeferida(){
        Pauta novaPauta = Pauta.builder().id(UUID.randomUUID()).titulo("Nova Pauta").autorPauta(votanteAutor).hash("378763").associados(new ArrayList<>()).build();
        novaPauta.getAssociados().add(new Votante(UUID.randomUUID(), "votante1", VotoEnum.SIM));
        novaPauta.getAssociados().add(new Votante(UUID.randomUUID(), "votante2", VotoEnum.NAO));
        novaPauta.getAssociados().add(new Votante(UUID.randomUUID(), "votante3", VotoEnum.NAO));
        given(pautaService.encontrarPautaPorHash(novaPauta.getHash())).willReturn(Optional.of(novaPauta));
        pautaService.finalizarPauta(novaPauta.getHash());
        assertTrue(novaPauta.getResultado().equals(PautaResultadoEnum.INDEFERIDO));
        assertTrue(novaPauta.getAndamento().equals(PautaAndamentoEnum.CONCLUIDO));
    }

    @Test
    @DisplayName("Teste de finalizar uma Pauta sem votos")
    public void testaFinalizaPautaSemVotos(){
        Pauta novaPauta = Pauta.builder().id(UUID.randomUUID()).titulo("Nova Pauta").autorPauta(votanteAutor).hash("378763").associados(new ArrayList<>()).build();
        given(pautaService.encontrarPautaPorHash(novaPauta.getHash())).willReturn(Optional.of(novaPauta));
        pautaService.finalizarPauta(novaPauta.getHash());
        assertTrue(novaPauta.getAndamento().equals(PautaAndamentoEnum.APURANDO));
    }

    @Test
    @DisplayName("Teste de finalizar uma Pauta invalida")
    public void testaFinalizaPautaInvalida(){
        String hashInvalido = "Invalido";
        given(pautaService.encontrarPautaPorHash(hashInvalido)).willReturn(Optional.empty());
        Pauta resposta = pautaService.finalizarPauta(hashInvalido);
        assertTrue(resposta == null);
    }

    
}
