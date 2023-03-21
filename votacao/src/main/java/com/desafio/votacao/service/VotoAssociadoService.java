package com.desafio.votacao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.validation.annotation.Validated;

import com.desafio.votacao.entity.Associado;

@Validated
public interface VotoAssociadoService {

	Optional<Associado> criar(Associado associado);

	Optional<Associado> alterar(Long id, Associado associado);

	Optional<List<Associado>> consultar(Optional<String> id, Optional<String> nome, Optional<Boolean> excluido);

	Optional<Associado> consultarPorId(Long id);

	void excluir(Long id);
}
