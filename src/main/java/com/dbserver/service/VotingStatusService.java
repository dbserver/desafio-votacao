package com.dbserver.service;

import com.dbserver.model.dto.AgendaVotingDTO;
import com.dbserver.model.dto.VotingAgendaDTO;
import com.dbserver.model.entity.Agenda;
import com.dbserver.model.entity.Vote;
import com.dbserver.model.entity.Voting;
import com.dbserver.model.enums.VotingStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotingStatusService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AgendaService agendaService;
    @Autowired
    private VotingService votingService;
    @Autowired
    private VoteService voteService;

    public AgendaVotingDTO getAgendaVotingStatus(String idAgenda) {
        Agenda agenda =  agendaService.findById(idAgenda);

        Voting voting = votingService.findByIdAgenda(agenda.getId());
        List<Vote> votes = voteService.findAllByIdVoting(voting.getId());

        Integer votesAgaist = Math.toIntExact(votes.stream().filter(vote -> vote.getVote() == false).count());
        Integer votesInFavor = Math.toIntExact(votes.stream().filter(vote -> vote.getVote() == true).count());

        VotingStatus votingStatus;

        if (votingService.isOpen(voting)) {
            votingStatus = VotingStatus.OPEN;
        } else {
            if (votesAgaist > votesInFavor) {
                votingStatus = VotingStatus.DISAPPROVED;
            } else if (votesInFavor > votesAgaist) {
                votingStatus = VotingStatus.APPROVED;
            }else{
                votingStatus = VotingStatus.TIED;
            }
        }

        VotingAgendaDTO votingAgendaDTO = VotingAgendaDTO.builder()
                .id(voting.getId())
                .duration(voting.getDuration())
                .votingStatus(votingStatus)
                .startDate(voting.getStartDate())
                .endDate(voting.getEndDate())
                .votesAgaist(votesAgaist)
                .votesInFavor(votesInFavor)
                .build();

        return AgendaVotingDTO.builder()
                .idAgenda(idAgenda)
                .description(agenda.getDescription())
                .title(agenda.getTitle())
                .voting(votingAgendaDTO)
                .build();

    }

}
