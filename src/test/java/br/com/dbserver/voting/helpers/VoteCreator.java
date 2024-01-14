package br.com.dbserver.voting.helpers;

import br.com.dbserver.voting.dtos.vote.ResultOfTheVoteDTO;
import br.com.dbserver.voting.dtos.vote.VoteRequestDTO;
import br.com.dbserver.voting.dtos.vote.VoteResponseDTO;
import br.com.dbserver.voting.enums.StatusVotingSessionEnum;
import br.com.dbserver.voting.enums.TypeVoteEnum;
import br.com.dbserver.voting.models.Associate;
import br.com.dbserver.voting.models.Schedule;
import br.com.dbserver.voting.models.Vote;

import java.time.LocalDateTime;
import java.util.UUID;

public class VoteCreator {

    public static VoteResponseDTO voteResponseDTO(){
        return new VoteResponseDTO("pauta salva", "user", "SIM");
    }

    public static VoteRequestDTO voteRequestDTOValid(){
        return new VoteRequestDTO("f718f99b-fa35-4a5a-af43-735d4d1d1c8e", "357.672.271-87", "SIM");
    }

    public static VoteRequestDTO voteRequestDTOInValid(){
        return new VoteRequestDTO("f718f99b-fa35-4a5a-af43-735d4d1d1c8e", "123.456.789-10", "SIM");
    }

    public static ResultOfTheVoteDTO resultOfTheVoteDTOValid(){
        return new ResultOfTheVoteDTO(
                UUID.fromString("7534bc0c-2d1e-48d6-9d0a-ebafe51a4b3d"),
                LocalDateTime.of(2023, 1, 12, 12, 20),
                LocalDateTime.of(2023, 12, 12, 12, 30),
                new Schedule(UUID.fromString("cf8ccd5f-145f-4c10-82c0-a36f5080b188"), "assempleia 1"),
                8L,
                5L,
                StatusVotingSessionEnum.OPEN
                );
    }

    public static Vote voteValid(){
        return new Vote(
                UUID.fromString("7534bc0c-2d1e-48d6-9d0a-ebafe51a4b3d"),
                new Associate(UUID.fromString("d6df5158-cd61-48f3-a8cb-0660c24d1a23"), "user", "357.672.271-87"),
                new Schedule(UUID.fromString("d6df5158-cd61-48f3-a8cb-0660c24d1a23"), "pauta teste"),
                TypeVoteEnum.SIM);
    }
}
