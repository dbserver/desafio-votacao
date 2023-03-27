
package com.dbserver.desafiovotacao.service;

import com.dbserver.desafiovotacao.dto.AssembleiaRequest;
import com.dbserver.desafiovotacao.dto.AssembleiaResponse;
import com.dbserver.desafiovotacao.dto.ClienteRequest;
import com.dbserver.desafiovotacao.enums.AssembleiaEnum;
import com.dbserver.desafiovotacao.enums.PautaAndamentoEnum;
import com.dbserver.desafiovotacao.enums.VotoEnum;
import com.dbserver.desafiovotacao.model.Assembleia;
import com.dbserver.desafiovotacao.model.Pauta;
import com.dbserver.desafiovotacao.model.Votante;
import com.dbserver.desafiovotacao.repository.AssembleiaRepositorio;

import java.time.LocalDateTime;
import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testar Assembleia Service")
public class AssembleiaServiceImplementacaoIT {
    
    @Mock
    private AssembleiaRepositorio assembleiaRepositorio;
    @Mock
    private PautaService pautaService;
    @InjectMocks
    private AssembleiaServiceImplementacao assembleiaService;
    Assembleia assembleia;
    Pauta pauta;
    Votante votante;
    Votante votanteAutor;

    AssembleiaRequest assembleiaRequest = new AssembleiaRequest("teste unitario");

    @BeforeEach
    public void setUp() {

        votante = Votante.builder().id(UUID.randomUUID()).idVotante("votante1").voto(VotoEnum.SIM).build();
        votanteAutor = Votante.builder().id(UUID.randomUUID()).idVotante("autoria1").voto(VotoEnum.AUTORIA).build();
        pauta = Pauta.builder().id(UUID.randomUUID()).titulo("Teste").descricao("Esse é um teste unitário").autorPauta(votanteAutor).hash("2h-23bh5").build();
        pauta.setAssociados(new ArrayList<>());
        pauta.getAssociados().add(votante);
        assembleia = Assembleia.builder().nomeAssembleia(assembleiaRequest.nomeAssembleia()).status(AssembleiaEnum.MOVIMENTO).listaPauta(new ArrayList<>()).build();
    }


    @Test
    @DisplayName("Teste para retornar uma assembleia valida")
    public void testEncontrarAssembleiaPorIDSucesso() {
        given(assembleiaService.encontrarAssembleiaPorID(assembleia.getId())).willReturn(Optional.of(assembleia));
		Optional<Assembleia> resposta = assembleiaService.encontrarAssembleiaPorID(assembleia.getId());
		assertEquals(assembleia, resposta.get());
    }
    
    @Test
    @DisplayName("Teste para retornar uma assembleia invalida")
    public void testEncontrarAssembleiaPorIDFalha() {
        UUID idRandom = UUID.randomUUID();
        given(assembleiaService.encontrarAssembleiaPorID(idRandom)).willReturn(Optional.empty());
		Optional<Assembleia> resposta = assembleiaService.encontrarAssembleiaPorID(idRandom);
		assertEquals(Optional.empty(), resposta);
    }

    @Test
    @DisplayName("Teste para salvar uma assembleia")
    public void testSalvarAssembleia() {
        when(assembleiaRepositorio.save(assembleia)).thenReturn(assembleia);
        assembleia.setAberturaAssembleia(LocalDateTime.now());
		assembleiaService.salvarAssembleia(assembleiaRequest);
		verify(assembleiaRepositorio, times(1)).save(assembleia);
    }

    @Test
    @DisplayName("Teste para retornar o total de pautas de uma assembleia")
    public void testTotalPautas(){
        assembleia.getListaPauta().add(pauta);
        given(assembleiaService.encontrarAssembleiaPorID(assembleia.getId())).willReturn(Optional.of(assembleia));
        Integer result = assembleiaService.totalPautas(assembleia.getId());
        assertEquals(1,result);
    }
    @Test
    @DisplayName("Teste para retornar o total de pautas de uma assembleia vazia")
    public void testTotalPautasVazia(){
        UUID idInvalido = UUID.randomUUID();
        given(assembleiaService.encontrarAssembleiaPorID(idInvalido)).willReturn(Optional.empty());
        Integer result = assembleiaService.totalPautas(idInvalido);
        assertEquals(0,result);
    }

    @Test
    @DisplayName("Teste para mostrar tudo")
    public void testaMostrarTudo(){
        List<Assembleia> listaAssembleias = Arrays.asList(assembleia);
        given(assembleiaRepositorio.findAll()).willReturn(listaAssembleias);
        Iterable<Assembleia> resposta = assembleiaService.mostraTudo();
        assertEquals(listaAssembleias, resposta);
    }

    @Test
    @DisplayName("Teste de mostrar todas as pautas")
    public void testaMostrarPautas(){
        given(assembleiaService.encontrarAssembleiaPorID(assembleia.getId())).willReturn(Optional.of(assembleia));
        List<Pauta> listaPauta = assembleia.getListaPauta();
        Iterable<Pauta> resposta = assembleiaService.mostraPautas(assembleia.getId());
        assertEquals(listaPauta, resposta);

    }

