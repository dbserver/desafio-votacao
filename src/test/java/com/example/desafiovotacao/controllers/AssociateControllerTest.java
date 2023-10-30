package com.example.desafiovotacao.controllers;

import com.example.desafiovotacao.controller.AssociateController;
import com.example.desafiovotacao.dto.CreatedAssociateDTO;
import com.example.desafiovotacao.dto.RegisterAssociateDTO;
import com.example.desafiovotacao.service.implementations.AssociateService;
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
public class AssociateControllerTest {

    @InjectMocks
    private AssociateController associateController;

    @Mock
    private AssociateService associateService;
    private RegisterAssociateDTO associate;
    private CreatedAssociateDTO createdAssociate;
    private ResponseEntity<CreatedAssociateDTO> responseCreateAssociate;

    @BeforeEach
    void setup() {
        associate = new RegisterAssociateDTO(CpfUtils.generateCPF(), "Usuário Teste");
        createdAssociate = new CreatedAssociateDTO(1, associate.getCpf());
        responseCreateAssociate = ResponseEntity.status(HttpStatus.OK).body(createdAssociate);
    }

    @Test
    void shouldSaveAssociate(){
        when(associateService.create(associate)).thenReturn(createdAssociate);
        var response = assertDoesNotThrow(() -> associateController.create(associate));
        assertNotNull(response);
        assertEquals(responseCreateAssociate, response);
    }

}
