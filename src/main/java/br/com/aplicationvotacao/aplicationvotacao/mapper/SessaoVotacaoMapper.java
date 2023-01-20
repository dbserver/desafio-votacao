package br.com.aplicationvotacao.aplicationvotacao.mapper;



import br.com.aplicationvotacao.aplicationvotacao.dto.SessaoVotacaoDTO;
import br.com.aplicationvotacao.aplicationvotacao.model.Pauta;
import br.com.aplicationvotacao.aplicationvotacao.model.SessaoVotacao;

import java.time.Duration;
import java.time.LocalDateTime;

public class SessaoVotacaoMapper {

    public static SessaoVotacao dtoParaNovaSessaoVotacao(SessaoVotacaoDTO sessaoVotacaoDTO, Pauta pauta){

        LocalDateTime dataInicio = LocalDateTime.now();
        LocalDateTime dataFim;
        if(sessaoVotacaoDTO.getTempoAberturaSessao() == null)
            dataFim = dataInicio.plusMinutes(sessaoVotacaoDTO.getTEMPO_DEFAULT());
        else
            dataFim = dataInicio.plusMinutes(sessaoVotacaoDTO.getTempoAberturaSessao());

        return SessaoVotacao.builder()
                .dataInicio(dataInicio)
                .dataFim(dataFim)
                .pauta(pauta)
                .build();
    }

    public static SessaoVotacaoDTO sessaoVotacaoParaNovaDTO(SessaoVotacao sessaoVotacao){

        Integer diferencaEmMinutos = (int) Duration.between(sessaoVotacao.getDataInicio(), sessaoVotacao.getDataFim()).toMinutes();

        return SessaoVotacaoDTO.builder()
                .idPauta(sessaoVotacao.getPauta().getId())
                .tempoAberturaSessao(diferencaEmMinutos)
                .build();
    }
}

