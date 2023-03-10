package com.dbserver.service;

import com.dbserver.model.dto.AgendaVotingStatusDTO;
import com.dbserver.model.dto.VotingStatusDTO;
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

    public AgendaVotingStatusDTO getAgendaVotingStatus(String idAgenda) {
        LOGGER.info("Starting voting status build for agenda id: {}", idAgenda);

        Agenda agenda = agendaService.findById(idAgenda);
        Voting voting = votingService.findByIdAgenda(agenda.getId());
        List<Vote> votes = voteService.findAllByIdAgenda(agenda.getId());

        VotingStatusDTO votingStatusDTO = buildVotingStatusDTO(voting, votes);

        AgendaVotingStatusDTO agendaVotingStatusDTO = AgendaVotingStatusDTO.builder()
                .idAgenda(idAgenda)
                .description(agenda.getDescription())
                .title(agenda.getTitle())
                .voting(votingStatusDTO)
                .build();

        LOGGER.info("Voting build completed: {}", agendaVotingStatusDTO);
        return agendaVotingStatusDTO;
    }


    private VotingStatusDTO buildVotingStatusDTO(Voting voting, List<Vote> votes) {
        Integer votesAgainst = getVotesAgainst(votes);
        Integer votesInFavor = getVotesInFavor(votes);

        VotingStatus votingStatus;

        if (votingService.isOpen(voting)) {
            votingStatus = VotingStatus.OPEN;
        } else {
            votingStatus = getVotingStatusByVotes(votesAgainst, votesInFavor);
        }

        return VotingStatusDTO.builder()
                .id(voting.getId())
                .duration(voting.getDuration())
                .votingStatus(votingStatus)
                .startDate(voting.getStartDate())
                .endDate(voting.getEndDate())
                .votesAgainst(votesAgainst)
                .votesInFavor(votesInFavor)
                .build();
    }

    private VotingStatus getVotingStatusByVotes(Integer votesAgainst, Integer votesInFavor) {
        return votesAgainst > votesInFavor ? VotingStatus.DISAPPROVED :
                votesInFavor > votesAgainst ? VotingStatus.APPROVED : VotingStatus.TIED;
    }

    private Integer getVotesInFavor(List<Vote> votes) {
        return Math.toIntExact(votes.stream().filter(Vote::getVote).count());
    }

    private Integer getVotesAgainst(List<Vote> votes) {
        return Math.toIntExact(votes.stream().filter(vote -> !vote.getVote()).count());
    }

}
