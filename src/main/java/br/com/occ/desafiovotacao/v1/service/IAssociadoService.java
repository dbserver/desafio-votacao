package br.com.occ.desafiovotacao.v1.service;

import br.com.occ.desafiovotacao.v1.dto.AssociadoDto;
import br.com.occ.desafiovotacao.v1.dto.AssociadoStatusDto;
import br.com.occ.desafiovotacao.v1.model.Associado;

import java.util.List;

public interface IAssociadoService {

    Associado save(AssociadoDto associado);
    Associado findById(Long id);
    List<Associado> findAllAtivos();

    AssociadoStatusDto getStatusCpf(String cpf);
}
