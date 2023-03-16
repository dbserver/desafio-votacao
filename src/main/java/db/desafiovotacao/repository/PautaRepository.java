package db.desafiovotacao.repository;


import db.desafiovotacao.model.Pauta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {
    Page<Pauta> findAllByAtivoTrue(Pageable pageable);
}
