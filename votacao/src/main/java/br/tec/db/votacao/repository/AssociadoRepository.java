package br.tec.db.votacao.repository;

import br.tec.db.votacao.model.Associado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssociadoRepository extends JpaRepository<Associado, Long> {
}
