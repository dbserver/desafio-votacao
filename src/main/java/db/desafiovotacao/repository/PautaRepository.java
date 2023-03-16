package db.desafiovotacao.repository;

import db.desafiovotacao.model.AssociadoPauta;
import db.desafiovotacao.model.Pauta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {
    Page<Pauta> findAllByAtivoTrue(Pageable pageable);
}
