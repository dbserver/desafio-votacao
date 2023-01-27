package br.com.occ.desafiovotacao.v1.service;

import br.com.occ.desafiovotacao.v1.dto.AssociadoStatusDto;
import br.com.occ.desafiovotacao.v1.enums.CpfStatusEnum;
import br.com.occ.desafiovotacao.v1.model.Associado;

import java.util.List;
import java.util.Optional;

public interface IAssociadoService {
    Optional<Associado> findById(Long id);
    List<Associado> findAllAtivos();

    AssociadoStatusDto getStatusCpf(String cpf);
}
