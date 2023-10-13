package com.desafiovotacao.repository;

import com.desafiovotacao.domain.SessaoPauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessaoPautaRepository extends JpaRepository<SessaoPauta, String> {

}
