package db.desafiovotacao.service.interfaces;

import db.desafiovotacao.model.*;

import java.util.UUID;

public interface IPautaService {
    Pauta cadastrarPauta(Pauta pauta);

    Pauta buscarPautaPorID(Long id);
}
