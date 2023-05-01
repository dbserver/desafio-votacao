package db.desafiovotacao.repository;


import db.desafiovotacao.model.Pauta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PautaRepository extends JpaRepository<Pauta, Long> {
    Optional<Page<Pauta>> findAllByAtivoTrue(Pageable pageable);
}
