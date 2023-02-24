package br.tec.db.desafio.business.service.implementation;

import br.tec.db.desafio.api.v1.dto.associado.AssociadoMapperV1;
import br.tec.db.desafio.api.v1.dto.associado.AssociadoRequestV1;
import br.tec.db.desafio.api.v1.dto.associado.AssociadoResponseV1;
import br.tec.db.desafio.api.v1.dto.pauta.PautaMapperV1;
import br.tec.db.desafio.api.v1.dto.pauta.PautaRequestV1;
import br.tec.db.desafio.api.v1.dto.pauta.PautaResponseV1;
import br.tec.db.desafio.business.domain.Associado;
import br.tec.db.desafio.business.domain.Pauta;
import br.tec.db.desafio.business.service.AssociadoService;
import br.tec.db.desafio.exception.BusinessException;
import br.tec.db.desafio.repository.AssociadoRepository;
import org.springframework.stereotype.Service;

@Service
public class AssociadoServiceImpl implements AssociadoService {
    private final AssociadoRepository associadoRepository;

    public AssociadoServiceImpl(AssociadoRepository associadoRepository) {
        this.associadoRepository = associadoRepository;
    }
    @Override
    public AssociadoResponseV1 criarUmNovoAssociado(AssociadoRequestV1 associadoRequestV1) {
        Associado associadoToCreate = AssociadoMapperV1.associadoRequestV1ToAssociado(
                associadoRequestV1
        );
        Associado verificaAssociado = associadoRepository.findAssociadoByCpf(associadoToCreate.getCpf());
        if(verificaAssociado!=null){
            throw new BusinessException("Associado já está cadastrado");
        }

        return AssociadoMapperV1.associadoToAssociadoResponseV1(
                associadoRepository.save(associadoToCreate)
        );
    }
}