    @Test
    @DisplayName("Teste de adicionar uma nova pauta a assembleia")
    public void testaAdicionarPauta() {
        Pauta novaPauta = Pauta.builder().id(UUID.randomUUID()).titulo("Nova Pauta").autorPauta(votanteAutor).hash("378763").build();
        ClienteRequest clienteRequest  = new ClienteRequest(novaPauta.getId());
        Assembleia novaAssembleia = Assembleia.builder().id(UUID.randomUUID()).listaPauta(new ArrayList<>()).build();
        given(assembleiaService.encontrarAssembleiaPorID(novaAssembleia.getId())).willReturn(Optional.of(novaAssembleia));
        given(pautaService.encontrarPautaPorID(clienteRequest.id())).willReturn(Optional.of(novaPauta));
        assembleiaService.adicionarPauta(novaAssembleia.getId(),clienteRequest);
        assertEquals(1,novaAssembleia.getListaPauta().size());
        assertEquals(novaPauta,novaAssembleia.getListaPauta().get(0));
        verify(assembleiaRepositorio, times(1)).save(novaAssembleia);

    }

    @Test
    @DisplayName("Teste de adicionar uma nova pauta nula")
    public void testaAdicionarPautaFalha() {
        UUID idInvalido = UUID.randomUUID();
        ClienteRequest clienteRequest  = new ClienteRequest(idInvalido);
        Assembleia novaAssembleia = Assembleia.builder().id(UUID.randomUUID()).listaPauta(new ArrayList<>()).build();
        given(assembleiaService.encontrarAssembleiaPorID(novaAssembleia.getId())).willReturn(Optional.of(novaAssembleia));
        given(pautaService.encontrarPautaPorID(clienteRequest.id())).willReturn(Optional.empty());
        assembleiaService.adicionarPauta(novaAssembleia.getId(),clienteRequest);
        assertEquals(0,novaAssembleia.getListaPauta().size());
        assertTrue(novaAssembleia.getListaPauta().isEmpty());
        verify(assembleiaRepositorio, times(0)).save(novaAssembleia);

    }

    @Test
    @DisplayName("Teste de todas as assembleias")
    public void testaMostrarTodasAsAssembleias(){
        AssembleiaResponse assembleiaResponse = new AssembleiaResponse(assembleia);
        List<AssembleiaResponse> listaAssembleias = Arrays.asList(assembleiaResponse);
        given(assembleiaService.mostrarAssembleias()).willReturn(listaAssembleias);
        assertEquals(1,listaAssembleias.size());
        assertEquals(assembleiaResponse,listaAssembleias.get(0));
    }

    @Test
    @DisplayName("Teste de finalizar uma Assembleia com Pauta finalizada")
    public void testaFinalizaAssembleiaSucesso(){
        Pauta novaPauta = Pauta.builder().id(UUID.randomUUID()).titulo("Nova Pauta").autorPauta(votanteAutor).hash("378763").andamento(PautaAndamentoEnum.CONCLUIDO).build();
        Assembleia novaAssembleia = Assembleia.builder().id(UUID.randomUUID()).nomeAssembleia("Nova Assembleia")
                .aberturaAssembleia(LocalDateTime.now()).listaPauta(new ArrayList<>()).status(AssembleiaEnum.MOVIMENTO).build();
        novaAssembleia.getListaPauta().add(novaPauta);
        given(assembleiaService.encontrarAssembleiaPorID(novaAssembleia.getId())).willReturn(Optional.of(novaAssembleia));
        assembleiaService.finalizarAssembleia(novaAssembleia.getId());
        assertTrue(novaAssembleia.getStatus().equals(AssembleiaEnum.FINALIZADO));
    }

    @Test
    @DisplayName("Teste de finalizar uma Assembleia com Pauta em andamento")
    public void testaFinalizaAssembleiaFalha(){
        Pauta novaPauta = Pauta.builder().id(UUID.randomUUID()).titulo("Nova Pauta").autorPauta(votanteAutor).hash("378763").andamento(PautaAndamentoEnum.APURANDO).build();
        Assembleia novaAssembleia = Assembleia.builder().id(UUID.randomUUID()).nomeAssembleia("Nova Assembleia")
                .aberturaAssembleia(LocalDateTime.now()).listaPauta(new ArrayList<>()).status(AssembleiaEnum.MOVIMENTO).build();
        novaAssembleia.getListaPauta().add(novaPauta);
        given(assembleiaService.encontrarAssembleiaPorID(novaAssembleia.getId())).willReturn(Optional.of(novaAssembleia));
        assembleiaService.finalizarAssembleia(novaAssembleia.getId());
        assertTrue(novaAssembleia.getStatus().equals(AssembleiaEnum.MOVIMENTO));
    }

    @Test
    @DisplayName("Teste de finalizar uma Assembleia Inexistente")
    public void testaFinalizaAssembleiaInexistente(){
        UUID idInvalido = UUID.randomUUID();
        given(assembleiaService.encontrarAssembleiaPorID(idInvalido)).willReturn(Optional.empty());
        Assembleia resposta = assembleiaService.finalizarAssembleia(idInvalido);
        assertTrue(resposta == null);
    }
    
}
