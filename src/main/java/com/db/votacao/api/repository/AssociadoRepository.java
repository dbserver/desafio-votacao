package com.db.votacao.api.repository;

import com.db.votacao.api.model.Associado;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AssociadoRepository extends CrudRepository<Associado, UUID> {
}
