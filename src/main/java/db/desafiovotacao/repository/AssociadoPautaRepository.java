package db.desafiovotacao.repository;

import db.desafiovotacao.model.Associado;
import db.desafiovotacao.model.AssociadoPauta;
import db.desafiovotacao.model.Pauta;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;


public interface AssociadoPautaRepository extends CrudRepository<AssociadoPauta, UUID> {
    Optional<AssociadoPauta> findByAssociadoAndPauta(Associado associado, Pauta pauta);
}
