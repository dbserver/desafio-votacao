package br.com.occ.desafiovotacao.v1.service;

import br.com.occ.desafiovotacao.v1.model.Voto;

import java.util.List;
import java.util.Optional;

public interface IVotoService {
    Optional<Voto> findById(Long id);
    List<Voto> findAllByAssociado(Long associadoId);
    Voto votar(Voto voto);
}
