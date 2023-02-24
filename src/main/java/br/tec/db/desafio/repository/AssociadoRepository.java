package br.tec.db.desafio.repository;

import br.tec.db.desafio.business.domain.Associado;
import br.tec.db.desafio.business.domain.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long> {

    Associado findAssociadoByCpf(@Param("cpf") String cpf);

}
