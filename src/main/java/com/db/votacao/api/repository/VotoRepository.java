package com.db.votacao.api.repository;

import com.db.votacao.api.model.Voto;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface VotoRepository extends CrudRepository<Voto, UUID> {
}
