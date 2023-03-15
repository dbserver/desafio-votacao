package db.desafiovotacao.service;

import db.desafiovotacao.model.*;
import db.desafiovotacao.repository.PautaRepository;
import db.desafiovotacao.service.interfaces.IPautaService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PautaService implements IPautaService {

    private final PautaRepository pautaRepository;
    private final VotoPautaService votoPautaService;

    private final SessaoService sessaoService;

    
    public PautaService(PautaRepository pautaRepository, VotoPautaService votoPautaService, SessaoService sessaoService){
        this.pautaRepository = pautaRepository;
        this.votoPautaService = votoPautaService;
        this.sessaoService = sessaoService;
    }

    @Override
    public Pauta cadastrarPauta(Pauta pauta) {

        Sessao sessao = pauta.getSessao();

        pauta.setSessao(sessaoService.validarSessao(sessao));

        return pautaRepository.save(pauta);
    }

    @Override
    public Pauta buscarPautaPorID(UUID uuidPauta) {
        Optional<Pauta> pauta = pautaRepository.findById(uuidPauta);
        return pauta.orElseGet(pauta::get); // TODO tratar exception
    }

    public ResultadoVotacao resultadoVotacao(UUID uuidPauta){

        Optional<Pauta> pauta = pautaRepository.findById(uuidPauta);

        if (pauta.isEmpty())
            throw new RuntimeException("pauta nao existe");

        Integer votosPositivos = votoPautaService.contagemVotosPositivos(pauta.get());

        Integer votosNegativos = votoPautaService.contagemVotosNegativos(pauta.get());

        return new ResultadoVotacao(uuidPauta, votosPositivos, votosNegativos);
    }

}
