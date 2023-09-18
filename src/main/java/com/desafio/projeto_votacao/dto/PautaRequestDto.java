package com.desafio.projeto_votacao.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PautaRequestDto {

    @NotBlank(message = "O campo título é obrigatório.")
    private String titulo;
    @NotBlank(message = "O campo descrição é obrigatória.")
    private String descricao;
    @Min(value = 1, message = "O campo tempoSessao deve ser igual ou maior que 1.")
    private Integer tempoSessao;

    public PautaRequestDto() {
        this.tempoSessao = 1;
    }

}
