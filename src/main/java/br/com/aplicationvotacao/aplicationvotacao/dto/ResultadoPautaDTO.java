package br.com.aplicationvotacao.aplicationvotacao.dto;


import br.com.aplicationvotacao.aplicationvotacao.model.enums.StatusPautaEnum;
import br.com.aplicationvotacao.aplicationvotacao.model.enums.StatusSessaoVotacaoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResultadoPautaDTO {

    private List<VotoResponseDTO> listaVotos;

    private StatusSessaoVotacaoEnum statusSessao;

    private StatusPautaEnum statusPauta;

}

