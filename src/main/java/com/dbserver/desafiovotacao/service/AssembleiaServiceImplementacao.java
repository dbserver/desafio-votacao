
package com.dbserver.desafiovotacao.service;

import com.dbserver.desafiovotacao.dto.AssembleiaRequest;
import com.dbserver.desafiovotacao.dto.AssembleiaResponse;
import com.dbserver.desafiovotacao.dto.ClienteRequest;
import com.dbserver.desafiovotacao.enums.AssembleiaEnum;
import com.dbserver.desafiovotacao.enums.PautaAndamentoEnum;
import com.dbserver.desafiovotacao.model.Assembleia;
import com.dbserver.desafiovotacao.model.Pauta;
import com.dbserver.desafiovotacao.repository.AssembleiaRepositorio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class AssembleiaServiceImplementacao implements AssembleiaService{

    private final PautaService pautaService;
    private final AssembleiaRepositorio assembleiaRepositorio;
    
    @Autowired
    public AssembleiaServiceImplementacao(AssembleiaRepositorio assembleiaRepositorio, PautaService pautaService) {
        this.assembleiaRepositorio = assembleiaRepositorio;
        this.pautaService = pautaService;
    }
    @Override
    public Optional<Assembleia> encontrarAssembleiaPorID(UUID id) throws DataAccessException {
        return assembleiaRepositorio.findById(id);
    }

    @Override
    public Assembleia salvarAssembleia(AssembleiaRequest assembleiaRequest) throws DataAccessException {
       return assembleiaRepositorio.save(Assembleia.builder().nomeAssembleia(assembleiaRequest.nomeAssembleia())
               .aberturaAssembleia(LocalDateTime.now()).status(AssembleiaEnum.MOVIMENTO)
               .listaPauta(new ArrayList<>()).build());
    }

    @Override
    public Integer totalPautas(UUID id) {
       Optional<Assembleia> assembleiaOpcional = encontrarAssembleiaPorID(id);
        if (assembleiaOpcional.isEmpty()) {
            return 0;
        }
        return assembleiaOpcional.get().getListaPauta().size();
    }

    @Override
    public Iterable<Assembleia> mostraTudo(){
        return assembleiaRepositorio.findAll();
    }

    @Override
    public Iterable<Pauta> mostraPautas(UUID id) {
        Optional<Assembleia> assembleiaOpcional = encontrarAssembleiaPorID(id);
        return assembleiaOpcional.get().getListaPauta();
    }

    @Override
    public Assembleia adicionarPauta(UUID idAssembleia, ClienteRequest clienteRequest){
        Optional<Assembleia> assembleiaOpcional = encontrarAssembleiaPorID(idAssembleia);
        Optional<Pauta> pautaOptional = pautaService.encontrarPautaPorID(clienteRequest.id());
        if (assembleiaOpcional.isPresent() && pautaOptional.isPresent()) {
            Pauta pauta = pautaOptional.get();
            if (pauta.getAndamento() == null) {
                pauta.setAndamento(PautaAndamentoEnum.APURANDO);
            }
            if (assembleiaOpcional.get().getListaPauta().contains(pauta)) {
                return assembleiaOpcional.get();
            }
            assembleiaOpcional.get().getListaPauta().add(pauta);
            return assembleiaRepositorio.save(assembleiaOpcional.get());
        }
        return null;
    }

    @Override
    public List<AssembleiaResponse> mostrarAssembleias(){
        Iterable<Assembleia> assembleias = assembleiaRepositorio.findAll();
        return StreamSupport.stream(assembleias.spliterator(), false)
                .map(AssembleiaResponse::new)
                .collect(Collectors.toList());

    }

    @Override
    public Assembleia finalizarAssembleia(UUID id){
        Optional<Assembleia> assembleiaOpcional = encontrarAssembleiaPorID(id);
        if (!assembleiaOpcional.isPresent()) {
            return null;
        }

        Assembleia assembleia = assembleiaOpcional.get();
        if (assembleia.getStatus() == AssembleiaEnum.FINALIZADO) {
            return assembleia;
        }

        boolean pautasConcluidas = assembleia.getListaPauta().stream()
                .allMatch(pauta -> pauta.getAndamento() == PautaAndamentoEnum.CONCLUIDO);

        if (!pautasConcluidas) {
            return assembleia;
        }
        atualizarStatus(assembleia);
        return assembleiaRepositorio.save(assembleia);

    }

    private void atualizarStatus(Assembleia assembleia) {
        assembleia.setStatus(AssembleiaEnum.FINALIZADO);
        assembleia.setEncerramentoAssembleia(LocalDateTime.now());
    }
}
