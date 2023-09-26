package com.db.api.repositories;

import com.db.api.models.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessaoRepository extends JpaRepository<Sessao, Long> {

}
