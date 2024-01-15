package br.com.dbserver.voting.dtos;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public record ScheduleDTO(Integer id, @NotBlank(message = "Titulo da pauta nao pode estar em branco") String title) implements Serializable {

}
