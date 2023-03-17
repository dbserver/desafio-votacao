package db.desafiovotacao.service;

import db.desafiovotacao.model.Associado;
import db.desafiovotacao.repository.AssociadoRepository;
import db.desafiovotacao.service.interfaces.IAssociadoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssociadoService implements IAssociadoService {

    private final AssociadoRepository associadoRepository;

    public AssociadoService(AssociadoRepository associadoRepository){
        this.associadoRepository = associadoRepository;
    }

    @Override
    @Transactional
    public Associado criarAssociado(Associado associado) {

        Optional<Associado> optionalAssociado = associadoRepository.findByCPF(associado.getCPF());

        if (optionalAssociado.isPresent())
            throw new RuntimeException("Associado ja cadastrado"); // TODO exception

        return associadoRepository.save(associado);
    }

    @Override
    public Associado buscarPorCPF(String cpf){

        Optional<Associado> associado = associadoRepository.findByCPF(cpf);

        if (associado.isEmpty())
            throw new RuntimeException("associado não cadastrado"); // TODO exception

        return associado.get();
    }


}
