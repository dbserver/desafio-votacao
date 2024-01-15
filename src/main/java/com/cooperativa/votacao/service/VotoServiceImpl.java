package com.cooperativa.votacao.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cooperativa.votacao.dto.VotoDTO;
import com.cooperativa.votacao.entity.PautaEntity;
import com.cooperativa.votacao.entity.StatusSessaoEnum;
import com.cooperativa.votacao.entity.VotoEnum;
import com.cooperativa.votacao.exception.SessaoException;
import com.cooperativa.votacao.exception.VotoInvalidoException;
import com.cooperativa.votacao.mapper.VotoMapper;
import com.cooperativa.votacao.repository.VotoRepository;

@Service
@Transactional
public class VotoServiceImpl implements VotoService{
	
	private static Logger log = LoggerFactory.getLogger(VotoServiceImpl.class);
	
	@Autowired
	private PautaService pautaService;
	
	@Autowired
	private SessaoService sessaoService;
	
	@Autowired
	private VotoRepository votoRepository;
	
	@Autowired
	private VotoMapper votoMapper;

	@Override
	public void votar(VotoDTO votoDTO) {
		log.info("Voto do associado idAssociado "+votoDTO.getIdAssociado());
		
		PautaEntity pautaEntity= pautaService.buscarPorId(votoDTO.getIdPauta());
		
		sessaoService.validarSessao(pautaEntity.getStatusSessao(),StatusSessaoEnum.ABERTO );
		
		this.validarVoto(votoDTO, pautaEntity);
		
		votoRepository.save(votoMapper.DTOtoEntity(votoDTO));
	}
	
	private void validarVoto(VotoDTO votoDTO, PautaEntity pautaEntity) {
		if(LocalDateTime.now().isAfter(pautaEntity.getTempoSessao()))
			throw new SessaoException("Sessão de votação já foi finalizada");
		
		if(votoRepository.countByPautaAndIdAssociado(pautaEntity,votoDTO.getIdAssociado())!=0)
			throw new VotoInvalidoException("Você já votou");
		
		if(!votoDTO.getVoto().equals(VotoEnum.NAO.getVoto())&&!votoDTO.getVoto().equals(VotoEnum.SIM.getVoto()))
			throw new VotoInvalidoException("Voto apenas pode ser 'Sim'/'Não'");
	}

}
