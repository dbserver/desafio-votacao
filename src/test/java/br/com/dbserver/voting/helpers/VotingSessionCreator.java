package br.com.dbserver.voting.helpers;

import br.com.dbserver.voting.dtos.ScheduleDTO;
import br.com.dbserver.voting.dtos.votingsession.VotingSessionRequestDTO;
import br.com.dbserver.voting.dtos.votingsession.VotingSessionResponseDTO;
import br.com.dbserver.voting.enums.StatusVotingSession;
import br.com.dbserver.voting.models.Schedule;
import br.com.dbserver.voting.models.VotingSession;

import java.time.LocalDateTime;

public class VotingSessionCreator {

    public static VotingSessionResponseDTO votingSessionResponseDTO(){
        return new VotingSessionResponseDTO(
                1,
                new ScheduleDTO(1, "pauta teste"),
                "11-01-2024 17:58:55",
                "11-01-2024 18:00:55",
                StatusVotingSession.OPEN.name()
        );
    }

    public static VotingSessionRequestDTO votingSessionRequestDTO(){
        return new VotingSessionRequestDTO("1", "10");
    }

    public static VotingSessionRequestDTO votingSessionRequestDTOInvalid(){
        return new VotingSessionRequestDTO(null, "10");
    }

    public static VotingSession votingSession(){
        return new VotingSession(
                1,
                new Schedule(1, "pauta teste"),
                LocalDateTime.of(2024, 01, 12, 12, 20),
                LocalDateTime.of(2024, 01, 12, 12, 30),
                StatusVotingSession.OPEN);
    }
}
