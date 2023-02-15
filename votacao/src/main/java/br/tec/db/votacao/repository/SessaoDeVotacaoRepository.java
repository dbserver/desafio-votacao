package br.tec.db.votacao.repository;

import br.tec.db.votacao.model.SessaoDeVotacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessaoDeVotacaoRepository extends JpaRepository<SessaoDeVotacao, Long> {
}
