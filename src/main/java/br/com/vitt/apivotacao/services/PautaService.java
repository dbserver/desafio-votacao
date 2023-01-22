package br.com.vitt.apivotacao.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vitt.apivotacao.dto.PautaDTO;
import br.com.vitt.apivotacao.entities.Associado;
import br.com.vitt.apivotacao.entities.Pauta;
import br.com.vitt.apivotacao.entities.PautaAssociado;
import br.com.vitt.apivotacao.entities.enums.Status;
import br.com.vitt.apivotacao.entities.enums.StatusPauta;
import br.com.vitt.apivotacao.entities.enums.Voto;
import br.com.vitt.apivotacao.repositories.AssociadoRepository;
import br.com.vitt.apivotacao.repositories.PautaAssociadoRepository;
import br.com.vitt.apivotacao.repositories.PautaRepository;
import br.com.vitt.apivotacao.services.exceptions.DatabaseException;
import br.com.vitt.apivotacao.services.exceptions.ResourceNotFoundException;
import br.com.vitt.apivotacao.services.exceptions.ValidationException;
import br.com.vitt.apivotacao.validation.CPF;
import br.com.vitt.apivotacao.validation.Time;

@Service
public class PautaService {
	
	@Autowired
	private PautaRepository repository;
	
	@Autowired
	PautaAssociadoRepository votacaoRepository;
	
	@Autowired
	AssociadoRepository associadoRepository;
	
	@Transactional(readOnly = true)
	public List<PautaDTO> findAll() {
		List<Pauta> page = repository.findAll();

		return page.stream().map(x -> new PautaDTO(x)).toList();
	}
	
	@Transactional(readOnly = true)
	public List<PautaDTO> findAllOpen() {
		List<PautaDTO> page = pautasValidas(repository.findAllByStatusPauta(1));

		return page;
	}
	
	@Transactional(readOnly = true)
	public List<PautaDTO> findAllApproved() {
		List<PautaDTO> page = pautasValidas(repository.findAllByStatusPauta(3));

		return page;
	}
	
	@Transactional(readOnly = true)
	public PautaDTO findById(Long id) {
		Optional<Pauta> obj = repository.findById(id);
		Pauta entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new PautaDTO(entity);
	}
	
	@Transactional
	public PautaDTO insert(PautaDTO dto) {
		if (dto.getTitulo().isEmpty())
			throw new ValidationException("Título nao pode estar em branco");
		Pauta entity = new Pauta();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		
		return new PautaDTO(entity);
	}
	
	public PautaDTO update(Long id, PautaDTO pauta) {
		Pauta obj = copyDtoToNewEntity(findById(id));	
		if (obj.getStatusPauta() != StatusPauta.OPEN)
			throw new ValidationException("Pautas votadas ou em votação não podem ser alteradas");
		obj.setTitulo(pauta.getTitulo());
		return insert(new PautaDTO(obj));
	}

	public PautaDTO reabrirPauta(Long id) {
		Pauta pauta = copyDtoToNewEntity(findById(id));	
		if (pauta.getStatusPauta() != StatusPauta.DRAW)
			throw new ValidationException("A pauta não pode ser reaberta");
		pauta.setStatusPauta(StatusPauta.OPEN);
		String time = "4000_12_31_00_00";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm");
		LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
		pauta.setFim(dateTime);
		for (PautaAssociado x : pauta.getPautaAssociado()) {
			x.setVoto(Voto.SEM_VOTO);
		}
		repository.save(pauta);
		return new PautaDTO(pauta);
	}

