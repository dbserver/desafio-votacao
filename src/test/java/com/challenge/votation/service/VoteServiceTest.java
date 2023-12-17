package com.challenge.votation.service;

import com.challenge.votation.exception.VotationException;
import com.challenge.votation.model.AgendaVoteRequest;
import com.challenge.votation.repository.VoteRepository;
import com.challenge.votation.repository.entity.AgendaEntity;
import com.challenge.votation.repository.entity.VoteEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
class VoteServiceTest {

    @InjectMocks
    private VoteService voteService;

    @Mock
    private VoteRepository voteRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveVoteSuccess() {
        AgendaEntity agendaEntity = new AgendaEntity();
        AgendaVoteRequest agendaVoteRequest = new AgendaVoteRequest(true, "gustavo.gonzaga");
        when(voteRepository.existsByClientIdAndAgendaEntity(any(), any())).thenReturn(false);

        voteService.saveVote(agendaEntity, agendaVoteRequest);

        verify(voteRepository, times(1)).save(any(VoteEntity.class));
    }

    @Test
    void testSaveVoteDuplicateVote() {
        AgendaEntity agendaEntity = new AgendaEntity();
        AgendaVoteRequest agendaVoteRequest = new AgendaVoteRequest(true, "gustavo.gonzaga");
        when(voteRepository.existsByClientIdAndAgendaEntity(any(), any())).thenReturn(true);

        Exception exception = assertThrows(Exception.class, () -> voteService.saveVote(agendaEntity, agendaVoteRequest));

        assertNotNull(exception);
        assertTrue(exception instanceof VotationException);
        assertEquals("This client already voted for this agenda.", exception.getMessage());
    }

}
