package br.com.vitt.apivotacao.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import br.com.vitt.apivotacao.components.ApiResponse;
import br.com.vitt.apivotacao.dto.AssociadoDTO;
import br.com.vitt.apivotacao.entities.Associado;
import br.com.vitt.apivotacao.entities.enums.Status;
import br.com.vitt.apivotacao.repositories.AssociadoRepository;
import br.com.vitt.apivotacao.services.exceptions.DatabaseException;
import br.com.vitt.apivotacao.services.exceptions.ResourceNotFoundException;
import br.com.vitt.apivotacao.services.exceptions.ValidationException;
import br.com.vitt.apivotacao.validation.CPF;

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

	public List<AssociadoDTO> acharTodosAptosAVotar() {
		 List<Associado> associadosAtivos = associadosAtivos(repository.findAllByStatus(1));
		 
		 return associadosAtivos.stream().map(x -> new AssociadoDTO(x)).toList();
	}

	@Transactional
	public AssociadoDTO insert(AssociadoDTO associado) {
		if (associado.getNome().isEmpty())
			throw new ValidationException("Precisa preencher o nome");
		validarCPF(associado.getCpf());
		if (repository.existsByCpf(associado.getCpf()))
			throw new ValidationException("CPF " + CPF.imprimeCPF(associado.getCpf()) + " já está cadastrado");

		if (associado.getStatus() == null) {
			RestTemplate restTemplate = new RestTemplate();
			String url = "https://user-info.herokuapp.com/users/" + associado.getCpf();
			ApiResponse response = restTemplate.getForObject(url, ApiResponse.class);
			associado.setStatus(Status.valueOf(response.getStatus()));
		}

		Associado entity = new Associado();
		copyDtoToEntity(associado, entity);
		entity = repository.save(entity);

		return new AssociadoDTO(entity);
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

	private Associado verificarAssociado(Long id) {
		Optional<Associado> associado = repository.findById(id);
		if (associado.isEmpty())
			throw new ValidationException("Nenhum associado encontrado com ID: " + id);
		if (!associado.get().getAtivo())
			throw new EntityNotFoundException("Associado inativo");
		return associado.get();
	}

	private List<Associado> associadosAtivos(List<Associado> associados) {
		associados = associados.stream().filter(x -> x.getAtivo()).collect(Collectors.toList());
		if (associados.isEmpty())
			throw new EntityNotFoundException("Nenhum associado encontrado");
		return associados;
	}

	public Status verificarSeAptoParaVotar(String cpf) {
		validarCPF(cpf);
		Optional<Associado> associado = repository.findByCpf(cpf);
		if (associado.isEmpty())
			throw new ValidationException("Nenhum associado encontrado com CPF: " + CPF.imprimeCPF(cpf));
		if (!associado.get().getAtivo())
			throw new EntityNotFoundException("Associado inativo");
		return associado.get().getStatus();
	}

	public AssociadoDTO ativarAssociado(Long id) {
		Optional<Associado> associadoOpt = repository.findById(id);
		if (associadoOpt.isEmpty())
			throw new ValidationException("Nenhum associado encontrado");
		associadoOpt.get().setAtivo(true);
		return new AssociadoDTO(repository.save(associadoOpt.get()));
	}

	public Associado mudarStatus(Long id, String status) {
		Associado associado = verificarAssociado(id);
		associado.setStatus(Status.valueOf(status));
		return repository.save(associado);
	}

	private void validarCPF(String cpf) {
		if (cpf.isEmpty())
			throw new ValidationException("Precisa preencher o CPF");
		if (cpf.length() != 11)
			throw new ValidationException("CPF possui 11 digitos");
		if (!CPF.isCPF(cpf))
			throw new ValidationException("CPF " + CPF.imprimeCPF(cpf) + " é inválido");
	}

	private void copyDtoToEntity(AssociadoDTO dto, Associado entity) {
		entity.setNome(dto.getNome());
		entity.setCpf(dto.getCpf());
		entity.setStatus(dto.getStatus());
		entity.setAtivo(dto.getAtivo());
	}

}
