package com.db.votacao.api.repository;

import com.db.votacao.api.model.Pauta;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PautaRepository extends CrudRepository<Pauta, UUID> {
}
