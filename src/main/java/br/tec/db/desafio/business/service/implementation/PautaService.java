package br.tec.db.desafio.business.service.implementation;

import br.tec.db.desafio.api.v1.dto.pauta.PautaMapperV1;
import br.tec.db.desafio.api.v1.dto.pauta.PautaRequestV1;
import br.tec.db.desafio.api.v1.dto.pauta.PautaResponseV1;
import br.tec.db.desafio.business.domain.Pauta;
import br.tec.db.desafio.business.service.IPautaService;
import br.tec.db.desafio.business.service.implementation.base.BasePauta;
import br.tec.db.desafio.repository.PautaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PautaService extends BasePauta implements IPautaService {


    public PautaService(PautaRepository pautaRepository) {
        super(pautaRepository);
    }

    @Override
    public PautaResponseV1 criarUmaNovaPauta(PautaRequestV1 pautaRequestV1) {

        Pauta pautaToCreate = PautaMapperV1.pautaRequestV1ToPauta(
                pautaRequestV1
        );
        Pauta verificaPauta = pautaRepository.findPautaByAssunto(pautaRequestV1.getAssunto());
        valida.validarJaExistente(verificaPauta);

        return PautaMapperV1.pautaToPautaResponseV1(
                pautaRepository.save(pautaToCreate)
        );
    }


}
