package br.com.vitt.apivotacao.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vitt.apivotacao.dto.AssociadoDTO;
import br.com.vitt.apivotacao.entities.Associado;
import br.com.vitt.apivotacao.repositories.AssociadoRepository;
import br.com.vitt.apivotacao.services.exceptions.DatabaseException;
import br.com.vitt.apivotacao.services.exceptions.ResourceNotFoundException;

@Service
public class AssociadoService {
	
	@Autowired
	private AssociadoRepository repository;
	
	@Transactional(readOnly = true)
	public List<AssociadoDTO> findAll() {
		List<Associado> page = repository.findAll();

		return page.stream().map(x -> new AssociadoDTO(x)).toList();
	}
	
	@Transactional(readOnly = true)
	public AssociadoDTO findById(Long id) {
		Optional<Associado> obj = repository.findById(id);
		Associado entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		
		return new AssociadoDTO(entity);
	}
	
	@Transactional
	public AssociadoDTO insert(AssociadoDTO dto) {
		Associado entity = new Associado();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		
		return new AssociadoDTO(entity);
	}

	@Transactional
	public AssociadoDTO update(Long id, AssociadoDTO dto) {
		try {
			Associado entity = repository.findById(id).get();
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			
			return new AssociadoDTO(entity);
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
	
	
	private void copyDtoToEntity(AssociadoDTO dto, Associado entity) {
		entity.setNome(dto.getNome());
		entity.setCpf(dto.getCpf());
		entity.setStatus(dto.getStatus());
		entity.setAtivo(dto.getAtivo());
	}

}
