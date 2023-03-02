package db.desafiovotacao.repository;

import db.desafiovotacao.model.Sessao;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SessaoRepository extends CrudRepository<Sessao, UUID> {
}
