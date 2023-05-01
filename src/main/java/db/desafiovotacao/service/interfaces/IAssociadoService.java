package db.desafiovotacao.service.interfaces;

import db.desafiovotacao.model.*;

public interface IAssociadoService {
    Associado criarAssociado(Associado associado);
    Associado buscarPorCPF(String CPF);

}
