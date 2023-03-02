package db.desafiovotacao.service.interfaces;

import db.desafiovotacao.model.Associado;

public interface IAssociadoService {
    void validaCPF(Associado associado);
    Associado criarAssociado(Associado associado);
}
