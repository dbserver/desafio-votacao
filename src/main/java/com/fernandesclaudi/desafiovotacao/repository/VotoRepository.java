package com.fernandesclaudi.desafiovotacao.repository;

import com.fernandesclaudi.desafiovotacao.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

    List<Voto> findBySessao_IdOrderByDtVoto(Long idSessao);
}
