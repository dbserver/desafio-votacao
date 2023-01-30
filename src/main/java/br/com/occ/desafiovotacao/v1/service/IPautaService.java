package br.com.occ.desafiovotacao.v1.service;

import br.com.occ.desafiovotacao.v1.model.Pauta;

import java.util.List;

public interface IPautaService {
    Pauta findById(Long id);
    List<Pauta> findAll();
    Pauta save(Pauta pauta);
    List<Pauta> findAllAtivas();
}
