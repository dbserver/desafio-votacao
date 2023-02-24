package br.tec.db.votacao.dto;

import br.tec.db.votacao.model.Associado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AssociadoDTO(@NotBlank String nome,
                           @NotBlank @Pattern(regexp = "[0-9]{11}", message = "CPF deve conter apenas números") String cpf) {

    public AssociadoDTO(Associado associado) {
        this(associado.getNome(), associado.getCpf());
    }
}