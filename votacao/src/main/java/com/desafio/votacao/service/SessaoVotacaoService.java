package com.desafio.votacao.service;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.desafio.votacao.entity.Votacao;
import com.desafio.votacao.enums.VotoEnum;

public class SessaoVotacaoService {
    
    Optional<Votacao> votar(Votacao votacao);

	Optional<List<Votacao>> consultar(@NotNull(message = "O campo idPauta") Long idPauta, Optional<Long> idAssociado, Optional<VotoEnum> voto);
}
