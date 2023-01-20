package br.com.aplicationvotacao.aplicationvotacao.dto;


import lombok.*;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessaoVotacaoDTO    {

    private final int TEMPO_DEFAULT = 1;

    @NotNull(message = "O campo id_pauta é obrigatório.")
    private Long idPauta;

    private Integer tempoAberturaSessao;

}
