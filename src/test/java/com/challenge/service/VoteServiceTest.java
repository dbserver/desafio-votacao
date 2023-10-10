package com.challenge.service;

import com.challenge.enums.VoteEnum;
import com.challenge.model.Associate;
import com.challenge.model.StaveSession;
import com.challenge.model.Vote;
import com.challenge.repository.AssociateRepository;
import com.challenge.repository.StaveSessionRepository;
import com.challenge.repository.VoteRepository;
import com.challenge.service.impl.VoteServiceImpl;
import com.challenge.stubs.AssociateStub;
import com.challenge.stubs.StaveSessionStub;
import com.challenge.stubs.VoteRequestDtoStub;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VoteServiceTest {
    @InjectMocks
    private VoteServiceImpl voteService;
    @Mock
    private VoteRepository voteRepository;
    @Mock
    private StaveSessionRepository staveSessionRepository;
    @Mock
    private AssociateRepository associateRepository;

    @Test
    void saveAssociateVote_whenSuccessful() throws Exception {
        StaveSession staveSession = StaveSessionStub.build();
        Associate associate = AssociateStub.build();

        when(voteRepository.existsByAssociateAndSession(any(), any())).thenReturn(false);
        when(staveSessionRepository.getReferenceById(any())).thenReturn(staveSession);
        when(associateRepository.getReferenceById(any())).thenReturn(associate);

        Vote voteMock = Vote.builder()
                .vote(VoteEnum.YES)
                .associate(associate)
                .staveSession(staveSession)
                .build();

        when(voteRepository.save(any())).thenReturn(voteMock);

        Vote result = voteService.save(VoteRequestDtoStub.build());
        assertThat(result).isEqualTo(voteMock);
    }

    @Test
    void saveAssociateVote_whenFailsVoteProcessed() {
        when(voteRepository.existsByAssociateAndSession(any(), any())).thenReturn(true);
        when(associateRepository.getReferenceById(any())).thenReturn(AssociateStub.build());
        assertThrows(IllegalArgumentException.class, () -> voteService.save(VoteRequestDtoStub.build()));
    }

    @Test
    void saveAssociateVote_whenFailsStaveClosed() {
        when(voteRepository.existsByAssociateAndSession(any(), any())).thenReturn(false);
        when(staveSessionRepository.getReferenceById(any())).thenReturn(StaveSessionStub.buildClose());
        when(associateRepository.getReferenceById(any())).thenReturn(AssociateStub.build());
        assertThrows(IllegalArgumentException.class, () -> voteService.save(VoteRequestDtoStub.build()));
    }
}
