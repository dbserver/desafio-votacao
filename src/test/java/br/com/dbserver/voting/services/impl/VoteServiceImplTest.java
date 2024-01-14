package br.com.dbserver.voting.services.impl;


import br.com.dbserver.voting.dtos.vote.ResultOfTheVoteDTO;
import br.com.dbserver.voting.dtos.vote.VoteRequestDTO;
import br.com.dbserver.voting.exceptions.NotFoundException;
import br.com.dbserver.voting.exceptions.VotingException;
import br.com.dbserver.voting.helpers.AssociateCreator;
import br.com.dbserver.voting.helpers.VoteCreator;
import br.com.dbserver.voting.helpers.VotingSessionCreator;
import br.com.dbserver.voting.models.vote.Vote;
import br.com.dbserver.voting.repositories.VoteRepository;
import br.com.dbserver.voting.services.VotingCacheService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class VoteServiceImplTest {

    @Mock
    private VoteRepository voteRepository;

    @Mock
    private VotingCacheService votingCacheService;

    @InjectMocks
    private VoteServiceImpl voteService;

    @BeforeEach
    void setUp() {
        when(votingCacheService.getCachedVotingSession(any())).thenReturn(Optional.of(VotingSessionCreator.votingSession()));
        when(votingCacheService.getCachedAssociate(anyString())).thenReturn(Optional.of(AssociateCreator.associateValid()));
        when(votingCacheService.voteProgress()).thenReturn(Optional.of(VoteCreator.resultOfTheVoteDTOValid()));
    }

    @Test
    @Transactional
    void shouldSaveVoteWhenValidVoteDTOAndSessionExistsAndAssociateExists() {
        when(voteRepository.save(any(Vote.class))).thenReturn(VoteCreator.voteValid());
        voteService.voting(VoteCreator.voteRequestDTOValid());

        verify(voteRepository, times(1)).save(any());
    }

    @Test
    void shouldThrowVotingExceptionWhenVotingIsClosed() {
        when(votingCacheService.getCachedVotingSession(any())).thenReturn(Optional.of(VotingSessionCreator.votingSessionClose()));
        VoteRequestDTO invalidVoteRequestDTO = new VoteRequestDTO("session-id", "357.672.271-87", "NAO");

        assertThrows(VotingException.class, () -> voteService.voting(invalidVoteRequestDTO));
    }

    @Test
    void shouldThrowNotFoundExceptionWhenAssociateInvalid() {
        when(votingCacheService.getCachedAssociate(anyString())).thenReturn(Optional.empty());
        VoteRequestDTO invalidVoteRequestDTO = new VoteRequestDTO("session-id", "123.456.789-10", "NAO");

        assertThrows(NotFoundException.class, () -> voteService.voting(invalidVoteRequestDTO));
    }

    @Test
    void shouldThrowVotingExceptionWhenVotingOutOfTime() {
        when(votingCacheService.getCachedVotingSession(any())).thenReturn(Optional.of(VotingSessionCreator.votingSessionOutOfTime()));
        VoteRequestDTO invalidVoteRequestDTO = new VoteRequestDTO("session-id", "123.456.789-10", "SIM");

        assertThrows(VotingException.class, () -> voteService.voting(invalidVoteRequestDTO));
    }

    @Test
    void shouldListAllVoteInProgressWhenVotingOpen() {
        ResultOfTheVoteDTO expectedResultOfTheVoteDTO = VoteCreator.resultOfTheVoteDTOValid();
        List<ResultOfTheVoteDTO> result = voteService.listVoteInProgress();

        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getId()).isEqualTo(expectedResultOfTheVoteDTO.getId());
    }

}