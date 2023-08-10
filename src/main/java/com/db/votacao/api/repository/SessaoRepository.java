package com.db.votacao.api.repository;

import com.db.votacao.api.model.Sessao;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SessaoRepository extends CrudRepository<Sessao, UUID> {

}
