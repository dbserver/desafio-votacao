package br.com.vitt.apivotacao.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vitt.apivotacao.entities.Associado;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long> {
	public Boolean existsByCpf(String cpf);
	public List<Associado> findAllByStatus(Integer status);
	public Optional<Associado> findByCpf(String cpf);
}
