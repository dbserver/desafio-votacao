package br.com.occ.desafiovotacao.v1.service;

import br.com.occ.desafiovotacao.v1.dto.VotoDto;
import br.com.occ.desafiovotacao.v1.model.Voto;

import java.util.List;
import java.util.Optional;

public interface IVotoService {
    Voto findById(Long id);
    List<Voto> findAllByAssociado(Long associadoId);
    Voto votar(VotoDto voto);
}
