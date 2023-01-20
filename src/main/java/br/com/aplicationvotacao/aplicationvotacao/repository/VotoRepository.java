package br.com.aplicationvotacao.aplicationvotacao.repository;



import br.com.aplicationvotacao.aplicationvotacao.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VotoRepository extends JpaRepository<Voto, Long> {

    Optional<Voto> findByidAssociadoEqualsAndSessaoVotacaoIdEquals(Long idAssociado, Long SessaoVotacaoId);

    List<Voto> findAllBySessaoVotacaoId(Long idSessaoVotacao);

}

