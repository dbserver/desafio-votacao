package com.desafio.votacao.scheduled;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.desafio.votacao.enums.VotoEnum;
import com.desafio.votacao.enums.VotoEnumDTO;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.desafio.votacao.dto.VotacaoResultadoDTO;
import com.desafio.votacao.entity.Pauta;
import com.desafio.votacao.entity.Votacao;
import com.desafio.votacao.mapper.PautaMapper;
import com.desafio.votacao.service.PautaService;
import com.desafio.votacao.service.SessaoVotacaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class VotacaoScheduler {

    private static final String UM_MINUTO = "60000";

    private static final String QUEUE = "votacao-sqs";

    private final QueueMessagingTemplate queueMessagingTemplate;

    private final SessaoVotacaoService sessaoVotacaoService;

    private final PautaService pautaService;

    private final PautaMapper pautaMapper;


    @Scheduled(fixedDelayString = UM_MINUTO)
    public void finalizarPautas() {
    	log.info("Processamento iniciado.");
        List<Pauta> pautaList = pautaService.consultarAsNaoFinalizadas();
        for (Pauta pauta : pautaList) {
			if (LocalDateTime.now().isAfter(pauta.getDtFim())) {
				pauta.setFlFinalizada(true);
				this.queueMessagingTemplate.convertAndSend(QUEUE, votacaoResultadoDTOList(pauta));
				this.pautaService.alterar(pauta.getId(), pauta);
				log.info("Pauta finalizada: {}", pauta);
			}
		}
    }

	private List<VotacaoResultadoDTO> votacaoResultadoDTOList(Pauta pauta) {
		List<VotacaoResultadoDTO> votacaoResultadoDTOList = new ArrayList<>();
		Optional<VotacaoResultadoDTO> votacaoResultadoDtoSimOptional = votacaoResultado(pauta, VotoEnum.SIM);
		if (votacaoResultadoDtoSimOptional.isPresent()) {
			votacaoResultadoDTOList.add(votacaoResultadoDtoSimOptional.get());
		}
		Optional<VotacaoResultadoDTO> votacaoResultadoDtoNaoOptional = votacaoResultado(pauta, VotoEnum.NAO);
		if (votacaoResultadoDtoNaoOptional.isPresent()) {
			votacaoResultadoDTOList.add(votacaoResultadoDtoNaoOptional.get());
		}
		log.info("Message {} ", votacaoResultadoDTOList);
		return votacaoResultadoDTOList;
	}

	private Optional<VotacaoResultadoDTO> votacaoResultado(Pauta pauta, VotoEnum voto) {
		Optional<List<Votacao>> votacaoSimListOptional = sessaoVotacaoService.consultar(pauta.getId(), Optional.empty(), Optional.of(voto));
		if (votacaoSimListOptional.isPresent()) {
			List<Votacao> votacaoSimList = votacaoSimListOptional.get();
			VotacaoResultadoDTO votacaoResultadoSimDTO = new VotacaoResultadoDTO();
			votacaoResultadoSimDTO.setVoto(VotoEnumDTO.valueOf(voto.name()));
			votacaoResultadoSimDTO.setPauta(this.pautaMapper.toPautaCompletoDto(pauta));
			votacaoResultadoSimDTO.setAssociados(new ArrayList<>());
			for (Votacao votacao : votacaoSimList) {
				votacaoResultadoSimDTO.getAssociados().add(votacao.getVotoId().getAssociado());
			}
			votacaoResultadoSimDTO.setQtdVotos(votacaoResultadoSimDTO.getAssociados().size());
			return Optional.of(votacaoResultadoSimDTO);
		}
		return Optional.empty();
	}
}
