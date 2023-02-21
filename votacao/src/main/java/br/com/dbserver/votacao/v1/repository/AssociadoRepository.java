package br.com.dbserver.votacao.v1.repository;

import br.com.dbserver.votacao.v1.entity.Associado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado,Long> {
	Optional<Associado>findByDocumento(String documento);
}