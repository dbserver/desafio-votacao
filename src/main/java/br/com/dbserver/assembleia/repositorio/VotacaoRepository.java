package br.com.dbserver.assembleia.repositorio;

import java.util.Optional;

import br.com.dbserver.assembleia.entidade.Votacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotacaoRepository extends JpaRepository<Votacao, Long> {

	Optional<Votacao> findByVotoIdPautaId(Long idPauta);

	Optional<Votacao> findByVotoIdPautaIdAndVotoIdAssociadoId(Long idPauta, Long idAssociado);

}
