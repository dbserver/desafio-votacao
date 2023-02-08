package br.com.adonias.desafiovotacao.controllers;

import br.com.adonias.desafiovotacao.entities.Session;
import br.com.adonias.desafiovotacao.repositories.ISessionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class SessionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ISessionRepository repository;

    @Test
    void testGetAllSessions_with_EmptyList() throws Exception {
        List<Session> emptyList = new ArrayList<Session>();
        Mockito.when(repository.findAll()).thenReturn(emptyList);

        this.mockMvc.perform(get("/sessions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

        Mockito.verify(repository).findAll();
    }

    @Test
    void testGetSessionByIdWithSuccess() throws Exception {
        Session session = Session.builder().agenda_id(1l)
                .startDate(LocalDateTime.of(2023,1,1,0,0,0))
                .endDate(LocalDateTime.of(2023,01,31,23,59,59)).build();

        Mockito.when(repository.findById(1l)).thenReturn(Optional.ofNullable(session));

        this.mockMvc.perform(get("/sessions/{id}",1))
                .andExpect(status().isOk())
                .andExpect(content().json("{ \"agenda_id\": 1, \"endDate\": \"31/01/2023 23:59:59\", \"startDate\": \"01/01/2023 00:00:00\" }"));
    }

    @Test
    void testCreateSessionWithSuccess() throws Exception {
        Session session = new Session(3L,
                LocalDateTime.of(2023,1,1,0,0,0),
                LocalDateTime.of(2023,01,31,23,59,59));


        Mockito.when(repository.existsById(3l)).thenReturn(false);
        Mockito.when(repository.save(session)).thenReturn(session);

        this.mockMvc.perform(post("/sessions")
                .contentType("application/json")
                .content("{ \"agenda_id\": 3, \"endDate\": \"31/01/2023 23:59:59\", \"startDate\": \"01/01/2023 00:00:00\" }"))
                .andExpect(status().isCreated());
    }

    @Test
    void testCreateSessionWithErrorSessionAlreadyExists() throws Exception {
        Mockito.when(repository.existsById(null)).thenReturn(false);

        this.mockMvc.perform(post("/sessions")
                        .contentType("application/json")
                        .content("{ \"agenda_id\": null, \"endDate\": \"31/01/2023 23:59:59\", \"startDate\": \"01/01/2023 00:00:00\" }"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testDelete() throws Exception {
        Mockito.doNothing().when(repository).deleteById(1L);
        Mockito.when(repository.existsById(1L)).thenReturn(true);

        this.mockMvc.perform(delete("/sessions/{id}", 1))
                .andExpect(status().isOk());
        Mockito.verify(repository).deleteById(1L);
    }

    @Test
    void testUpdateSessionWithSuccess() throws Exception {

        Session session = new Session(3L,
                LocalDateTime.of(2023,1,1,0,0,0),
                LocalDateTime.of(2023,01,31,23,59,59));


        Mockito.when(repository.existsById(3l)).thenReturn(true);
        Mockito.when(repository.save(session)).thenReturn(session);

        this.mockMvc.perform(put("/sessions")
                        .contentType("application/json")
                        .content("{ \"agenda_id\": 3, \"endDate\": \"31/01/2023 23:59:59\", \"startDate\": \"01/01/2023 00:00:00\" }"))
                .andExpect(content().json("{ \"agenda_id\": 3, \"endDate\": \"31/01/2023 23:59:59\", \"startDate\": \"01/01/2023 00:00:00\" }"))
                .andExpect(status().isOk());
    }
    @Test
    void testUpdateSessionThatDoesNotExist() throws Exception {

        Mockito.when(repository.existsById(3l)).thenReturn(false);

        this.mockMvc.perform(put("/sessions")
                        .contentType("application/json")
                        .content("{ \"agenda_id\": 3, \"endDate\": \"31/01/2023 23:59:59\", \"startDate\": \"01/01/2023 00:00:00\" }"))
                .andExpect(status().isBadRequest());
    }
}
