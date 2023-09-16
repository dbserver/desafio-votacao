package com.desafio.projeto_votacao.repository;

import com.desafio.projeto_votacao.entity.Pauta;
import com.desafio.projeto_votacao.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

    List<Voto> findByPauta(Pauta pauta);
    @Query("SELECT COUNT(v) > 0 FROM Voto v WHERE v.associado.cpf = :cpf")
    boolean existsByAssociadoCpf(@Param("cpf") String cpf);

}
