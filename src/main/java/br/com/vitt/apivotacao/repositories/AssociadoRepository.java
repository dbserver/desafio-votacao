package br.com.vitt.apivotacao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vitt.apivotacao.entities.Associado;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long> {

}
