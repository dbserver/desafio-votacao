package br.com.dbserver.voting.helpers;

import br.com.dbserver.voting.dtos.ScheduleDTO;
import br.com.dbserver.voting.dtos.votingsession.VotingSessionRequestDTO;
import br.com.dbserver.voting.dtos.votingsession.VotingSessionResponseDTO;
import br.com.dbserver.voting.enums.StatusVotingSession;
import br.com.dbserver.voting.models.Schedule;
import br.com.dbserver.voting.models.VotingSession;

import java.time.LocalDateTime;
import java.util.UUID;

public class VotingSessionCreator {

    public static VotingSessionResponseDTO votingSessionResponseDTO(){
        return new VotingSessionResponseDTO(
                UUID.fromString("f718f99b-fa35-4a5a-af43-735d4d1d1c8e"),
                new ScheduleDTO(UUID.fromString("d6df5158-cd61-48f3-a8cb-0660c24d1a23"), "pauta teste"),
                "11-01-2024 17:58:55",
                "11-01-2024 18:00:55",
                StatusVotingSession.OPEN.name()
        );
    }

    public static VotingSessionRequestDTO votingSessionRequestDTO(){
        return new VotingSessionRequestDTO("d6df5158-cd61-48f3-a8cb-0660c24d1a23", "10");
    }

    public static VotingSessionRequestDTO votingSessionRequestDTOInvalid(){
        return new VotingSessionRequestDTO(null, "10");
    }

    public static VotingSession votingSession(){
        return new VotingSession(
                UUID.fromString("f718f99b-fa35-4a5a-af43-735d4d1d1c8e"),
                new Schedule(UUID.fromString("d6df5158-cd61-48f3-a8cb-0660c24d1a23"), "pauta teste"),
                LocalDateTime.of(2024, 01, 12, 12, 20),
                LocalDateTime.of(2024, 01, 12, 12, 30),
                StatusVotingSession.OPEN);
    }
}
