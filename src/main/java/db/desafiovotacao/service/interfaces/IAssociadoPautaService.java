package db.desafiovotacao.service.interfaces;

import db.desafiovotacao.dto.AssociadoPautaRequest;
import db.desafiovotacao.model.AssociadoPauta;


public interface IAssociadoPautaService {
    AssociadoPauta cadastarAssociadoNaPauta(AssociadoPautaRequest associadoPautaRequest);
}
