package db.desafiovotacao.service;

import db.desafiovotacao.dto.AssociadoPautaRequest;
import db.desafiovotacao.exceptions.ConflictException;
import db.desafiovotacao.exceptions.NotFoundException;
import db.desafiovotacao.model.Associado;
import db.desafiovotacao.model.AssociadoPauta;
import db.desafiovotacao.model.Pauta;
import db.desafiovotacao.repository.AssociadoPautaRepository;
import db.desafiovotacao.service.interfaces.IAssociadoPautaService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    @Transactional
    public AssociadoPauta cadastarAssociadoNaPauta(AssociadoPautaRequest associadoPautaRequest) {

        Associado associado = associadoService.buscarPorCPF(associadoPautaRequest.cpf());
        Pauta pauta = pautaService.buscarPautaPorID(associadoPautaRequest.idPauta());
        AssociadoPauta associadoPauta = buscarAssociadoPauta(associado, pauta);

        if (associadoPauta != null)
            throw new ConflictException("Associado já cadastrado na pauta!");

        return associadoPautaRepository.save(
                AssociadoPauta.builder()
                        .associado(associado)
                        .pauta(pauta)
                        .votou(false)
                        .build()
        );
    }

    public AssociadoPauta buscarAssociadoPauta(Associado associado, Pauta pauta){

        Optional<AssociadoPauta> associadoPauta = associadoPautaRepository.findByAssociadoAndPauta(associado, pauta);

        if (associadoPauta.isEmpty())
            throw new NotFoundException("Associado não esta cadastrado na pauta!");

        return associadoPauta.get();
    }

}
