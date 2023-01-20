package br.com.aplicationvotacao.aplicationvotacao.dto;


import lombok.*;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VotoDTO {

    @NotNull(message = "O campo id_associado é obrigatório.")
    private Long idAssociado;

    @NotNull(message = "O campo id_sessao_votacao é obrigatório.")
    private Long id_sessao_votacao;

    @NotNull(message = "O campo voto é obrigatório.")
    private boolean voto;

}

