package com.desafio.votacao.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.desafio.votacao.entity.Associado;
import com.desafio.votacao.repository.VotoAssociadoRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class VotoAssociadoServiceImpl implements VotoAssociadoService {

	private final EntityManager entityManager;

	private final VotoAssociadoRepository votoAssociadoRepository;

	@Override
	public Optional<Associado> criar(Associado associado) {
		log.debug("Associado: {}", associado);
		log.info("Associado criado: {}", associado);
		return Optional.of(this.votoAssociadoRepository.save(associado));
	}

	@Override
	public Optional<Associado> alterar(Long id, Associado associadoAlterado) {
		log.debug("idAssociado: {}, associadoAlterado: {}", id, associadoAlterado);
		Optional<Associado> associadoDaBaseDeDadosOptional = this.votoAssociadoRepository.findById(id);
		if (associadoDaBaseDeDadosOptional.isPresent()) {
			Associado associado = associadoDaBaseDeDadosOptional.get();
			associadoAlterado.setId(id);
			log.info("Associado da base de dados: {}", associado);
			log.info("Associado alterado: {}", associadoAlterado);
			return Optional.of(this.votoAssociadoRepository.save(associadoAlterado));
		} else {
			log.warn("Objeto informado não foi encontrado. idAssociado: {}", id);
			throw new Exception();
		}
	}

	@Override
	public Optional<List<Associado>> consultar(Optional<String> id, Optional<String> nome, Optional<Boolean> excluido) {
		log.debug("idAssociado: {}, nome: {}, excluido: {}", id, nome, excluido);
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Associado> cq = cb.createQuery(Associado.class);
		Root<Associado> associadoRoot = cq.from(Associado.class);

		List<Predicate> predicateList = new ArrayList<>();
		if (id.isPresent()) {
			predicateList.add(cb.equal(associadoRoot.get("id"), id.get()));
		}

		if (nome.isPresent()) {
			predicateList.add(cb.like(associadoRoot.get("nome"), "%" + nome.get() + "%"));
		}

		if (excluido.isPresent()) {
			predicateList.add(cb.equal(associadoRoot.get("flExcluido"), excluido.get()));
		}
		cq.where(predicateList.stream().toArray(Predicate[]::new));

		return Optional.of(this.entityManager.createQuery(cq).getResultList());
	}

	@Override
	public Optional<Associado> consultarPorId(Long id) {
		log.debug("idAssociado: {}", id);
		return this.votoAssociadoRepository.findById(id);
	}

	@Override
	public void excluir(Long id) {
		log.debug("idAssociado: {}", id);
		Optional<Associado> associadoOptional = this.votoAssociadoRepository.findById(id);
		if (associadoOptional.isPresent()) {
			Associado associado = associadoOptional.get();
			associado.setFlExcluido(true);
			log.info("Associado excluído: {}", associado);
			this.votoAssociadoRepository.save(associado);
		} else {
			log.warn("Objeto informado não foi encontrado. idAssociado: {}", id);
			throw new Exception();
		}
	}
}
