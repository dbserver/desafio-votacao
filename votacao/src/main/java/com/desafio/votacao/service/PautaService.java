package com.desafio.votacao.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.desafio.votacao.entity.Pauta;
import org.springframework.validation.annotation.Validated;

@Validated
public class PautaService {
    
    Optional<Pauta> criar(Pauta pauta);

	Optional<Pauta> alterar(Long id, Pauta pauta);

	Optional<List<Pauta>> consultar(Optional<String> id, Optional<String> descricao, Optional<LocalDateTime> dtInicio,
			Optional<LocalDateTime> dtFim, Optional<Boolean> excluida);

	Optional<Pauta> consultarPorId(Long id);

	List<Pauta> consultarAsNaoFinalizadas();

	void excluir(Long id);
}
