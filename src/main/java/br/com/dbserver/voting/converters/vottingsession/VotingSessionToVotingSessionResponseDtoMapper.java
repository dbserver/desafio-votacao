package br.com.dbserver.voting.converters.vottingsession;

import br.com.dbserver.voting.converters.Mapper;
import br.com.dbserver.voting.dtos.ScheduleDTO;
import br.com.dbserver.voting.dtos.votingsession.VotingSessionResponseDTO;
import br.com.dbserver.voting.helpers.Util;
import br.com.dbserver.voting.models.Schedule;
import br.com.dbserver.voting.models.VotingSession;
import org.springframework.stereotype.Component;

@Component
public class VotingSessionToVotingSessionResponseDtoMapper implements Mapper<VotingSession, VotingSessionResponseDTO> {
    @Override
    public VotingSessionResponseDTO map(VotingSession votingSession, VotingSessionResponseDTO votingSessionResponseDTO) {

        votingSessionResponseDTO = new VotingSessionResponseDTO(
                votingSession.getId(),
                getScheduleDTO(votingSession.getSchedule()),
                Util.localDateTimeToString(votingSession.getStart()),
                Util.localDateTimeToString(votingSession.getEnd()),
                votingSession.getStatus().name()
        );


        return votingSessionResponseDTO;
    }

    private ScheduleDTO getScheduleDTO(Schedule schedule){
        return new ScheduleDTO(schedule.getId(), schedule.getTitle());
    }
}
