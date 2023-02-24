package br.tec.db.votacao.dto;

import br.tec.db.votacao.model.Pauta;
import jakarta.validation.constraints.NotBlank;

public record PautaDTO(@NotBlank String titulo, Long idAssembleia) {

    public PautaDTO(Pauta pauta) {
        this(pauta.getTitulo(), pauta.getAssembleia().getId());
    }
}