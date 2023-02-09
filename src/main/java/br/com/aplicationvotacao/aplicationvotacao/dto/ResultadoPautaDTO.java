package br.com.aplicationvotacao.aplicationvotacao.dto;


import br.com.aplicationvotacao.aplicationvotacao.model.enums.StatusPautaEnum;
import br.com.aplicationvotacao.aplicationvotacao.model.enums.StatusSessaoVotacaoEnum;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultadoPautaDTO {

    private List<VotoResponseDTO> listaVotos;

    private StatusSessaoVotacaoEnum statusSessao;

    private StatusPautaEnum statusPauta;

}

