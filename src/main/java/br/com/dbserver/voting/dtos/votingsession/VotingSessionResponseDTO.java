package br.com.dbserver.voting.dtos.votingsession;

import br.com.dbserver.voting.dtos.ScheduleDTO;

import java.util.UUID;

public record VotingSessionResponseDTO(UUID idSessionVoting, ScheduleDTO schedule, String votingStartTime, String votingEndTime, String status) {


}
