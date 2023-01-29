package br.com.occ.desafiovotacao.v1.service;

import br.com.occ.desafiovotacao.v1.model.Pauta;
import br.com.occ.desafiovotacao.v1.model.Sessao;

import java.util.List;
import java.util.Optional;

public interface ISessaoService {
    Optional<Sessao> findById(Long id);
    List<Sessao> findAll();
    Sessao save(Sessao sessao, Long idPauta);
    Sessao update(Sessao sessao);
    void remove(Sessao sessao);
    List<Sessao> findAllAtivas();

}
