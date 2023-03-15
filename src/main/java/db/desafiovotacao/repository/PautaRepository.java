package db.desafiovotacao.repository;

import db.desafiovotacao.model.AssociadoPauta;
import db.desafiovotacao.model.Pauta;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PautaRepository extends CrudRepository<Pauta, Long> {
}
