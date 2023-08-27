package br.com.stapassoli.desafiovotacao.repository;

import br.com.stapassoli.desafiovotacao.entity.Voto;
import br.com.stapassoli.desafiovotacao.entity.VotoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<Voto, VotoId> {
}
