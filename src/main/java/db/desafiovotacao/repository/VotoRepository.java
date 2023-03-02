package db.desafiovotacao.repository;

import db.desafiovotacao.model.Voto;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface VotoRepository extends CrudRepository<Voto, UUID> {
}
