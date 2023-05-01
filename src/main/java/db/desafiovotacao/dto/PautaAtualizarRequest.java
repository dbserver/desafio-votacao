package db.desafiovotacao.dto;

import db.desafiovotacao.model.Pauta;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PautaAtualizarRequest(
        @NotNull(message = "deve ser informada a pauta")
        Long id,

        @NotBlank(message = "titulo nao pode estar em branco")
        @Size(min = 7, max = 200, message = "deve conter de 7 a 200 caracteres")
        String titulo,

        String descricao,

        @NotNull @Valid SessaoRequest sessaoRequest
) {
        public PautaAtualizarRequest(Pauta pauta){
                this(pauta.getId(),
                        pauta.getTitulo(),
                        pauta.getDescricao(),
                        new SessaoRequest(pauta.getSessao())
                );
        }
}
