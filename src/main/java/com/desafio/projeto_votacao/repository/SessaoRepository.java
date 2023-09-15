package com.desafio.projeto_votacao.repository;

import com.desafio.projeto_votacao.entity.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {

}
