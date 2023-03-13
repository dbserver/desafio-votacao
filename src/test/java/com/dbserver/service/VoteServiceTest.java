package com.dbserver.service;

import com.dbserver.exception.BusinessException;
import com.dbserver.exception.ConflictException;
import com.dbserver.exception.UnableToVoteException;
import com.dbserver.model.dto.VoteCreatedDTO;
import com.dbserver.model.dto.VoteDTO;
import com.dbserver.model.entity.Vote;
import com.dbserver.model.entity.VotingSession;
import com.dbserver.model.mapper.VoteMapper;
import com.dbserver.repository.VoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowableOfType;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ExtendWith(MockitoExtension.class)
class VoteServiceTest {

    @Mock
    private VoteRepository voteRepository;

    @Mock
    private VoteMapper voteMapper;

    @Mock
    private VotingSessionService votingSessionService;

    @Mock
    private CpfValidatorService cpfValidatorService;

    @InjectMocks
    private VoteService voteService;


    @Test
    void shouldCreateVote() {
        Vote vote = getVoteMock();
        VoteCreatedDTO voteCreatedDTO = getVoteCreatedDTOMock();
        VoteDTO voteDTO = getVoteDTOMock();
        VotingSession voting = getVotingMock(vote);
        when(votingSessionService.findByIdAgenda(any())).thenReturn(voting);
        when(votingSessionService.isOpen(voting)).thenReturn(true);
        when(voteMapper.toEntity(voteCreatedDTO)).thenReturn(vote);
        when(voteRepository.save(vote)).thenReturn(vote);
        when(voteMapper.toDTO(vote)).thenReturn(voteDTO);
        doNothing().when(cpfValidatorService).validate(any());
        VoteDTO saved = voteService.create(voteCreatedDTO);
        assertThat(saved, equalTo(voteDTO));
    }

    @Test
    void shouldThrowConflictException() {
        Vote vote = getVoteMock();
        VoteCreatedDTO voteCreatedDTO = getVoteCreatedDTOMock();
        VotingSession voting = getVotingMock(vote);
        when(votingSessionService.findByIdAgenda(any())).thenReturn(voting);
        when(votingSessionService.isOpen(voting)).thenReturn(false);
        ConflictException throwable =
                catchThrowableOfType(() -> voteService.create(voteCreatedDTO), ConflictException.class);
        assertThat(throwable.getClass(), equalTo(ConflictException.class));
    }

    @Test
    void shouldThrowBusinessException() {
        Vote vote = getVoteMock();
        VoteCreatedDTO voteCreatedDTO = getVoteCreatedDTOMock();
        VotingSession voting = getVotingMock(vote);
        when(votingSessionService.findByIdAgenda(any())).thenReturn(voting);
        when(votingSessionService.isOpen(voting)).thenReturn(true);
        when(voteMapper.toEntity(voteCreatedDTO)).thenReturn(vote);
        when(voteRepository.save(vote)).thenThrow(new BusinessException());
        doNothing().when(cpfValidatorService).validate(any());
        BusinessException throwable =
                catchThrowableOfType(() -> voteService.create(voteCreatedDTO), BusinessException.class);
        assertThat(throwable.getClass(), equalTo(BusinessException.class));
    }

    @Test
    void shouldThrowConflictExceptionOnSave() {
        Vote vote = getVoteMock();
        VoteCreatedDTO voteCreatedDTO = getVoteCreatedDTOMock();
        VotingSession voting = getVotingMock(vote);
        when(votingSessionService.findByIdAgenda(any())).thenReturn(voting);
        when(votingSessionService.isOpen(voting)).thenReturn(true);
        when(voteMapper.toEntity(voteCreatedDTO)).thenReturn(vote);
        when(voteRepository.save(vote)).thenThrow(DuplicateKeyException.class);
        doNothing().when(cpfValidatorService).validate(any());
        ConflictException throwable =
                catchThrowableOfType(() -> voteService.create(voteCreatedDTO), ConflictException.class);
        assertThat(throwable.getClass(), equalTo(ConflictException.class));
    }

    @Test
    void shouldFindAllVotesByIdAgenda() {
        Vote vote = getVoteMock();
        when(voteRepository.findAllByIdAgenda(vote.getIdAgenda())).thenReturn(Arrays.asList(vote));
        List<Vote> votes = voteService.findAllByIdAgenda(vote.getIdAgenda());
        assertThat(votes.size(), equalTo(1));
    }

    @Test
    void shouldThrowNotFoundExceptionWithUNABLE_TO_VOTE() {
        Vote vote = getVoteMock();
        VoteCreatedDTO voteCreatedDTO = getVoteCreatedDTOMock();
        VotingSession voting = getVotingMock(vote);
        when(votingSessionService.findByIdAgenda(any())).thenReturn(voting);
        when(votingSessionService.isOpen(voting)).thenReturn(true);
        doThrow(new UnableToVoteException()).when(cpfValidatorService).validate(any());
        UnableToVoteException throwable =
                catchThrowableOfType(() -> voteService.create(voteCreatedDTO), UnableToVoteException.class);
        assertThat(throwable.getClass(), equalTo(UnableToVoteException.class));
    }

    private Vote getVoteMock() {
        return Vote.builder()
                .id("6404ff797f24ce45b0022c83")
                .idAgenda("idAgenda01")
                .vote(true)
                .cpf("00011155566699")
                .createdDate(LocalDateTime.now())
                .build();
    }

    private VoteCreatedDTO getVoteCreatedDTOMock() {
        Vote vote = getVoteMock();
        return VoteCreatedDTO.builder()
                .cpf(vote.getCpf())
                .vote(vote.getVote())
                .idAgenda(vote.getIdAgenda())
                .build();
    }

    private VoteDTO getVoteDTOMock() {
        Vote vote = getVoteMock();
        return VoteDTO.builder()
                .cpf(vote.getCpf())
                .vote(vote.getVote())
                .idAgenda(vote.getIdAgenda())
                .createdDate(vote.getCreatedDate())
                .build();
    }

    private VotingSession getVotingMock(Vote vote) {
        return VotingSession.builder().idAgenda(vote.getIdAgenda()).build();
    }

}
