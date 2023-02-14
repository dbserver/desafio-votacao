package br.tec.db.desafio.business.service.implementation;

import br.tec.db.desafio.api.v1.dto.PautaMapperV1;
import br.tec.db.desafio.api.v1.dto.PautaRequestV1;
import br.tec.db.desafio.api.v1.dto.PautaResponseV1;
import br.tec.db.desafio.business.entity.Pauta;
import br.tec.db.desafio.business.repository.PautaRepository;
import br.tec.db.desafio.business.service.PautaService;
import org.springframework.stereotype.Service;

@Service
public class PautaServiceImpl implements PautaService {
    private final PautaRepository pautaRepository;

    public PautaServiceImpl(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    @Override
    public PautaResponseV1 criarUmaNovaPauta(PautaRequestV1 pautaRequestV1) {
        Pauta pautaToCreate = PautaMapperV1.pautaRequestV1ToPauta(
                pautaRequestV1
        );
        return PautaMapperV1.pautaToPautaResponseV1(
                pautaToCreate
        );
    }
}
