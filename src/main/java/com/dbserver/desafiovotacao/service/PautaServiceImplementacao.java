package com.dbserver.desafiovotacao.service;

import com.dbserver.desafiovotacao.dto.ClienteRequest;
import com.dbserver.desafiovotacao.dto.PautaRequest;
import com.dbserver.desafiovotacao.enums.PautaAndamentoEnum;
import com.dbserver.desafiovotacao.enums.PautaResultadoEnum;
import com.dbserver.desafiovotacao.enums.VotoEnum;
import com.dbserver.desafiovotacao.model.Pauta;
import com.dbserver.desafiovotacao.model.Votante;
import com.dbserver.desafiovotacao.repository.PautaRepositorio;

import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PautaServiceImplementacao implements PautaService {

    private final VotanteService votanteService;
    private final PautaRepositorio pautaRepositorio;

    @Autowired
    public PautaServiceImplementacao(VotanteService votanteService, PautaRepositorio pautaRepositorio) {
        this.votanteService = votanteService;
        this.pautaRepositorio = pautaRepositorio;
    }

    @Override
    public Optional<Pauta> encontrarPautaPorID(UUID id) throws DataAccessException {
        return pautaRepositorio.findById(id);
    }
    
    @Override
    public Optional<Pauta> encontrarPautaPorHash(String hash) throws DataAccessException {
        return pautaRepositorio.findByHash(hash);
    }

    @Override
    public Pauta salvarPauta(PautaRequest pautaRequest) throws DataAccessException {
        Optional<Votante> buscaAutor = votanteService.encontrarVotantePorID(pautaRequest.idAutor());
        return pautaRepositorio.save(Pauta.builder().titulo(pautaRequest.titulo())
                        .autorPauta(buscaAutor.get()).hash(pautaRequest.hash()).andamento(PautaAndamentoEnum.APURANDO).build());
    }
    
    @Override
    public Iterable<Pauta> mostraPautas(){
        return pautaRepositorio.findAll();
    }

    @Override
    public Integer totalVotantes(UUID id) {
        Optional<Pauta> pautaOpcional = pautaRepositorio.findById(id);
        if (pautaOpcional.isEmpty()) {
            return 0;
        }
        return pautaOpcional.get().getAssociados().size();
    }

    @Override
    public Pauta adicionarAssociado(String hashPauta, ClienteRequest clienteRequest){
        Optional<Pauta> pauta = encontrarPautaPorHash(hashPauta);
        Optional<Votante> votante = votanteService.encontrarVotantePorID(clienteRequest.id());
        if(pauta.isEmpty()|| votante.isEmpty())
            return null;

        pauta.get().getAssociados().add(votante.get());
        return pautaRepositorio.save(pauta.get());
    }

    @Override
    public Pauta finalizarPauta(String hash) {
        Optional<Pauta> pautaOpcional = encontrarPautaPorHash(hash);
        if (pautaOpcional.isEmpty())
            return null;

        Pauta pauta = pautaOpcional.get();
        Integer contSim = pauta.getAssociados().stream()
                .filter(associado -> associado.getVoto() == VotoEnum.SIM)
                .mapToInt(i -> 1).sum();
        Integer totalVotos = pauta.getAssociados().size();

        if (totalVotos == 0) {
            pauta.setAndamento(PautaAndamentoEnum.APURANDO);
            return pauta;
        } else if (contSim >= (totalVotos / 2) + 1) {
            pauta.setResultado(PautaResultadoEnum.APROVADO);
        } else {
            pauta.setResultado(PautaResultadoEnum.INDEFERIDO);
        }

        pauta.setAndamento(PautaAndamentoEnum.CONCLUIDO);
        return pautaRepositorio.save(pauta);
    }
}
