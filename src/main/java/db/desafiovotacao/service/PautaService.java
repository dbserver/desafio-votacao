package db.desafiovotacao.service;

import db.desafiovotacao.model.*;
import db.desafiovotacao.repository.PautaRepository;
import db.desafiovotacao.service.interfaces.IPautaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PautaService implements IPautaService {

    private final PautaRepository pautaRepository;

    private final SessaoService sessaoService;

    
    public PautaService(PautaRepository pautaRepository, SessaoService sessaoService){
        this.pautaRepository = pautaRepository;
        this.sessaoService = sessaoService;
    }

    @Override
    public Pauta cadastrarPauta(Pauta pauta) {

        Sessao sessao = pauta.getSessao();

        pauta.setSessao(sessaoService.validarSessao(sessao));

        return pautaRepository.save(pauta);
    }

    @Override
    public Pauta buscarPautaPorID(Long id) {
        Optional<Pauta> pauta = pautaRepository.findById(id);
        return pauta.orElseGet(pauta::get); // TODO tratar exception
    }

    public Page<Pauta> listarPautas(Pageable pageable){
        return pautaRepository.findAll(pageable);
    }

}
