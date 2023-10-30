package com.example.desafiovotacao.controllers;

import com.example.desafiovotacao.controller.VoteController;
import com.example.desafiovotacao.dto.ComputingVoteDTO;
import com.example.desafiovotacao.dto.VotedDTO;
import com.example.desafiovotacao.service.implementations.VoteService;
import com.example.desafiovotacao.utils.CpfUtils;
import com.example.desafiovotacao.utils.DateUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VoteControllerTest {

    @InjectMocks
    private VoteController voteController;

    @Mock
    private VoteService voteService;
    private ComputingVoteDTO computingVoteDTO;
    private VotedDTO votedDTO;
    private ResponseEntity<VotedDTO> responseVotedDTO;

    @BeforeEach
    void setup(){
        computingVoteDTO = new ComputingVoteDTO(CpfUtils.generateCPF(), true, 1);
        votedDTO = VotedDTO.builder()
                .voteId(1)
                .sessionId(1)
                .cpfAssociate(computingVoteDTO.getCpf())
                .computedVote("Sim")
                .sessionDate(DateUtils.formatDate(new Date()))
                .topic("Votação teste")
                .build();
        responseVotedDTO = ResponseEntity.status(HttpStatus.OK).body(votedDTO);
    }

    @Test
    void shouldComputeVote(){
        when(voteService.create(computingVoteDTO)).thenReturn(votedDTO);
        var response = assertDoesNotThrow(() -> voteController.compute(computingVoteDTO));
        assertNotNull(response);
        assertEquals(responseVotedDTO, response);
    }

}