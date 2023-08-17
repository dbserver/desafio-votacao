package com.db.votacao.api.interfaces;

import com.db.votacao.api.model.Pauta;

import java.util.UUID;

public interface IPautaService {
    Pauta criarPauta(Pauta pauta);

    Pauta consultarPautaPorNome(String nomePauta);

    Pauta consultarPautaPorId(UUID idPauta);
}
