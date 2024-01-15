package br.com.dbserver.voting.helpers;

import br.com.dbserver.voting.dtos.vote.ResultOfTheVoteDTO;
import br.com.dbserver.voting.dtos.vote.VoteRequestDTO;
import br.com.dbserver.voting.dtos.vote.VoteResponseDTO;
import br.com.dbserver.voting.enums.StatusCpfEnum;
import br.com.dbserver.voting.enums.StatusVotingSessionEnum;
import br.com.dbserver.voting.enums.TypeVoteEnum;
import br.com.dbserver.voting.models.Associate;
import br.com.dbserver.voting.models.Schedule;
import br.com.dbserver.voting.models.Vote;
import br.com.dbserver.voting.models.VotingSession;

import java.time.LocalDateTime;
import java.util.UUID;

public class VoteCreator {

    public static VoteResponseDTO voteResponseDTO(){
        return new VoteResponseDTO("pauta salva", "user", "SIM", StatusCpfEnum.ABLE_TO_VOTE.name());
    }

    public static VoteRequestDTO voteRequestDTOValid(){
        return new VoteRequestDTO("1", "357.672.271-87", "SIM");
    }

    public static VoteRequestDTO voteRequestDTOInValid(){
        return new VoteRequestDTO("1", "123.456.789-10", "SIM");
    }

    public static ResultOfTheVoteDTO resultOfTheVoteDTOValid(){
        return new ResultOfTheVoteDTO(
                1,
                LocalDateTime.of(2023, 1, 12, 12, 20),
                LocalDateTime.of(2023, 12, 12, 12, 30),
                new Schedule(1, "assempleia 1"),
                8L,
                5L,
                StatusVotingSessionEnum.OPEN
                );
    }

    public static Vote voteValid(){
        return new Vote(
                1,
                new Associate(1, "user", "357.672.271-87"),
                new Schedule(1, "pauta teste"),
                new VotingSession(
                        1,
                        new Schedule(1, "pauta teste"),
                        LocalDateTime.of(2024, 1, 12, 12, 20),
                        LocalDateTime.of(2024, 12, 12, 12, 30),
                        StatusVotingSessionEnum.OPEN),
                TypeVoteEnum.SIM);
    }
}
