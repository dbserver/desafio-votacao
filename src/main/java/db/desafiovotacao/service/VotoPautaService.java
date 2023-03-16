package db.desafiovotacao.service;

import db.desafiovotacao.enums.EnumVoto;
import db.desafiovotacao.model.*;
import db.desafiovotacao.repository.AssociadoPautaRepository;
import db.desafiovotacao.repository.VotoPautaRepository;
import db.desafiovotacao.service.interfaces.IVotoPautaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VotoPautaService implements IVotoPautaService {

    private final VotoPautaRepository votoPautaRepository;

    public VotoPautaService(VotoPautaRepository votoPautaRepository){
        this.votoPautaRepository = votoPautaRepository;
    }

    public VotoPauta cadastrarVotoPauta(VotoPauta votoPauta){
        return votoPautaRepository.save(votoPauta);
    }

    public Integer contagemVotos(Pauta pauta){
        List<VotoPauta> votosPauta = votoPautaRepository.findByPauta(pauta);
        return votosPauta.size();
    }


    public Integer contagemVotosPositivos(Pauta pauta){

        List<VotoPauta> votosPauta = votoPautaRepository.findByPauta(pauta);

        List<Voto> votos = votosPauta.stream().map(VotoPauta::getVoto).toList();

        return votos.stream().filter(v -> v.getVoto() == EnumVoto.SIM).toList().size();
    }

}
