package br.com.occ.desafiovotacao.v1.service;

import br.com.occ.desafiovotacao.v1.dto.AssociadoStatusDto;
import br.com.occ.desafiovotacao.v1.enums.CpfStatusEnum;
import br.com.occ.desafiovotacao.v1.model.Associado;
import br.com.occ.desafiovotacao.v1.repository.AssociadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class AssociadoService implements IAssociadoService{

    @Autowired
    AssociadoRepository repository;

    @Override
    public Optional<Associado> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Associado> findAllAtivos() {
        return repository.findAllByAtivoIs(true);
    }

    @Override
    public AssociadoStatusDto getStatusCpf(String cpf) {
        return new AssociadoStatusDto();
    }

}
