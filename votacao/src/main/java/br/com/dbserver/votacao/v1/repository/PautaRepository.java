package br.com.dbserver.votacao.v1.repository;

import br.com.dbserver.votacao.v1.entity.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {
}
