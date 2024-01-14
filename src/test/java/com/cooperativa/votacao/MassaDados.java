package com.cooperativa.votacao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.cooperativa.votacao.dto.AberturaSessaoDTO;
import com.cooperativa.votacao.dto.PautaDTO;
import com.cooperativa.votacao.dto.VotoDTO;
import com.cooperativa.votacao.entity.ContabilizacaoVotacaoPauta;
import com.cooperativa.votacao.entity.PautaEntity;
import com.cooperativa.votacao.entity.StatusSessaoEntity;
import com.cooperativa.votacao.entity.StatusSessaoEnum;
import com.cooperativa.votacao.entity.VotoEntity;

public class MassaDados {

	public static List<PautaEntity> getListPautaEntity(){
		List<PautaEntity> listPautaEntity= new ArrayList<>();
		listPautaEntity.add(getPautaEntity(StatusSessaoEnum.CRIADO));
		
		return listPautaEntity;
	}
	
	public static PautaEntity getPautaEntity(StatusSessaoEnum statusSessaoEnum) {
	  return new PautaEntity("teste", new StatusSessaoEntity(statusSessaoEnum));
	}
	
	public static PautaDTO getPautaDTO() {
		PautaDTO pauta = new PautaDTO();
		
		pauta.setId(1);
		pauta.setNome("teste");
		pauta.setStatusSessao("criado");
		
		return pauta;
	}
	
	public static ContabilizacaoVotacaoPauta getContabilizacaoVotacaoPauta() {
		ContabilizacaoVotacaoPauta contabilizacaoVotacaoPauta = new ContabilizacaoVotacaoPauta();
		
		contabilizacaoVotacaoPauta.setIdPauta(1);
		contabilizacaoVotacaoPauta.setNomePauta("teste");
		contabilizacaoVotacaoPauta.setStatusSessao("ABERTO");
		contabilizacaoVotacaoPauta.setTempoSessao(LocalDateTime.now());
		contabilizacaoVotacaoPauta.setTotalNao(2);
		contabilizacaoVotacaoPauta.setTotalSim(3);
		
		return contabilizacaoVotacaoPauta;
	}
	
	public static AberturaSessaoDTO  getAberturaSessaoDTO() {
		AberturaSessaoDTO aberturaSessaoDTO = new AberturaSessaoDTO();
		
		aberturaSessaoDTO.setIdPauta(1);
		aberturaSessaoDTO.setTempoSessao(LocalDateTime.now().plusDays(1));
		
		return aberturaSessaoDTO;
	}
	
	public static VotoEntity getVotoEntity() {
		VotoEntity votoEntity = new VotoEntity();
		
		votoEntity.setId(1L);
		votoEntity.setIdAssociado(1);
		votoEntity.setPauta(getPautaEntity(StatusSessaoEnum.ABERTO));
		votoEntity.setVoto("Sim");
		
		return votoEntity;
	}
	
	public static VotoDTO getVotoDTO() {
		VotoDTO votoDTO = new VotoDTO();
		
		votoDTO.setIdAssociado(1);
		votoDTO.setIdPauta(1);
		votoDTO.setVoto("Sim");
		
		return votoDTO;
	}
	
}
