package br.com.vitt.apivotacao.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vitt.apivotacao.dto.PautaDTO;
import br.com.vitt.apivotacao.entities.Pauta;
import br.com.vitt.apivotacao.repositories.PautaRepository;
import br.com.vitt.apivotacao.services.exceptions.DatabaseException;
import br.com.vitt.apivotacao.services.exceptions.ResourceNotFoundException;

@Service
public class PautaService {
	
	@Autowired
	private PautaRepository repository;
	
	@Transactional(readOnly = true)
	public List<PautaDTO> findAll() {
		List<Pauta> page = repository.findAll();

		return page.stream().map(x -> new PautaDTO(x)).toList();
	}
	
	@Transactional(readOnly = true)
	public PautaDTO findById(Long id) {
		Optional<Pauta> obj = repository.findById(id);
		Pauta entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new PautaDTO(entity);
	}
	
	@Transactional
	public PautaDTO insert(PautaDTO dto) {
		Pauta entity = new Pauta();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new PautaDTO(entity);
	}

	@Transactional
	public PautaDTO update(Long id, PautaDTO dto) {
		try {
			Pauta entity = repository.findById(id).get();
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new PautaDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id nout found " + id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}
	
	
	private void copyDtoToEntity(PautaDTO dto, Pauta entity) {
		entity.setTitulo(dto.getTitulo());
		entity.setStatusPauta(dto.getStatusPauta());
		entity.setData(dto.getData());
		entity.setInicio(dto.getInicio());
		entity.setFim(dto.getFim());
		entity.setAtivo(dto.getAtivo());
	}

}
