package br.com.dbserver.voting.dtos.vote;

import java.io.Serializable;

public record VoteResponseDTO(String titleSchedule, String nameAssociate, String vote, String status) implements Serializable {
}
