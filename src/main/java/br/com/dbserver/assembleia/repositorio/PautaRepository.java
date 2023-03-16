package br.com.dbserver.assembleia.repositorio;

import java.util.List;

import br.com.dbserver.assembleia.entidade.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {

	List<Pauta> findByFlFinalizadaFalse();

}
