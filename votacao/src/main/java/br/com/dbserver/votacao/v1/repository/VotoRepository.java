package br.com.dbserver.votacao.v1.repository;

import br.com.dbserver.votacao.v1.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {
	/**
	 * Verifica se existe um voto de um associado em uma determinada pauta
	 * @param associadoId o ID do associado que realizou o voto
	 * @param pautaId o ID da pauta na qual o voto foi realizado
	 * @return true se o voto do associado na pauta existir, false caso contrário
	 */
	boolean existsByAssociadoIdAndPautaId(Long associadoId, Long pautaId);
}
