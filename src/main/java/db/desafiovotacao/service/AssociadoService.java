package db.desafiovotacao.service;

import db.desafiovotacao.model.Associado;
import db.desafiovotacao.repository.AssociadoRepository;
import db.desafiovotacao.service.interfaces.IAssociadoService;
import org.springframework.stereotype.Service;

@Service
public class AssociadoService implements IAssociadoService {

    private final AssociadoRepository associadoRepository;

    public AssociadoService(AssociadoRepository associadoRepository){
        this.associadoRepository = associadoRepository;
    }

    @Override
    public void validaCPF(Associado associado) {

    }

    @Override
    public Associado criarAssociado(Associado associado) {
        return associadoRepository.save(associado);
    }
}
