package br.com.occ.desafiovotacao.v1.service;

import br.com.occ.desafiovotacao.v1.model.Pauta;

import java.util.List;
import java.util.Optional;

public interface IPautaService {
    Optional<Pauta> findById(Long id);
    List<Pauta> findAll();
    Pauta save(Pauta pauta);
    Pauta update(Pauta pauta);
    void remove(Pauta pauta);
    List<Pauta> findAllAtivas();
}
