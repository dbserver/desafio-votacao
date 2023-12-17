package com.challenge.votation.service;

import com.challenge.votation.exception.VotationException;
import com.challenge.votation.exception.VotationNotFoundException;
import com.challenge.votation.mapper.AgendaMapper;
import com.challenge.votation.model.*;
import com.challenge.votation.repository.AgendaRepository;
import com.challenge.votation.repository.entity.AgendaEntity;
import com.challenge.votation.repository.entity.VoteEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AgendaServiceTest {

    @Mock
    private AgendaRepository agendaRepository;

    @Mock
    private AgendaMapper agendaMapper;

    @Mock
    private VoteService voteService;

    @InjectMocks
    private AgendaService agendaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAgenda() {
        String agendaName = "Approve Gustavo as our new employee";
        AgendaCreateRequest agendaCreateRequest = new AgendaCreateRequest(agendaName);
        AgendaCreateResponse agendaCreateResponse = new AgendaCreateResponse(1L, agendaName);

        AgendaEntity agendaEntity = new AgendaEntity();
        agendaEntity.setId(1L);
        agendaEntity.setName("Test Agenda");

        when(agendaRepository.save(any(AgendaEntity.class))).thenReturn(agendaEntity);
        when(agendaMapper.mapAgendaCreateResponse(agendaEntity)).thenReturn(agendaCreateResponse);

        AgendaCreateResponse response = agendaService.createAgenda(agendaCreateRequest);

        assertNotNull(response);
        assertEquals(1L, response.getAgendaId());
        assertEquals(agendaName, response.getAgendaName());
    }

    @Test
    void testOpenAgendaIdNotFound() {
        Exception exception = assertThrows(Exception.class, () -> agendaService.openAgenda(1L, new AgendaOpenRequest()));

        assertNotNull(exception);
        assertTrue(exception instanceof VotationNotFoundException);
        assertEquals("Agenda was not found.", exception.getMessage());
    }

    @Test
    void testOpenAgenda() {
        Long agendaId = 1L;
        String agendaName = "Approve Gustavo as our new employee";
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime agendaEnd = now.plusMinutes(1);
        AgendaOpenResponse agendaOpenResponse = new AgendaOpenResponse(agendaId, agendaName, now, agendaEnd);

        AgendaEntity agendaEntity = new AgendaEntity();
        agendaEntity.setId(agendaId);
        agendaEntity.setName(agendaName);

        when(agendaRepository.findById(agendaId)).thenReturn(Optional.of(agendaEntity));
        when(agendaMapper.mapAgendaOpenResponse(agendaEntity)).thenReturn(agendaOpenResponse);

        AgendaOpenResponse response = agendaService.openAgenda(agendaId, new AgendaOpenRequest());

        assertNotNull(response);
        assertNotNull(response.getAgendaStart());
        assertNotNull(response.getAgendaEnd());
    }

    @Test
    void testOpenAgendaAlreadyOpened() {
        Long agendaId = 1L;
        String agendaName = "Approve Gustavo as our new employee";
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime agendaEnd = now.plusMinutes(1);

        AgendaEntity agendaEntity = new AgendaEntity();
        agendaEntity.setId(agendaId);
        agendaEntity.setName(agendaName);
        agendaEntity.setStartDate(now);
        agendaEntity.setStartDate(agendaEnd);

        when(agendaRepository.findById(agendaId)).thenReturn(Optional.of(agendaEntity));

        Exception exception = assertThrows(Exception.class, () -> agendaService.openAgenda(agendaId, new AgendaOpenRequest()));

        assertNotNull(exception);
        assertTrue(exception instanceof VotationException);
        assertEquals("Agenda is already opened.", exception.getMessage());
    }

    @Test
    void testOpenAgendaWithPastEndDate() {
        Long agendaId = 1L;
        String agendaName = "Approve Gustavo as our new employee";
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime agendaEnd = now.minusMinutes(1);

        AgendaEntity agendaEntity = new AgendaEntity();
        agendaEntity.setId(agendaId);
        agendaEntity.setName(agendaName);

        when(agendaRepository.findById(agendaId)).thenReturn(Optional.of(agendaEntity));

        Exception exception = assertThrows(Exception.class, () -> agendaService.openAgenda(agendaId, new AgendaOpenRequest(agendaEnd)));

        assertNotNull(exception);
        assertTrue(exception instanceof VotationException);
        assertEquals("The Agenda End Date informed is in the past.", exception.getMessage());
    }

    @Test
    void testOpenAgendaInformingEndDate() {
        Long agendaId = 1L;
        String agendaName = "Approve Gustavo as our new employee";
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime agendaEnd = now.plusMinutes(20);
        AgendaOpenResponse agendaOpenResponse = new AgendaOpenResponse(agendaId, agendaName, now, agendaEnd);

        AgendaEntity agendaEntity = new AgendaEntity();
        agendaEntity.setId(agendaId);
        agendaEntity.setName(agendaName);

        when(agendaRepository.findById(agendaId)).thenReturn(Optional.of(agendaEntity));
        when(agendaMapper.mapAgendaOpenResponse(agendaEntity)).thenReturn(agendaOpenResponse);

        AgendaOpenResponse response = agendaService.openAgenda(agendaId, new AgendaOpenRequest(agendaEnd));

        assertNotNull(response);
        assertNotNull(response.getAgendaStart());
        assertNotNull(response.getAgendaEnd());
        assertEquals(response.getAgendaEnd(), agendaEnd);
    }

    @Test
    void testVote() {
        Long agendaId = 1L;
        AgendaVoteRequest voteRequest = new AgendaVoteRequest();

        AgendaEntity agendaEntity = new AgendaEntity();
        agendaEntity.setId(agendaId);
        agendaEntity.setStartDate(LocalDateTime.now().minusMinutes(1));
        agendaEntity.setEndDate(LocalDateTime.now().plusMinutes(1));

        when(agendaRepository.findById(agendaId)).thenReturn(Optional.of(agendaEntity));

        agendaService.vote(agendaId, voteRequest);

        verify(voteService, times(1)).saveVote(agendaEntity, voteRequest);
    }

    @Test
    void testVoteAgendaNotOpen() {
        Long agendaId = 1L;
        AgendaVoteRequest voteRequest = new AgendaVoteRequest();

        AgendaEntity agendaEntity = new AgendaEntity();
        agendaEntity.setId(agendaId);

        when(agendaRepository.findById(agendaId)).thenReturn(Optional.of(agendaEntity));

        Exception exception = assertThrows(Exception.class, () -> agendaService.vote(agendaId, voteRequest));

        assertNotNull(exception);
        assertTrue(exception instanceof VotationException);
        assertEquals("Agenda it's not open yet.", exception.getMessage());
    }

    @Test
    void testVoteAgendaClosed() {
        Long agendaId = 1L;
        AgendaVoteRequest voteRequest = new AgendaVoteRequest();

        AgendaEntity agendaEntity = new AgendaEntity();
        agendaEntity.setId(agendaId);
        agendaEntity.setStartDate(LocalDateTime.now().minusMinutes(2));
        agendaEntity.setEndDate(LocalDateTime.now().minusMinutes(1));

        when(agendaRepository.findById(agendaId)).thenReturn(Optional.of(agendaEntity));

        Exception exception = assertThrows(Exception.class, () -> agendaService.vote(agendaId, voteRequest));

        assertNotNull(exception);
        assertTrue(exception instanceof VotationException);
        assertEquals("Agenda it's already closed.", exception.getMessage());
    }

    @Test
    void testGetAgendaStatus() {
        Long agendaId = 1L;
        String agendaName = "Approve Gustavo as our new employee";

        Set<VoteEntity> voteEntitySet = new HashSet<>();
        VoteEntity voteEntity = new VoteEntity();
        voteEntitySet.add(voteEntity);

        AgendaEntity agendaEntity = new AgendaEntity();
        agendaEntity.setId(agendaId);
        agendaEntity.setName(agendaName);
        agendaEntity.setVotes(voteEntitySet);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime agendaEnd = now.plusMinutes(1);

        AgendaResponse agendaResponse = AgendaResponse.builder()
                .agendaId(agendaId)
                .agendaName(agendaName)
                .agendaStart(now)
                .agendaEnd(agendaEnd)
                .build();

        when(agendaRepository.findById(agendaId)).thenReturn(Optional.of(agendaEntity));
        when(agendaMapper.mapAgendaResponse(agendaEntity)).thenReturn(agendaResponse);

        AgendaResponse response = agendaService.getAgendaStatus(agendaId);

        assertNotNull(response);
        assertEquals(agendaEntity.getId(), response.getAgendaId());
    }

    @Test
    void testGetAgendaStatusNotStarted() {
        Long agendaId = 1L;
        String agendaName = "Approve Gustavo as our new employee";

        Set<VoteEntity> voteEntitySet = new HashSet<>();
        VoteEntity voteEntity = new VoteEntity();
        voteEntitySet.add(voteEntity);

        AgendaEntity agendaEntity = new AgendaEntity();
        agendaEntity.setId(agendaId);
        agendaEntity.setName(agendaName);
        agendaEntity.setVotes(voteEntitySet);

        AgendaResponse agendaResponse = AgendaResponse.builder()
                .agendaId(agendaId)
                .agendaName(agendaName)
                .build();

        when(agendaRepository.findById(agendaId)).thenReturn(Optional.of(agendaEntity));
        when(agendaMapper.mapAgendaResponse(agendaEntity)).thenReturn(agendaResponse);

        AgendaResponse response = agendaService.getAgendaStatus(agendaId);

        assertNotNull(response);
        assertEquals(agendaEntity.getId(), response.getAgendaId());
        assertEquals(AgendaStatus.NOT_STARTED, response.getAgendaStatus());
    }

    @Test
    void testGetAgendaStatusFinished() {
        Long agendaId = 1L;
        String agendaName = "Approve Gustavo as our new employee";

        Set<VoteEntity> voteEntitySet = new HashSet<>();
        VoteEntity voteEntity = new VoteEntity();
        voteEntitySet.add(voteEntity);

        AgendaEntity agendaEntity = new AgendaEntity();
        agendaEntity.setId(agendaId);
        agendaEntity.setName(agendaName);
        LocalDateTime startDate = LocalDateTime.now().minusMinutes(2);
        agendaEntity.setStartDate(startDate);
        LocalDateTime endDate = LocalDateTime.now().minusMinutes(1);
        agendaEntity.setEndDate(endDate);
        agendaEntity.setVotes(voteEntitySet);

        AgendaResponse agendaResponse = AgendaResponse.builder()
                .agendaId(agendaId)
                .agendaName(agendaName)
                .agendaStart(startDate)
                .agendaEnd(endDate)
                .build();

        when(agendaRepository.findById(agendaId)).thenReturn(Optional.of(agendaEntity));
        when(agendaMapper.mapAgendaResponse(agendaEntity)).thenReturn(agendaResponse);

        AgendaResponse response = agendaService.getAgendaStatus(agendaId);

        assertNotNull(response);
        assertEquals(agendaEntity.getId(), response.getAgendaId());
        assertEquals(AgendaStatus.FINISHED, response.getAgendaStatus());
    }

    @Test
    void testGetAgendaStatusInProgress() {
        Long agendaId = 1L;
        String agendaName = "Approve Gustavo as our new employee";

        Set<VoteEntity> voteEntitySet = new HashSet<>();
        VoteEntity voteEntity = new VoteEntity();
        voteEntitySet.add(voteEntity);

        AgendaEntity agendaEntity = new AgendaEntity();
        agendaEntity.setId(agendaId);
        agendaEntity.setName(agendaName);
        LocalDateTime startDate = LocalDateTime.now().minusMinutes(2);
        agendaEntity.setStartDate(startDate);
        LocalDateTime endDate = LocalDateTime.now().plusMinutes(10);
        agendaEntity.setEndDate(endDate);
        agendaEntity.setVotes(voteEntitySet);

        AgendaResponse agendaResponse = AgendaResponse.builder()
                .agendaId(agendaId)
                .agendaName(agendaName)
                .agendaStart(startDate)
                .agendaEnd(endDate)
                .build();

        when(agendaRepository.findById(agendaId)).thenReturn(Optional.of(agendaEntity));
        when(agendaMapper.mapAgendaResponse(agendaEntity)).thenReturn(agendaResponse);

        AgendaResponse response = agendaService.getAgendaStatus(agendaId);

        assertNotNull(response);
        assertEquals(agendaEntity.getId(), response.getAgendaId());
        assertEquals(AgendaStatus.IN_PROGRESS, response.getAgendaStatus());
    }

    @Test
    void testSetAgendaVotesTie() {
        Long agendaId = 1L;
        String agendaName = "Approve Gustavo as our new employee";

        Set<VoteEntity> voteEntitySet = new HashSet<>();
        VoteEntity voteEntity = new VoteEntity();
        voteEntity.setVote(true);
        voteEntitySet.add(voteEntity);
        VoteEntity voteEntity2 = new VoteEntity();
        voteEntity2.setVote(false);
        voteEntitySet.add(voteEntity2);

        AgendaEntity agendaEntity = new AgendaEntity();
        agendaEntity.setId(agendaId);
        agendaEntity.setName(agendaName);
        LocalDateTime startDate = LocalDateTime.now().minusMinutes(2);
        agendaEntity.setStartDate(startDate);
        LocalDateTime endDate = LocalDateTime.now().minusMinutes(1);
        agendaEntity.setEndDate(endDate);
        agendaEntity.setVotes(voteEntitySet);

        AgendaResponse agendaResponse = AgendaResponse.builder()
                .agendaId(agendaId)
                .agendaName(agendaName)
                .agendaStart(startDate)
                .agendaEnd(endDate)
                .build();

        when(agendaRepository.findById(agendaId)).thenReturn(Optional.of(agendaEntity));
        when(agendaMapper.mapAgendaResponse(agendaEntity)).thenReturn(agendaResponse);

        AgendaResponse response = agendaService.getAgendaStatus(agendaId);

        assertNotNull(response);
        assertEquals(agendaEntity.getId(), response.getAgendaId());
        assertEquals(2L, response.getTotalVotes());
        assertEquals(1L, response.getYesVotes());
        assertEquals(1L, response.getNoVotes());
        assertNull(response.getAgendaResult());
        assertEquals("It's a tie, the number of votes are equal", response.getAgendaResultDescription());
    }

    @Test
    void testSetAgendaVotesYesMostVotaded() {
        Long agendaId = 1L;
        String agendaName = "Approve Gustavo as our new employee";

        Set<VoteEntity> voteEntitySet = new HashSet<>();
        VoteEntity voteEntity = new VoteEntity();
        voteEntity.setVote(true);
        voteEntitySet.add(voteEntity);
        VoteEntity voteEntity2 = new VoteEntity();
        voteEntity2.setVote(true);
        voteEntitySet.add(voteEntity2);

        AgendaEntity agendaEntity = new AgendaEntity();
        agendaEntity.setId(agendaId);
        agendaEntity.setName(agendaName);
        LocalDateTime startDate = LocalDateTime.now().minusMinutes(2);
        agendaEntity.setStartDate(startDate);
        LocalDateTime endDate = LocalDateTime.now().minusMinutes(1);
        agendaEntity.setEndDate(endDate);
        agendaEntity.setVotes(voteEntitySet);

        AgendaResponse agendaResponse = AgendaResponse.builder()
                .agendaId(agendaId)
                .agendaName(agendaName)
                .agendaStart(startDate)
                .agendaEnd(endDate)
                .build();

        when(agendaRepository.findById(agendaId)).thenReturn(Optional.of(agendaEntity));
        when(agendaMapper.mapAgendaResponse(agendaEntity)).thenReturn(agendaResponse);

        AgendaResponse response = agendaService.getAgendaStatus(agendaId);

        assertNotNull(response);
        assertEquals(agendaEntity.getId(), response.getAgendaId());
        assertEquals(2L, response.getTotalVotes());
        assertEquals(2L, response.getYesVotes());
        assertEquals(0, response.getNoVotes());
        assertEquals(true, response.getAgendaResult());
        assertEquals("YES is the most votaded", response.getAgendaResultDescription());
    }

    @Test
    void testSetAgendaVotesNoMostVotaded() {
        Long agendaId = 1L;
        String agendaName = "Approve Gustavo as our new employee";

        Set<VoteEntity> voteEntitySet = new HashSet<>();
        VoteEntity voteEntity = new VoteEntity();
        voteEntity.setVote(false);
        voteEntitySet.add(voteEntity);
        VoteEntity voteEntity2 = new VoteEntity();
        voteEntity2.setVote(false);
        voteEntitySet.add(voteEntity2);

        AgendaEntity agendaEntity = new AgendaEntity();
        agendaEntity.setId(agendaId);
        agendaEntity.setName(agendaName);
        LocalDateTime startDate = LocalDateTime.now().minusMinutes(2);
        agendaEntity.setStartDate(startDate);
        LocalDateTime endDate = LocalDateTime.now().minusMinutes(1);
        agendaEntity.setEndDate(endDate);
        agendaEntity.setVotes(voteEntitySet);

        AgendaResponse agendaResponse = AgendaResponse.builder()
                .agendaId(agendaId)
                .agendaName(agendaName)
                .agendaStart(startDate)
                .agendaEnd(endDate)
                .build();

        when(agendaRepository.findById(agendaId)).thenReturn(Optional.of(agendaEntity));
        when(agendaMapper.mapAgendaResponse(agendaEntity)).thenReturn(agendaResponse);

        AgendaResponse response = agendaService.getAgendaStatus(agendaId);

        assertNotNull(response);
        assertEquals(agendaEntity.getId(), response.getAgendaId());
        assertEquals(2L, response.getTotalVotes());
        assertEquals(0, response.getYesVotes());
        assertEquals(2L, response.getNoVotes());
        assertEquals(false, response.getAgendaResult());
        assertEquals("NO is the most votaded", response.getAgendaResultDescription());
    }

    @Test
    void testGetAgendasStatus() {
        Long agendaId = 1L;
        String agendaName = "Approve Gustavo as our new employee";

        Set<VoteEntity> voteEntitySet = new HashSet<>();
        VoteEntity voteEntity = new VoteEntity();
        voteEntity.setVote(false);
        voteEntitySet.add(voteEntity);
        VoteEntity voteEntity2 = new VoteEntity();
        voteEntity2.setVote(false);
        voteEntitySet.add(voteEntity2);

        AgendaEntity agendaEntity = new AgendaEntity();
        agendaEntity.setId(agendaId);
        agendaEntity.setName(agendaName);
        LocalDateTime startDate = LocalDateTime.now().minusMinutes(2);
        agendaEntity.setStartDate(startDate);
        LocalDateTime endDate = LocalDateTime.now().minusMinutes(1);
        agendaEntity.setEndDate(endDate);
        agendaEntity.setVotes(voteEntitySet);

        AgendaResponse agendaResponse = AgendaResponse.builder()
                .agendaId(agendaId)
                .agendaName(agendaName)
                .agendaStart(startDate)
                .agendaEnd(endDate)
                .build();

        Pageable pageable = mock(Pageable.class);
        Page<AgendaEntity> agendaEntityPage = new PageImpl<>(Collections.singletonList(agendaEntity));

        when(agendaRepository.findAll(pageable)).thenReturn(agendaEntityPage);
        when(agendaMapper.mapAgendaResponse(agendaEntity)).thenReturn(agendaResponse);

        Page<AgendaResponse> responsePage = agendaService.getAgendasStatus(pageable);

        assertNotNull(responsePage);
        assertEquals(1, responsePage.getContent().size());
    }
}
