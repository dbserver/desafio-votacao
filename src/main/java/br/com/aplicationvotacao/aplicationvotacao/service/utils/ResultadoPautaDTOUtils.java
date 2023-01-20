package br.com.aplicationvotacao.aplicationvotacao.service.utils;


import br.com.aplicationvotacao.aplicationvotacao.dto.ResultadoPautaDTO;
import br.com.aplicationvotacao.aplicationvotacao.dto.VotoResponseDTO;
import br.com.aplicationvotacao.aplicationvotacao.model.SessaoVotacao;
import br.com.aplicationvotacao.aplicationvotacao.model.Voto;
import br.com.aplicationvotacao.aplicationvotacao.model.enums.StatusPautaEnum;
import br.com.aplicationvotacao.aplicationvotacao.model.enums.StatusSessaoVotacaoEnum;
import br.com.aplicationvotacao.aplicationvotacao.model.enums.VotoEnum;
import br.com.aplicationvotacao.aplicationvotacao.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ResultadoPautaDTOUtils {

    @Autowired
    VotoRepository votoRepository;

    public ResultadoPautaDTO getResultadoPautaDTOFromSessaoVotacao(SessaoVotacao sessaoVotacao) {
        ResultadoPautaDTO resPauta = new ResultadoPautaDTO();
        System.out.println("id sessao : " + votoRepository.findAllBySessaoVotacaoId(sessaoVotacao.getId()));
        List<VotoResponseDTO> listaVotos = converteParaVotoResponseDTO(votoRepository.findAllBySessaoVotacaoId(sessaoVotacao.getId()));
        resPauta.setListaVotos(listaVotos);
        resPauta.setStatusSessao(calculaStatusSessaoVotacao(sessaoVotacao));
        resPauta.setStatusPauta(calculaStatusPauta(listaVotos));
        return resPauta;
    }

    public List<VotoResponseDTO> converteParaVotoResponseDTO(List<Voto> votos){
        List<VotoResponseDTO> novaListaVotos = new ArrayList<>();
        votos.forEach(voto ->{
                    novaListaVotos.add(new VotoResponseDTO(voto.getIdAssociado(), voto.getVoto()));
                }
        );
        return novaListaVotos;
    }

    public StatusSessaoVotacaoEnum calculaStatusSessaoVotacao(SessaoVotacao sessaoVotacao){
        LocalDateTime dataAtual = LocalDateTime.now();
        if(dataAtual.isAfter(sessaoVotacao.getDataFim()) || dataAtual.isBefore(sessaoVotacao.getDataInicio()))
            return StatusSessaoVotacaoEnum.FECHADA;
        else
            return StatusSessaoVotacaoEnum.ABERTA;
    }

    public StatusPautaEnum calculaStatusPauta(List<VotoResponseDTO> listaVotos){
        int resultado = 0;
        for(VotoResponseDTO v : listaVotos)
            resultado += v.getVoto().equals(VotoEnum.SIM) ? +1 : -1;

        if(listaVotos.isEmpty())
            return (StatusPautaEnum.NAO_VOTADA);
        else if(resultado == 0)
            return (StatusPautaEnum.EMPATE);
        else if(resultado > 0)
            return (StatusPautaEnum.APROVADA);
        else
            return (StatusPautaEnum.REJEITADA);
    }
}

