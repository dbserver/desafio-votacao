package com.fernandesclaudi.desafiovotacao.repository;

import com.fernandesclaudi.desafiovotacao.model.Associado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long> {

    Associado findByCpf(String cpf);
}
