package db.desafiovotacao.repository;

import db.desafiovotacao.model.Associado;
import db.desafiovotacao.model.AssociadoPauta;
import db.desafiovotacao.model.Pauta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AssociadoPautaRepository extends CrudRepository<AssociadoPauta, UUID> {
    Optional<AssociadoPauta> findByAssociadoAndPauta(Associado associado, Pauta pauta);
}
