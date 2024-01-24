package com.db.desafiovotacao.mockito;

import com.db.desafiovotacao.api.entity.Agenda;
import com.db.desafiovotacao.api.entity.Member;
import com.db.desafiovotacao.api.entity.Vote;
import com.db.desafiovotacao.api.exception.AgendaNotFoundException;
import com.db.desafiovotacao.api.exception.MemberNotFoundException;
import com.db.desafiovotacao.api.exception.OperationNotPermittedException;
import com.db.desafiovotacao.api.repository.AgendaRepository;
import com.db.desafiovotacao.api.repository.MemberRepository;
import com.db.desafiovotacao.api.repository.VoteRepository;
import com.db.desafiovotacao.api.service.VoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
public class VoteServiceTest {
    @InjectMocks
    private VoteService voteService;

    @Mock
    private VoteRepository voteRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private AgendaRepository agendaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void voteSuccess() throws AgendaNotFoundException, MemberNotFoundException, OperationNotPermittedException {
        UUID memberId = UUID.randomUUID();
        UUID agendaId = UUID.randomUUID();
        Boolean vote = true;

        Member member = new Member();
        Agenda agenda = new Agenda();

        when(memberRepository.findById(memberId)).thenReturn(Optional.of(member));
        when(agendaRepository.findById(agendaId)).thenReturn(Optional.of(agenda));
        when(voteRepository.existsByMemberAndAgenda(member, agenda)).thenReturn(false);
        when(voteRepository.save(any(Vote.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Vote newVote = voteService.vote(memberId, agendaId, vote);

        assertNotNull(newVote, "newVote is null");
        assertEquals(member, newVote.getMember(), "Member is not correct");
        assertEquals(agenda, newVote.getAgenda(), "Agenda is not correct");
        assertEquals(vote, newVote.getVoted(), "Vote is not correct");

        verify(voteRepository).save(any(Vote.class));
    }



    @Test
    void throwExceptionWhenMemberAlreadyVoted() {
        UUID associadoId = UUID.randomUUID();
        UUID pautaId = UUID.randomUUID();
        Boolean vote = true;

        Member member = new Member();
        Agenda agenda = new Agenda();

        when(memberRepository.findById(associadoId)).thenReturn(Optional.of(member));
        when(agendaRepository.findById(pautaId)).thenReturn(Optional.of(agenda));
        when(voteRepository.existsByMemberAndAgenda(member, agenda)).thenReturn(true);

        assertThrows(OperationNotPermittedException.class, () -> voteService.vote(associadoId, pautaId, vote));
    }
}
