package com.db.votacao.api.repository;

import com.db.votacao.api.model.Associado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AssociadoRepository extends CrudRepository<Associado, UUID> {
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Associado a WHERE a.cpf = :cpf")
    boolean verificarCpfExistente(@Param("cpf") String cpf);
}
