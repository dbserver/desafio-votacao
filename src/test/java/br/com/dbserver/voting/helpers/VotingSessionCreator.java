package br.com.dbserver.voting.helpers;

import br.com.dbserver.voting.dtos.ScheduleDTO;
import br.com.dbserver.voting.dtos.vote.ResultOfTheVoteDTO;
import br.com.dbserver.voting.dtos.votingsession.VotingSessionRequestDTO;
import br.com.dbserver.voting.dtos.votingsession.VotingSessionResponseDTO;
import br.com.dbserver.voting.enums.StatusVotingSessionEnum;
import br.com.dbserver.voting.models.Schedule;
import br.com.dbserver.voting.models.VotingSession;

import java.time.LocalDateTime;
import java.util.UUID;

public class VotingSessionCreator {

    public static VotingSessionResponseDTO votingSessionResponseDTO(){
        return new VotingSessionResponseDTO(
                1,
                new ScheduleDTO(1, "pauta teste"),
                "11-01-2024 17:58:55",
                "11-01-2024 18:00:55",
                StatusVotingSessionEnum.OPEN.name()
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
                LocalDateTime.of(2024, 1, 12, 12, 20),
                LocalDateTime.of(2024, 12, 12, 12, 30),
                StatusVotingSessionEnum.OPEN);
    }

    public static VotingSession votingSessionClose(){
        return new VotingSession(
                1,
                new Schedule(1, "pauta teste"),
                LocalDateTime.of(2024, 1, 12, 12, 20),
                LocalDateTime.of(2024, 12, 12, 12, 30),
                StatusVotingSessionEnum.CLOSE);
    }

    public static VotingSession votingSessionOutOfTime(){
        return new VotingSession(
                1,
                new Schedule(1, "pauta teste"),
                LocalDateTime.of(2023, 1, 12, 12, 20),
                LocalDateTime.of(2023, 12, 12, 12, 30),
                StatusVotingSessionEnum.OPEN);
    }

    public static ResultOfTheVoteDTO resultOfTheVoteDTOValid(){
        return new ResultOfTheVoteDTO(
                1,
                LocalDateTime.of(2023, 1, 12, 12, 20),
                LocalDateTime.of(2023, 12, 12, 12, 30),
                new Schedule(1, "assempleia 1"),
                8L,
                5L,
                StatusVotingSessionEnum.CLOSE
        );
    }
}
