package db.desafiovotacao.service;

import db.desafiovotacao.dto.AssociadoPautaRequest;
import db.desafiovotacao.model.Associado;
import db.desafiovotacao.model.AssociadoPauta;
import db.desafiovotacao.model.Pauta;
import db.desafiovotacao.repository.AssociadoPautaRepository;
import db.desafiovotacao.service.interfaces.IAssociadoPautaService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AssociadoPautaService implements IAssociadoPautaService {

    private final AssociadoPautaRepository associadoPautaRepository;
    private final AssociadoService associadoService;
    private final PautaService pautaService;


    public AssociadoPautaService(AssociadoPautaRepository associadoPautaRepository, AssociadoService associadoService, PautaService pautaService){
        this.associadoPautaRepository = associadoPautaRepository;
        this.associadoService = associadoService;
        this.pautaService = pautaService;
    }

    @Override
    public AssociadoPauta cadastarAssociadoNaPauta(AssociadoPautaRequest associadoPautaRequest) {

        Associado associado = associadoService.buscarPorCPF(associadoPautaRequest.cpf());
        Pauta pauta = pautaService.buscarPautaPorID(associadoPautaRequest.idPauta());

        Optional<AssociadoPauta> associadoPauta = associadoPautaRepository.findByAssociadoAndPauta(associado, pauta);

        if (associadoPauta.isPresent())
            throw new RuntimeException("associado ja cadastrado na pauta"); // TODO exception

        return associadoPautaRepository.save(new AssociadoPauta(associado, pauta));
    }

}
