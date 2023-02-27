package br.tec.db.desafio.business.service.implementation;

import br.tec.db.desafio.api.v1.dto.pauta.PautaMapperV1;
import br.tec.db.desafio.api.v1.dto.pauta.PautaRequestV1;
import br.tec.db.desafio.api.v1.dto.pauta.PautaResponseV1;
import br.tec.db.desafio.business.domain.Pauta;
import br.tec.db.desafio.business.service.IPautaService;
import br.tec.db.desafio.business.service.implementation.validacao.pauta.ValidacaoPauta;
import br.tec.db.desafio.exception.BusinessException;
import br.tec.db.desafio.repository.PautaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PautaService implements IPautaService {
    private final PautaRepository pautaRepository;
    private final List<ValidacaoPauta> validacoesPauta;
    public PautaService(PautaRepository pautaRepository, List<ValidacaoPauta> validacoesPauta) {
        this.pautaRepository = pautaRepository;
        this.validacoesPauta = validacoesPauta;
    }
    @Override
    public PautaResponseV1 criarUmaNovaPauta(PautaRequestV1 pautaRequestV1) {
        this.validacoesPauta.forEach(v -> v.validarPauta(pautaRequestV1));

        Pauta pautaToCreate = PautaMapperV1.pautaRequestV1ToPauta(
                pautaRequestV1
        );
        Pauta verificaPauta = pautaRepository.findPautaByAssunto(pautaRequestV1.getAssunto());
        if(verificaPauta!=null){
            throw new BusinessException("Já existe uma pauta com este tema");
        }
        return PautaMapperV1.pautaToPautaResponseV1(
                pautaRepository.save(pautaToCreate)
        );
    }
}
