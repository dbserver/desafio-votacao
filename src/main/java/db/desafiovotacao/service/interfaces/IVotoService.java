package db.desafiovotacao.service.interfaces;

import db.desafiovotacao.model.*;

public interface IVotoService {
    VotoPauta cadastrarVoto(VotoPauta votoPauta, String cpf);

}
