package com.db.votacao.api.interfaces;

import com.db.votacao.api.model.Pauta;

public interface IPautaService {
    Pauta criarPauta(Pauta pauta);

    Pauta consultarPautaPorNome(String nomePauta);
}
