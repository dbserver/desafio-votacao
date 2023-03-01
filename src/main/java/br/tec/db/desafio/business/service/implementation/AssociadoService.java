package br.tec.db.desafio.business.service.implementation;

import br.tec.db.desafio.api.v1.dto.associado.AssociadoMapperV1;
import br.tec.db.desafio.api.v1.dto.associado.AssociadoRequestV1;
import br.tec.db.desafio.api.v1.dto.associado.AssociadoResponseV1;
import br.tec.db.desafio.business.domain.Associado;
import br.tec.db.desafio.business.service.IAssociadoService;
import br.tec.db.desafio.business.service.implementation.base.BaseAssociado;
import br.tec.db.desafio.repository.AssociadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssociadoService extends BaseAssociado implements IAssociadoService {


    public AssociadoService(AssociadoRepository associadoRepository) {
        super(associadoRepository);
    }

    @Override
    public AssociadoResponseV1 criarUmNovoAssociado(AssociadoRequestV1 associadoRequestV1) {
        valida.validarCpf(associadoRequestV1);
        Associado associadoToCreate = AssociadoMapperV1.associadoRequestV1ToAssociado(
                associadoRequestV1
        );
        Associado verificaAssociado = associadoRepository.findAssociadoByCpf(associadoToCreate.getCpf());
        valida.validarNaoPodeSerNulo(verificaAssociado);
        return AssociadoMapperV1.associadoToAssociadoResponseV1(
                associadoRepository.save(associadoToCreate)
        );


    }


}
