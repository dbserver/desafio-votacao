package br.tec.db.votacao.repository;

import br.tec.db.votacao.model.Assembleia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssembleiaRepository extends JpaRepository<Assembleia, Long> {
}
