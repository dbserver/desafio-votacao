/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.dbserver.desafiovotacao.service;

import com.dbserver.desafiovotacao.dto.VotanteRequest;
import com.dbserver.desafiovotacao.enums.VotoEnum;
import com.dbserver.desafiovotacao.model.Votante;
import com.dbserver.desafiovotacao.repository.VotanteRepositorio;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testar Votante Service")
public class VotanteServiceImplementacaoIT {

    @Mock
    private VotanteRepositorio votanteRepositorio;

    @InjectMocks
    VotanteServiceImplementacao votanteService;

    Votante votante;

    VotanteRequest votanteRequest = new VotanteRequest("codTeste", VotoEnum.NAO);

    @BeforeEach
    public void setUp() {
        votante = Votante.builder().idVotante("codTeste").voto(VotoEnum.NAO).build();
    }

    @Test
    public void testFindByIdSucesso() {
        when(votanteRepositorio.findById(votante.getId())).thenReturn(Optional.of(votante));
        Optional<Votante> retorno = votanteService.encontrarVotantePorID(votante.getId());
        assertEquals(Optional.of(votante), retorno);
    }

    @Test
    public void testFindByIdFalho() {
        UUID idRandom = UUID.randomUUID();
        when(votanteRepositorio.findById(idRandom)).thenReturn(Optional.of(votante));
        Optional<Votante> retorno = votanteService.encontrarVotantePorID(votante.getId());
        assertEquals(Optional.empty(), retorno);
    }

    @Test
    public void testFindAll() {
        List<Votante> listaAssociados = new ArrayList<>();
        listaAssociados.add(votante);
        when(votanteRepositorio.findAll()).thenReturn(listaAssociados);
        Iterable<Votante> resultado = votanteService.findAll();
        assertEquals(listaAssociados, resultado);
    }

    @Test
    public void testSalvarVotante() {
        when(votanteRepositorio.save(votante)).thenReturn(votante);
		votanteService.salvarVotante(votanteRequest);
		verify(votanteRepositorio, times(1)).save(votante);
    }

    @Test
    @DisplayName("Teste de mostrar o total de votantes")
    public void testaTotalVotantes() {
        List<Votante> listaVotantes = Arrays.asList(votante);
        given(votanteService.totalVotantes()).willReturn((long) listaVotantes.size());
        assertEquals(1,listaVotantes.size());
        assertEquals(votante,listaVotantes.get(0));
    }

}
