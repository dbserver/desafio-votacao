package com.example.desafiovotacao.controllers;

import com.example.desafiovotacao.controller.SessionController;
import com.example.desafiovotacao.dto.CreatedSessionDTO;
import com.example.desafiovotacao.dto.SessionReturnDTO;
import com.example.desafiovotacao.dto.StartSessionDTO;
import com.example.desafiovotacao.service.implementations.SessionService;
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
public class SessionControllerTest {

    @InjectMocks
    private SessionController sessionController;

    @Mock
    private SessionService sessionService;
    private StartSessionDTO startSessionDTO;
    private CreatedSessionDTO createdSessionDTO;
    private ResponseEntity<CreatedSessionDTO> responseCreatedSession;
    private SessionReturnDTO sessionReturnDTO;
    private ResponseEntity<SessionReturnDTO> responseSessionReturn;
    private List<SessionReturnDTO> listSessionReturn;
    private ResponseEntity<List<SessionReturnDTO>> responseListSessionReturn;

    @BeforeEach
    void setup(){
        startSessionDTO = new StartSessionDTO(1, null);
        createdSessionDTO = new CreatedSessionDTO(1, DateUtils.formatDate(new Date()), startSessionDTO.getDuration());
        responseCreatedSession = ResponseEntity.status(HttpStatus.OK).body(createdSessionDTO);
        sessionReturnDTO = new SessionReturnDTO(1, "Votação teste", 1, 15, DateUtils.formatDate(new Date()));
        responseSessionReturn = ResponseEntity.status(HttpStatus.OK).body(sessionReturnDTO);
        listSessionReturn = new ArrayList<>();
        listSessionReturn.add(sessionReturnDTO);
        listSessionReturn.add(new SessionReturnDTO(1, "Votação teste", 1, 60, DateUtils.formatDate(new Date())));
        responseListSessionReturn = ResponseEntity.status(HttpStatus.OK).body(listSessionReturn);
    }

    @Test
    void shouldCreateSession(){
        when(sessionService.create(startSessionDTO)).thenReturn(createdSessionDTO);
        var response = assertDoesNotThrow(() -> sessionController.create(startSessionDTO));
        assertNotNull(response);
        assertEquals(responseCreatedSession, response);
    }

    @Test
    void shouldReturnSessionById(){
        when(sessionService.listById(createdSessionDTO.getSessionId())).thenReturn(sessionReturnDTO);
        var response = assertDoesNotThrow(() -> sessionController.findById(createdSessionDTO.getSessionId()));
        assertNotNull(response);
        assertEquals(responseSessionReturn, response);
    }

    @Test
    void shouldListSessions(){
        when(sessionService.listAll()).thenReturn(listSessionReturn);
        var response = assertDoesNotThrow(() -> sessionController.list());
        assertNotNull(response);
        assertEquals(responseListSessionReturn, response);
    }

}
