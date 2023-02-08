package br.com.adonias.desafiovotacao.controllers;

import br.com.adonias.desafiovotacao.entities.Session;
import br.com.adonias.desafiovotacao.entities.Vote;
import br.com.adonias.desafiovotacao.entities.enums.Answer;
import br.com.adonias.desafiovotacao.repositories.ISessionRepository;
import br.com.adonias.desafiovotacao.repositories.IVoteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class VoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IVoteRepository voteRepository;

    @MockBean
    private ISessionRepository sessionRepository;

    @Test
    void getAllVotes_with_EmptyList() throws Exception {
        List<Vote> emptyList = new ArrayList<Vote>();
        Mockito.when(voteRepository.findAll()).thenReturn(emptyList);

        this.mockMvc.perform(get("/votes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

        Mockito.verify(voteRepository).findAll();
    }

    @Test
    void getAllVotesWithSuccess() throws Exception {
        List<Vote> list = Arrays.asList(Vote.builder().id(1L).agendaId(1L).answer(Answer.YES).cpfAssociate("123").build());
        Mockito.when(voteRepository.findAll()).thenReturn(list);

        this.mockMvc.perform(get("/votes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

        Mockito.verify(voteRepository).findAll();
    }

    @Test
    void getVoteByIdWithSuccess() throws Exception {
        Vote vote = Vote.builder().id(1L).agendaId(1L).answer(Answer.YES).cpfAssociate("123").dateTime(LocalDateTime.of(2023,01,01,12,0,0)).build();



        Mockito.when(voteRepository.findById(1L)).thenReturn(Optional.ofNullable(vote));

        this.mockMvc.perform(get("/votes/{id}",1))
                .andExpect(status().isOk())
                .andExpect(content().json("{ \"agendaId\": 1, \"answer\": \"YES\", \"cpfAssociate\": \"123\", \"dateTime\": \"01/01/2023 12:00:00\", \"id\": 1 }"));
        Mockito.verify(voteRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    void getVotesByAgendaId() throws Exception {
        List<Vote> list =  Arrays.asList(Vote.builder().id(1L).agendaId(1L).answer(Answer.YES).cpfAssociate("123").build());

        Mockito.when(voteRepository.findAllByAgendaId(1L)).thenReturn(list);

        this.mockMvc.perform(get("/votes/agenda/{id}",1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

        Mockito.verify(voteRepository, Mockito.times(1)).findAllByAgendaId(1L);

    }

    @Test
    void testCreateWithSuccess() throws Exception {
        Vote vote = Vote.builder().id(1L).agendaId(1L).answer(Answer.YES).cpfAssociate("123").dateTime(LocalDateTime.of(2023,01,01,12,0,0)).build();
        Session session = new Session(3L,
                LocalDateTime.of(2023,1,1,0,0,0),
                LocalDateTime.of(2023,01,31,23,59,59));

        Mockito.when(sessionRepository.findById(1L)).thenReturn(Optional.of(session));
        Mockito.when(voteRepository.existsVoteByAgendaIdAndCpfAssociate(1L,"123")).thenReturn(false);
        Mockito.when(voteRepository.save(vote)).thenReturn(vote);

        this.mockMvc.perform(post("/votes")
                        .contentType("application/json")
                        .content("{ \"agendaId\": 1, \"answer\": \"YES\", \"cpfAssociate\": \"123\", \"dateTime\": \"01/01/2023 12:00:00\", \"id\": 1 }"))
                .andExpect(status().isCreated());
    }

    @Test
    void testCreateWithErrorPostSessionClosed() throws Exception {
        Vote vote = Vote.builder().id(1L).agendaId(1L).answer(Answer.YES).cpfAssociate("123").dateTime(LocalDateTime.of(2023,01,15,12,0,0)).build();
        Session session = new Session(3L,
                LocalDateTime.of(2023,1,1,0,0,0),
                LocalDateTime.of(2023,01,12,23,59,59));

        Mockito.when(sessionRepository.findById(1L)).thenReturn(Optional.of(session));

        this.mockMvc.perform(post("/votes")
                        .contentType("application/json")
                        .content("{ \"agendaId\": 1, \"answer\": \"YES\", \"cpfAssociate\": \"123\", \"dateTime\": \"15/01/2023 12:00:00\", \"id\": 1 }"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testDelete() throws Exception {

        Mockito.doNothing().when(voteRepository).deleteById(1L);
        Mockito.when(voteRepository.existsById(1L)).thenReturn(true);

        this.mockMvc.perform(delete("/votes/{id}", 1))
                .andExpect(status().isOk());

        Mockito.verify(voteRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    void testUpdateWithSuccess() throws Exception {
        Vote vote = Vote.builder().id(1L).agendaId(1L).answer(Answer.YES).cpfAssociate("123").dateTime(LocalDateTime.of(2023,01,01,12,0,0)).build();
        Session session = new Session(3L,
                LocalDateTime.of(2023,1,1,0,0,0),
                LocalDateTime.of(2023,01,31,23,59,59));

        Mockito.when(sessionRepository.findById(1L)).thenReturn(Optional.of(session));
        Mockito.when(voteRepository.save(vote)).thenReturn(vote);

        this.mockMvc.perform(put("/votes")
                        .contentType("application/json")
                        .content("{ \"agendaId\": 1, \"answer\": \"YES\", \"cpfAssociate\": \"123\", \"dateTime\": \"01/01/2023 12:00:00\", \"id\": 1 }"))
                .andExpect(content().json("{ \"agendaId\": 1, \"answer\": \"YES\", \"cpfAssociate\": \"123\", \"dateTime\": \"01/01/2023 12:00:00\", \"id\": 1 }"))
                .andExpect(status().isOk());

        Mockito.verify(voteRepository, Mockito.times(1)).save(vote);
    }
}
