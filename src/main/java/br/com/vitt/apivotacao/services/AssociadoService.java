package br.com.vitt.apivotacao.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vitt.apivotacao.entities.Associado;
import br.com.vitt.apivotacao.entities.enums.Status;
import br.com.vitt.apivotacao.repositories.AssociadoRepository;
import br.com.vitt.apivotacao.services.exceptions.ValidationException;
import br.com.vitt.apivotacao.validation.CPF;

@Service
public class AssociadoService {

	@Autowired
	private AssociadoRepository repository;

	public Associado findById(Long id){
		Associado associado = verificarAssociado(id);		
		return associado;				
	}
	
	public List<Associado> findAll(){
		return associadosAtivos(repository.findAll());		
	}
	
	public List<Associado> acharTodosAptosAVotar(){
		return associadosAtivos(repository.findAllByStatus(1));		
	}
	
	public Associado insert(Associado associado) {
		if(associado.getNome().isEmpty()) throw new ValidationException("Precisa preencher o nome");
		validarCPF(associado.getCpf());		
		if(repository.existsByCpf(associado.getCpf())) throw new ValidationException("CPF "+ CPF.imprimeCPF(associado.getCpf()) +" já está cadastrado");
		
		return repository.save(associado);		
	}	
	
	public Status verificarSeAptoParaVotar(String cpf) {
		validarCPF(cpf);
		Optional<Associado> associado = repository.findByCpf(cpf);
		if(associado.isEmpty()) throw new ValidationException("Nenhum associado encontrado com CPF: " + CPF.imprimeCPF(cpf));
		if(!associado.get().getAtivo()) throw new EntityNotFoundException("Associado inativo");
		return associado.get().getStatus();		
	}
	
	public Associado ativarAssociado(Long id) {
		Optional<Associado> associadoOpt = repository.findById(id);
		if(associadoOpt.isEmpty()) throw new ValidationException("Nenhum associado encontrado");
		associadoOpt.get().setAtivo(true);
		return repository.save(associadoOpt.get());		
	}
	
	public Associado mudarStatus(Long id, String status) {		
			Associado associado = verificarAssociado(id);
			associado.setStatus(Status.valueOf(status));
			return repository.save(associado);			
	}
	
	public void delete(Long id) {
		Associado associado = findById(id);
		associado.setStatus(Status.UNABLE_TO_VOTE);
		associado.setAtivo(false);		
		repository.save(associado);
	}
	
	private Associado verificarAssociado(Long id) {
		Optional<Associado> associado = repository.findById(id);
		if(associado.isEmpty()) throw new ValidationException("Nenhum associado encontrado com ID: " +id);
		if(!associado.get().getAtivo()) throw new EntityNotFoundException("Associado inativo");
		return associado.get();
	}
	
	private List<Associado> associadosAtivos(List<Associado> associados){
		associados = associados.stream().filter(x->x.getAtivo()).collect(Collectors.toList());
		if(associados.isEmpty()) throw new EntityNotFoundException("Nenhum associado encontrado");
		return associados;
	}
	
	private void validarCPF(String cpf) {
		if(cpf.isEmpty()) throw new ValidationException("Precisa preencher o CPF");
		if(cpf.length()!=11) throw new ValidationException("CPF possui 11 digitos");
		if(!CPF.isCPF(cpf)) throw new ValidationException("CPF " + CPF.imprimeCPF(cpf) +" é inválido");
	}
}
