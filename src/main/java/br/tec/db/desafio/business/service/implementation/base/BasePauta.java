package br.tec.db.desafio.business.service.implementation.base;



import br.tec.db.desafio.business.domain.Pauta;
import br.tec.db.desafio.business.service.implementation.validacao.pauta.AValidacaoCriarUmaNovaPauta;
import br.tec.db.desafio.business.service.implementation.validacao.pauta.ValidarPautaComPoucoCaracter;
import br.tec.db.desafio.business.service.implementation.validacao.pauta.ValidarPautaJaExistente;
import br.tec.db.desafio.business.service.implementation.validacao.pauta.ValidarPautaVazia;
import br.tec.db.desafio.repository.PautaRepository;

import java.util.List;

public class BasePauta {
    protected final PautaRepository pautaRepository;
    protected final List<AValidacaoCriarUmaNovaPauta> validacaoCriarUmaNovaPautaList;
    protected final ValidarPautaComPoucoCaracter validarPautaComPoucoCaracter = new ValidarPautaComPoucoCaracter();
    protected final ValidarPautaJaExistente validarPautaJaExistente = new ValidarPautaJaExistente();
    protected final ValidarPautaVazia validarPautaVazia = new ValidarPautaVazia();

    public BasePauta(PautaRepository pautaRepository,
                        List<AValidacaoCriarUmaNovaPauta> validacaoCriarUmaNovaPautaList) {
        this.pautaRepository = pautaRepository;
        this.validacaoCriarUmaNovaPautaList =
                List.of(validarPautaJaExistente, validarPautaComPoucoCaracter, validarPautaVazia);
    }
    protected void valida(Object obj){
        if (obj instanceof Pauta)
            this.validacaoCriarUmaNovaPautaList.forEach(v -> v.validarPauta((Pauta) obj));
    }
}
