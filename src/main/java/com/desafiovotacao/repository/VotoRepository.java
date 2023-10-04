package com.desafiovotacao.repository;

import com.desafiovotacao.domain.VotoAssociado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VotoRepository extends JpaRepository<VotoAssociado, String> {

    Optional<VotoAssociado> findByAssociadoIdAndSessaoPautaId(String associadoId, String sessaoId);

    List<VotoAssociado> findAllByPautaId(String pautaId);

}
