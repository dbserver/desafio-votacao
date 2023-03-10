package com.dbserver.service;

import com.dbserver.exception.BusinessException;
import com.dbserver.exception.ConflictException;
import com.dbserver.exception.EntityNotFoundException;
import com.dbserver.model.dto.VoteCreatedDTO;
import com.dbserver.model.dto.VoteDTO;
import com.dbserver.model.entity.Agenda;
import com.dbserver.model.entity.Vote;
import com.dbserver.model.entity.Voting;
import com.dbserver.model.mapper.VoteMapper;
import com.dbserver.repository.VoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowableOfType;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VoteServiceTest {

    @Mock
    private VoteRepository voteRepository;

    @Mock
    private VoteMapper voteMapper;

    @Mock
    private VotingService votingService;

    @InjectMocks
    private VoteService voteService;

    Vote vote;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        vote = Vote.builder()
                .id("6404ff797f24ce45b0022c83")
                .idAgenda("idAgenda01")
                .vote(true)
                .createdDate(LocalDateTime.now())
                .build();
    }

    @Test
    void shouldCreateVote() {
        VoteCreatedDTO voteCreatedDTO = VoteCreatedDTO.builder().build();
        VoteDTO voteDTO = VoteDTO.builder().build();
        Voting voting = Voting.builder().idAgenda(vote.getIdAgenda()).build();
        when(votingService.findByIdAgenda(any())).thenReturn(voting);
        when(votingService.isOpen(voting)).thenReturn(true);
        when(voteMapper.toEntity(voteCreatedDTO)).thenReturn(vote);
        when(voteRepository.save(vote)).thenReturn(vote);
        when(voteMapper.toDTO(vote)).thenReturn(voteDTO);
        VoteDTO saved = voteService.create(voteCreatedDTO);
        assertThat(saved, equalTo(voteDTO));
    }

    @Test
    void shouldThrowConflictException() {
        VoteCreatedDTO voteCreatedDTO = VoteCreatedDTO.builder().build();
        VoteDTO voteDTO = VoteDTO.builder().build();
        Voting voting = Voting.builder().idAgenda(vote.getIdAgenda()).build();
        when(votingService.findByIdAgenda(any())).thenReturn(voting);
        when(votingService.isOpen(voting)).thenReturn(false);
        ConflictException throwable =
                catchThrowableOfType(() -> voteService.create(voteCreatedDTO), ConflictException.class);
        assertThat(throwable.getClass(), equalTo(ConflictException.class));
    }

    @Test
    void shouldThrowBusinessException() {
        VoteCreatedDTO voteCreatedDTO = VoteCreatedDTO.builder().build();
        VoteDTO voteDTO = VoteDTO.builder().build();
        Voting voting = Voting.builder().idAgenda(vote.getIdAgenda()).build();
        when(votingService.findByIdAgenda(any())).thenReturn(voting);
        when(votingService.isOpen(voting)).thenReturn(true);
        when(voteMapper.toEntity(voteCreatedDTO)).thenReturn(vote);
        when(voteRepository.save(vote)).thenThrow(BusinessException.class);
        BusinessException throwable =
                catchThrowableOfType(() -> voteService.create(voteCreatedDTO), BusinessException.class);
        assertThat(throwable.getClass(), equalTo(BusinessException.class));
    }

    @Test
    void shouldThrowConflictExceptionOnSave() {
        VoteCreatedDTO voteCreatedDTO = VoteCreatedDTO.builder().build();
        VoteDTO voteDTO = VoteDTO.builder().build();
        Voting voting = Voting.builder().idAgenda(vote.getIdAgenda()).build();
        when(votingService.findByIdAgenda(any())).thenReturn(voting);
        when(votingService.isOpen(voting)).thenReturn(true);
        when(voteMapper.toEntity(voteCreatedDTO)).thenReturn(vote);
        when(voteRepository.save(vote)).thenThrow(DuplicateKeyException.class);
        ConflictException throwable =
                catchThrowableOfType(() -> voteService.create(voteCreatedDTO), ConflictException.class);
        assertThat(throwable.getClass(), equalTo(ConflictException.class));
    }

    @Test
    void shouldFindAllVotesByIdAgenda() {
        when(voteRepository.findAllByIdAgenda(vote.getIdAgenda())).thenReturn(Arrays.asList(vote));
        List<Vote> votes = voteService.findAllByIdAgenda(vote.getIdAgenda());
        assertThat(votes.size(), equalTo(1));
    }
}
