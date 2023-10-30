package com.example.desafiovotacao.controllers;

import com.example.desafiovotacao.controller.RulingController;
import com.example.desafiovotacao.dto.CountingResultsDTO;
import com.example.desafiovotacao.dto.CreatedRulingDTO;
import com.example.desafiovotacao.dto.RegisterRulingDTO;
import com.example.desafiovotacao.dto.RulingReturnDTO;
import com.example.desafiovotacao.service.implementations.RulingService;
import com.example.desafiovotacao.utils.DateUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RulingControllerTest {

    @InjectMocks
    private RulingController rulingController;

    @Mock
    private RulingService rulingService;
    private RegisterRulingDTO registerRulingDTO;
    private CreatedRulingDTO createdRulingDTO;
    private ResponseEntity<CreatedRulingDTO> responseCreateRuling;
    private CountingResultsDTO countingResultsDTO;
    private ResponseEntity<CountingResultsDTO> responseCountingResults;
    private RulingReturnDTO rulingReturnDTO;
    private ResponseEntity<RulingReturnDTO> responseRulingDTO;
    private List<RulingReturnDTO> rulingReturnList;
    private ResponseEntity<List<RulingReturnDTO>> responseRulingList;


    @BeforeEach
    void setup(){
        registerRulingDTO = new RegisterRulingDTO("Votação teste", "Descrição teste");
        createdRulingDTO = new CreatedRulingDTO(1, "Votação teste", "Descrição teste");
        responseCreateRuling = ResponseEntity.status(HttpStatus.OK).body(createdRulingDTO);
        countingResultsDTO = CountingResultsDTO.builder()
                .sessionDate(DateUtils.formatDate(new Date()))
                .creationDate(DateUtils.formatDate(new Date()))
                .countDate(DateUtils.formatDate(new Date()))
                .result("Sim")
                .againstVotes(1L)
                .inFavorVotes(2L)
                .title("Votação teste")
                .description("Descrição teste")
                .build();
        responseCountingResults = ResponseEntity.status(HttpStatus.OK).body(countingResultsDTO);
        rulingReturnDTO = new RulingReturnDTO(1, "Votação Teste", "Descrição teste", "Sim", DateUtils.formatDate(new Date()), DateUtils.formatDate(new Date()));
        responseRulingDTO = ResponseEntity.status(HttpStatus.OK).body(rulingReturnDTO);
        rulingReturnList = new ArrayList<>();
        rulingReturnList.add(rulingReturnDTO);
        rulingReturnList.add(new RulingReturnDTO(2, "Votação Teste", "Descrição teste", "Não", DateUtils.formatDate(new Date()), DateUtils.formatDate(new Date())));
        responseRulingList = ResponseEntity.status(HttpStatus.OK).body(rulingReturnList);
    }

    @Test
    void shouldCreateAssociate(){
        when(rulingService.create(registerRulingDTO)).thenReturn(createdRulingDTO);
        var response = assertDoesNotThrow(() -> rulingController.save(registerRulingDTO));
        assertNotNull(response);
        assertEquals(responseCreateRuling, response);
    }

    @Test
    void shouldCountVotes(){
        when(rulingService.countVotes(createdRulingDTO.getRulingId())).thenReturn(countingResultsDTO);
        var response = assertDoesNotThrow(() -> rulingController.countVotes(createdRulingDTO.getRulingId()));
        assertNotNull(response);
        assertEquals(responseCountingResults, response);
    }

    @Test
    void shouldReturnRulingList(){
        when(rulingService.listAll()).thenReturn(rulingReturnList);
        var response = assertDoesNotThrow(() -> rulingController.list());
        assertNotNull(response);
        assertEquals(responseRulingList, response);
    }

    @Test
    void shouldReturnRuling(){
        when(rulingService.getRulingReturnIfExists(createdRulingDTO.getRulingId())).thenReturn(rulingReturnDTO);
        var response = assertDoesNotThrow(() -> rulingController.listById(createdRulingDTO.getRulingId()));
        assertNotNull(response);
        assertEquals(responseRulingDTO, response);
    }

}
