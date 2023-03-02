package db.desafiovotacao.service;

import db.desafiovotacao.model.Voto;
import db.desafiovotacao.repository.VotoRepository;
import db.desafiovotacao.service.interfaces.IVotoService;
import org.springframework.stereotype.Service;

@Service
public class VotoService implements IVotoService {

    private final VotoRepository votoRepository;

    public VotoService(VotoRepository votoRepository){
        this.votoRepository = votoRepository;
    }

    @Override
    public Voto criarVoto(Voto voto) {
        return votoRepository.save(voto);
    }
}
