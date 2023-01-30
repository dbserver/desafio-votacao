package br.com.occ.desafiovotacao.v1.service;

import br.com.occ.desafiovotacao.config.exception.ServiceException;
import br.com.occ.desafiovotacao.v1.dto.AssociadoStatusDto;
import br.com.occ.desafiovotacao.v1.enums.CpfStatusEnum;
import br.com.occ.desafiovotacao.v1.model.Associado;
import br.com.occ.desafiovotacao.v1.repository.AssociadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class AssociadoService implements IAssociadoService{

    @Autowired
    AssociadoRepository repository;

    @Override
    public Associado save(Associado associado) {
        if (repository.existsByCpf(associado.getCpf()))
            throw new ServiceException("CPF já cadastrado", HttpStatus.CONFLICT);
        return repository.save(associado);
    }

    @Override
    public Associado findById(Long id) {
        Optional<Associado> associadoOptional = repository.findById(id);
        return associadoOptional.orElseThrow(() -> new ServiceException("Associado não localizado", HttpStatus.BAD_REQUEST));
    }

    @Override
    public List<Associado> findAllAtivos() {
        List<Associado> associadosAtivos = repository.findAllByAtivoIs(true);
        if (associadosAtivos.isEmpty())
            throw new ServiceException("Não foi encontrado associados ativos", HttpStatus.BAD_REQUEST);
        return associadosAtivos;
    }

    @Override
    public AssociadoStatusDto getStatusCpf(String cpf) {
        return new AssociadoStatusDto();
    }

}
