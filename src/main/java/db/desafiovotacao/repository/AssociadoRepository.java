package db.desafiovotacao.repository;

import db.desafiovotacao.model.Associado;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface AssociadoRepository extends CrudRepository<Associado, UUID> {

    Optional<Associado> findByCPF(String CPF);
}
