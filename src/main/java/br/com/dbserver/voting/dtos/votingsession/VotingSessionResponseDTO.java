package br.com.dbserver.voting.dtos.votingsession;

import br.com.dbserver.voting.dtos.ScheduleDTO;

public record VotingSessionResponseDTO(Integer idSessionVoting, ScheduleDTO schedule, String votingStartTime, String votingEndTime, String status) {


}
