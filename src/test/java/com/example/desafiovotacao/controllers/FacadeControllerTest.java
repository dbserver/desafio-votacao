package com.example.desafiovotacao.controllers;

import com.example.desafiovotacao.controller.FacadeController;
import com.example.desafiovotacao.dto.FacadeDTO;
import com.example.desafiovotacao.facade.CpfFacade;
import com.example.desafiovotacao.utils.CpfUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FacadeControllerTest {

    @InjectMocks
    private FacadeController facadeController;

    @Mock
    private CpfFacade cpfFacade;
    private String cpfParam;
    private FacadeDTO facadeDTO;
    private ResponseEntity<FacadeDTO> responseFacadeDTO;

    @BeforeEach
    void setup(){
        cpfParam = CpfUtils.generateCPF();
        facadeDTO = new FacadeDTO("UNABLE_TO_VOTE");
        responseFacadeDTO = ResponseEntity.status(HttpStatus.OK).body(facadeDTO);
    }

    @Test
    void shouldValidateCPF(){
        when(cpfFacade.isCpfValid(cpfParam)).thenReturn(responseFacadeDTO);
        var response = assertDoesNotThrow(() -> facadeController.validateCpf(cpfParam));
        assertNotNull(response);
        assertEquals(responseFacadeDTO, response);
    }

}