	public void delete(Long id) {
		try {
			Pauta pauta = copyDtoToNewEntity(findById(id));	
			if(pauta.getStatusPauta()!=StatusPauta.OPEN) throw new ValidationException("Pautas votadas ou em votação não podem ser deletadas");
			pauta.setAtivo();
			repository.save(pauta);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}
	
	private List<PautaDTO> pautasValidas(List<Pauta> pautas){
		pautas = pautas.stream().filter(x->x.getAtivo()).collect(Collectors.toList());
		if (pautas.isEmpty())throw new ResourceNotFoundException("Entity not found");
		
		return pautas.stream().map(x -> new PautaDTO(x)).collect(Collectors.toList());		
	}
	
	private Set<PautaAssociado> criandoVotacao(Pauta pauta) {
		List<Associado> aptosVotar = associadoRepository.findAllByStatus(1);
		if (aptosVotar.isEmpty())
			throw new ValidationException("Não pode ser aberta a votação, pois não temos associados aptos à votar");
		Set<PautaAssociado> list = new HashSet<>();
		for (Associado x : aptosVotar) {				
			list.add(new PautaAssociado(x, pauta));
		}
		return list;
	}
	
	public Pauta abrirVotacao(Long id, String time) {
		Pauta pauta = copyDtoToNewEntity(findById(id));	
		
		if (!pauta.getStatusPauta().equals(StatusPauta.OPEN))
			throw new ValidationException("Pauta já foi aberta para votação");
		
		if(time != null) {			
			if(!Time.isFormatTime(time)) throw new ValidationException("O tempo precisa estar no formado yyyy_MM_dd_HH_mm");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm");
			LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
			if (dateTime.isBefore(LocalDateTime.now())) {
				formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
				throw new ValidationException("Data inválida. " + dateTime.format(formatter)+ " já passou. Precisa informar uma data acima de " 
																							+ LocalDateTime.now().format(formatter) + "." );
			}
			pauta.abrirVotacao(dateTime);			
		}else {
			System.out.println("else");
			pauta.abrirVotacao();
		}
		Set<PautaAssociado> votacao = criandoVotacao(pauta);
		pauta.setPautaAssociado(votacao);
		repository.save(pauta);
		return pauta;
	}
	
	public PautaAssociado votar(Long id, String cpf, String voto) {
		Pauta pauta = copyDtoToNewEntity(findById(id));	

		if (pauta.getStatusPauta() != StatusPauta.IN_VOTING)
			throw new ValidationException("Pauta não está em votação");

		Associado associado = validarCPF(cpf);

		if (associado.getStatus() == Status.UNABLE_TO_VOTE)
			throw new ValidationException("Associado não está apto a votar");

		for (PautaAssociado x : pauta.getPautaAssociado()) {			
			if (x.getAssociado().equals(associado)) {
				if (x.getVoto() == Voto.SEM_VOTO) {
					if (voto.equalsIgnoreCase("sim")) {
						x.setVoto(Voto.SIM);
						votacaoRepository.save(x);
						return x;
					} else if (voto.equalsIgnoreCase("nao")) {
						x.setVoto(Voto.NAO);
						votacaoRepository.save(x);
						return x;
					} else {
						throw new ValidationException("Voto inválido");
					}
				} else {
					throw new ValidationException("Associado "+associado.getNome()+" já votou");
				}
			}
		}
		throw new ValidationException("Erro na urna");
	}
	
	private Associado validarCPF(String cpf) {
		if (cpf.isEmpty())
			throw new ValidationException("Precisa preencher o CPF");
		if (cpf.length() != 11)
			throw new ValidationException("CPF possui 11 digitos");
		if (!CPF.isCPF(cpf))
			throw new ValidationException("CPF " + CPF.imprimeCPF(cpf) + " é inválido");
		Optional<Associado> optAssociado = associadoRepository.findByCpf(cpf);
		if (optAssociado.isEmpty())
			throw new ValidationException("Nenhuma associado encontrada com cpf: " + CPF.imprimeCPF(cpf));
		return optAssociado.get();
	}
	
	private void copyDtoToEntity(PautaDTO dto, Pauta entity) {
		entity.setTitulo(dto.getTitulo());
		entity.setStatusPauta(dto.getStatusPauta());
		entity.setData(dto.getData());
		entity.setInicio(dto.getInicio());
		entity.setFim(dto.getFim());
		entity.setAtivo(dto.getAtivo());
	}
	
	private Pauta copyDtoToNewEntity(PautaDTO dto) {
		Pauta entity = new Pauta();
		entity.setTitulo(dto.getTitulo());
		entity.setStatusPauta(dto.getStatusPauta());
		entity.setData(dto.getData());
		entity.setInicio(dto.getInicio());
		entity.setFim(dto.getFim());
		entity.setAtivo(dto.getAtivo());
		
		return entity;
	}

}
