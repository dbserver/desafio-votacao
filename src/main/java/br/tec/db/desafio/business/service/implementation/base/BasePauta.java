package br.tec.db.desafio.business.service.implementation.base;



import br.tec.db.desafio.business.domain.Pauta;
import br.tec.db.desafio.business.service.implementation.validacao.pauta.AValidacaoCriarUmaNovaPauta;
import br.tec.db.desafio.repository.PautaRepository;

import java.util.List;

public class BasePauta {
    protected final PautaRepository pautaRepository;
    protected final List<AValidacaoCriarUmaNovaPauta> validacaoCriarUmaNovaPautaList;

    public BasePauta(PautaRepository pautaRepository,
                        List<AValidacaoCriarUmaNovaPauta> validacaoCriarUmaNovaPautaList) {
        this.pautaRepository = pautaRepository;
        this.validacaoCriarUmaNovaPautaList = FactoryBase.createAValidacaoCriarUmaNovaPauta();
    }
    protected void valida(Object obj){
        if (obj instanceof Pauta)
            this.validacaoCriarUmaNovaPautaList.forEach(v -> v.validarPauta((Pauta) obj));
    }
}
