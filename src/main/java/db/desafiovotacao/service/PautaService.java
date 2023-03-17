package db.desafiovotacao.service;

import db.desafiovotacao.model.*;
import db.desafiovotacao.repository.PautaRepository;
import db.desafiovotacao.service.interfaces.IPautaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class PautaService implements IPautaService {

    private final PautaRepository pautaRepository;

    private final SessaoService sessaoService;

    @Autowired
    public PautaService(PautaRepository pautaRepository, SessaoService sessaoService){
        this.pautaRepository = pautaRepository;
        this.sessaoService = sessaoService;
    }

    @Transactional
    @Override
    public Pauta cadastrarPauta(Pauta pauta) {

        Sessao sessao = pauta.getSessao();

        pauta.setSessao(sessaoService.validarSessao(sessao));

        return pautaRepository.save(
                Pauta.builder()
                        .titulo(pauta.getTitulo())
                        .descricao(pauta.getDescricao())
                        .dataCriacao(LocalDateTime.now())
                        .sessao(pauta.getSessao())
                        .ativo(true)
                        .build()
        );
    }

    @Override
    public Pauta buscarPautaPorID(Long id) {

        Optional<Pauta> pauta = pautaRepository.findById(id);

        if (pauta.isEmpty())
            throw new RuntimeException("pauta inexistente"); // TODO exception

        return pauta.get();
    }

    @Override
    public Page<Pauta> listarPautas(Pageable pageable){
        return pautaRepository.findAllByAtivoTrue(pageable);
    }

    @Transactional
    @Override
    public Pauta deletarPauta(Long id){

        Optional<Pauta> optionalPauta = pautaRepository.findById(id);

        if (optionalPauta.isEmpty())
            return null;

        Pauta pauta = optionalPauta.get();

        pauta.setAtivo(false);

        return pauta;
    }

    @Transactional
    @Override
    public Pauta atualizarPauta(Pauta pauta) {

        Pauta pautaAtualizada = buscarPautaPorID(pauta.getId());

        pautaAtualizada.setTitulo(pauta.getTitulo());
        pautaAtualizada.setDescricao(pauta.getDescricao());
        pautaAtualizada.setSessao(pauta.getSessao());

        return pautaAtualizada;
    }

}
