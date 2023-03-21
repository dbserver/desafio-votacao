package com.desafio.votacao.repository;

import java.util.Optional;

import com.desafio.votacao.entity.Votacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessaoVotacaoRepository extends JpaRepository<Votacao, Long> {

	Optional<Votacao> findByVotoIdPautaId(Long idPauta);

	Optional<Votacao> findByVotoIdPautaIdAndVotoIdAssociadoId(Long idPauta, Long idAssociado);

}
