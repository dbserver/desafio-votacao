package com.dbserver.service;

import com.dbserver.model.dto.AgendaVotingStatusDTO;
import com.dbserver.model.entity.Agenda;
import com.dbserver.model.entity.Vote;
import com.dbserver.model.entity.VotingSession;
import com.dbserver.model.enums.VotingStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VotingStatusServiceTest {

    @Mock
    private AgendaService agendaService;
    @Mock
    private VotingSessionService votingSessionService;
    @Mock
    private VoteService voteService;

    @InjectMocks
    private VotingStatusService votingStatusService;

    @Test
    void shouldGetVotingStatusWithOpenVoting() {
        String idAgenda = "idAgenda01";
        Agenda agenda = getAgendaMock();
        VotingSession voting = getVotingSessionMock();
        List<Vote> votes = getVotesMock();

        when(agendaService.findById(idAgenda)).thenReturn(agenda);
        when(votingSessionService.findByIdAgenda(any())).thenReturn(voting);
        when(votingSessionService.isOpen(voting)).thenReturn(true);
        when(voteService.findAllByIdAgenda(any())).thenReturn(votes);

        AgendaVotingStatusDTO agendaVotingStatus = votingStatusService.getAgendaVotingStatus(idAgenda);
        assertThat(agendaVotingStatus.getVoting().getVotingStatus(), equalTo(VotingStatus.OPEN));
    }

    @Test
    void shouldGetVotingStatusWithDisapprovadStatus() {
        String idAgenda = "idAgenda01";
        Agenda agenda = getAgendaMock();
        VotingSession voting = getVotingSessionMock();
        List<Vote> votes = getVotesMock();

        when(agendaService.findById(idAgenda)).thenReturn(agenda);
        when(votingSessionService.findByIdAgenda(any())).thenReturn(voting);
        when(votingSessionService.isOpen(voting)).thenReturn(false);
        when(voteService.findAllByIdAgenda(any())).thenReturn(votes);

        AgendaVotingStatusDTO agendaVotingStatus = votingStatusService.getAgendaVotingStatus(idAgenda);
        assertThat(agendaVotingStatus.getVoting().getVotingStatus(), equalTo(VotingStatus.DISAPPROVED));
        assertThat(agendaVotingStatus.getVoting().getVotesInFavor(), equalTo(1));
        assertThat(agendaVotingStatus.getVoting().getVotesAgainst(), equalTo(3));
        assertThat(agendaVotingStatus.getVoting().getTotalVotes(), equalTo(4));
    }

    @Test
    void shouldGetVotingStatusWithApprovadStatus() {
        String idAgenda = "idAgenda01";
        Agenda agenda = getAgendaMock();
        VotingSession voting = getVotingSessionMock();
        List<Vote> votes = getVotesMock();

        votes.add(Vote.builder().vote(true).cpf("000").idAgenda(idAgenda).build());
        votes.add(Vote.builder().vote(true).cpf("000").idAgenda(idAgenda).build());
        votes.add(Vote.builder().vote(true).cpf("000").idAgenda(idAgenda).build());
        votes.add(Vote.builder().vote(true).cpf("000").idAgenda(idAgenda).build());

        when(agendaService.findById(idAgenda)).thenReturn(agenda);
        when(votingSessionService.findByIdAgenda(any())).thenReturn(voting);
        when(votingSessionService.isOpen(voting)).thenReturn(false);
        when(voteService.findAllByIdAgenda(any())).thenReturn(votes);

        AgendaVotingStatusDTO agendaVotingStatus = votingStatusService.getAgendaVotingStatus(idAgenda);
        assertThat(agendaVotingStatus.getVoting().getVotingStatus(), equalTo(VotingStatus.APPROVED));
        assertThat(agendaVotingStatus.getVoting().getVotesInFavor(), equalTo(5));
        assertThat(agendaVotingStatus.getVoting().getVotesAgainst(), equalTo(3));
        assertThat(agendaVotingStatus.getVoting().getTotalVotes(), equalTo(8));
    }

    @Test
    void shouldGetVotingStatusWithTIEDStatus() {
        String idAgenda = "idAgenda01";
        Agenda agenda = getAgendaMock();
        VotingSession voting = getVotingSessionMock();
        List<Vote> votes = getVotesMock();

        votes.add(Vote.builder().vote(true).cpf("000").idAgenda(idAgenda).build());
        votes.add(Vote.builder().vote(true).cpf("000").idAgenda(idAgenda).build());

        when(agendaService.findById(idAgenda)).thenReturn(agenda);
        when(votingSessionService.findByIdAgenda(any())).thenReturn(voting);
        when(votingSessionService.isOpen(voting)).thenReturn(false);
        when(voteService.findAllByIdAgenda(any())).thenReturn(votes);

        AgendaVotingStatusDTO agendaVotingStatus = votingStatusService.getAgendaVotingStatus(idAgenda);
        assertThat(agendaVotingStatus.getVoting().getVotingStatus(), equalTo(VotingStatus.TIED));
        assertThat(agendaVotingStatus.getVoting().getVotesInFavor(), equalTo(3));
        assertThat(agendaVotingStatus.getVoting().getVotesAgainst(), equalTo(3));
        assertThat(agendaVotingStatus.getVoting().getTotalVotes(), equalTo(6));
    }

    private List<Vote> getVotesMock() {
        Agenda agenda = getAgendaMock();
        List<Vote> votes = new ArrayList<>();
        votes.add(Vote.builder().vote(true).cpf("000").idAgenda(agenda.getId()).build());
        votes.add(Vote.builder().vote(false).cpf("111").idAgenda(agenda.getId()).build());
        votes.add(Vote.builder().vote(false).cpf("222").idAgenda(agenda.getId()).build());
        votes.add(Vote.builder().vote(false).cpf("333").idAgenda(agenda.getId()).build());
        return votes;
    }

    private VotingSession getVotingSessionMock() {
        Agenda agenda = getAgendaMock();
        long duration = 6000000000L;
        return VotingSession.builder()
                .id("idVoting01")
                .idAgenda(agenda.getId())
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plus(duration, ChronoUnit.MILLIS))
                .duration(duration)
                .build();
    }

    private Agenda getAgendaMock() {
        return Agenda.builder()
                .id("6404ff797f24ce45b0022c83")
                .title("teste")
                .description("teste")
                .createdDate(LocalDateTime.now())
                .build();
    }

}
