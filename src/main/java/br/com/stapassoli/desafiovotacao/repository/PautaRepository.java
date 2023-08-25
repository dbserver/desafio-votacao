package br.com.stapassoli.desafiovotacao.repository;

import br.com.stapassoli.desafiovotacao.entity.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PautaRepository extends JpaRepository<Pauta, Long> {
}
