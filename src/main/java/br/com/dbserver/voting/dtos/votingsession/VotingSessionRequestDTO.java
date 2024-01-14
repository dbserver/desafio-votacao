package br.com.dbserver.voting.dtos.votingsession;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public record VotingSessionRequestDTO (@NotBlank(message = "Id da pauta nao pode estar em branco")  String scheduleId, String votingEndTime) implements Serializable { }
