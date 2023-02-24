package db.desafiovotacao.service;

import db.desafiovotacao.model.Pauta;
import db.desafiovotacao.repository.PautaRepository;
import db.desafiovotacao.service.interfaces.IPautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PautaService implements IPautaService {

    private final PautaRepository pautaRepository;

    @Autowired
    public PautaService(PautaRepository pautaRepository){
        this.pautaRepository = pautaRepository;
    }


    @Override
    public Pauta criarPauta(Pauta pauta) {
        return pautaRepository.save(pauta);
    }
}
