package br.com.dbserver.voting.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record ScheduleDTO(UUID id, @NotBlank(message = "Titulo da pauta nao pode estar em branco") String title) {

}
