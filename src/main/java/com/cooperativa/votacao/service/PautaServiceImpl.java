package com.cooperativa.votacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cooperativa.votacao.dto.PautaDTO;
import com.cooperativa.votacao.entity.PautaEntity;
import com.cooperativa.votacao.entity.StatusSessaoEntity;
import com.cooperativa.votacao.entity.StatusSessaoEnum;
import com.cooperativa.votacao.mapper.PautaMapper;
import com.cooperativa.votacao.repository.PautaRepository;

@Service
@Transactional
public class PautaServiceImpl implements PautaService {
	
	@Autowired
	private PautaRepository pautaRepository;
	
	@Autowired
	private PautaMapper pautaMapper;

	@Override
	public PautaDTO cadastrar(PautaDTO pautaDTO) {
		return pautaMapper.entityToDTO(pautaRepository.save(new PautaEntity(pautaDTO.getNome(), 
				new StatusSessaoEntity(StatusSessaoEnum.CRIADO))));
	}

}
