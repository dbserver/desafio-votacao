package br.com.vitt.apivotacao.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vitt.apivotacao.entities.Pauta;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {
	public List<Pauta> findAllByStatusPauta(Integer statusPauta);
	public Optional<Pauta> findByTitulo(String titulo);
}
