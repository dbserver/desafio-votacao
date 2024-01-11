package br.com.dbserver.voting.dtos;

import jakarta.validation.constraints.NotBlank;

public record ScheduleDTO(Integer id, @NotBlank(message = "Titulo da pauta nao pode estar em branco") String title) {
}
