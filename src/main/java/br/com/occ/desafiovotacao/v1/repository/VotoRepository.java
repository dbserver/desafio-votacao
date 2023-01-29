package br.com.occ.desafiovotacao.v1.repository;

import br.com.occ.desafiovotacao.v1.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {
    List<Voto> findAllByAssociado_Id(Long associadoId);

    boolean existsVotoByAssociado_IdAndPauta_Id(Long associadoId, Long pautaId);
}
