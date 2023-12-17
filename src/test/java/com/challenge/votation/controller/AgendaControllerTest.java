package com.challenge.votation.controller;

import com.challenge.votation.model.*;
import com.challenge.votation.service.AgendaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import static org.mockito.Mockito.when;

@WebMvcTest(AgendaController.class)
class AgendaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AgendaService agendaService;

    @Test
    void createAgenda_shouldReturnCreatedStatus() throws Exception {
        String agendaName = "Approve Gustavo as our new employee";
        AgendaCreateRequest agendaCreateRequest = new AgendaCreateRequest(agendaName);
        AgendaCreateResponse agendaCreateResponse = new AgendaCreateResponse(1L, agendaName);
        when(agendaService.createAgenda(Mockito.any(AgendaCreateRequest.class)))
                .thenReturn(agendaCreateResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/agendas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(agendaCreateRequest)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.agenda_id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.agenda_name").value(agendaName));
    }

    @Test
    void openAgenda_shouldReturnOkStatus() throws Exception {
        Long agendaId = 1L;
        String agendaName = "Approve Gustavo as our new employee";

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime agendaEnd = now.plusMinutes(1);
        String agendaStartExpected = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(now);
        String agendaEndExpected = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(agendaEnd);

        AgendaOpenResponse agendaOpenResponse = new AgendaOpenResponse(agendaId, agendaName, now, agendaEnd);
        when(agendaService.openAgenda(Mockito.any(Long.class), Mockito.any(AgendaOpenRequest.class)))
                .thenReturn(agendaOpenResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/agendas/{agendaId}", agendaId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new AgendaOpenRequest())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.agenda_id").value(agendaId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.agenda_name").value(agendaName))
                .andExpect(MockMvcResultMatchers.jsonPath("$.agenda_start").value(agendaStartExpected))
                .andExpect(MockMvcResultMatchers.jsonPath("$.agenda_end").value(agendaEndExpected));
    }

    @Test
    void vote_shouldReturnOkStatus() throws Exception {
        Long agendaId = 1L;
        AgendaVoteRequest agendaVoteRequest = new AgendaVoteRequest(true, "gustavo.gonzaga");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/agendas/{agendaId}/votes", agendaId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(agendaVoteRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getAgendaStatus_shouldReturnOkStatus() throws Exception {
        Long agendaId = 1L;
        String agendaName = "Approve Gustavo as our new employee";

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime agendaEnd = now.plusMinutes(1);
        String agendaStartExpected = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(now);
        String agendaEndExpected = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(agendaEnd);

        AgendaResponse agendaResponse = AgendaResponse.builder()
                .agendaId(agendaId)
                .agendaName(agendaName)
                .agendaStart(now)
                .agendaEnd(agendaEnd)
                .agendaStatus(AgendaStatus.FINISHED)
                .totalVotes(10L)
                .yesVotes(6L)
                .noVotes(4L)
                .agendaResult(true)
                .agendaResultDescription("YES is the most votaded")
                .build();

        when(agendaService.getAgendaStatus(Mockito.any(Long.class)))
                .thenReturn(agendaResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/agendas/{agendaId}", agendaId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.agenda_id").value(agendaId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.agenda_name").value(agendaName))
                .andExpect(MockMvcResultMatchers.jsonPath("$.agenda_start").value(agendaStartExpected))
                .andExpect(MockMvcResultMatchers.jsonPath("$.agenda_end").value(agendaEndExpected))
                .andExpect(MockMvcResultMatchers.jsonPath("$.agenda_status").value(AgendaStatus.FINISHED.name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.total_votes").value(10L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.yes_votes").value(6L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.no_votes").value(4L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.agenda_result").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.agenda_result_description").value("YES is the most votaded"));
    }

    @Test
    void getAgendasStatus_shouldReturnOkStatus() throws Exception {
        Pageable pageable = PageRequest.of(0, 1);
        Page<AgendaResponse> mockPage = new PageImpl<>(Collections.emptyList());

        when(agendaService.getAgendasStatus(pageable)).thenReturn(mockPage);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/agendas?pageNumber=0&pageSize=1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
