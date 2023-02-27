package br.tec.db.desafio.business.service;

import br.tec.db.desafio.api.v1.dto.associado.AssociadoRequestV1;
import br.tec.db.desafio.api.v1.dto.associado.AssociadoResponseV1;

public interface IAssociadoService {
    AssociadoResponseV1 criarUmNovoAssociado(AssociadoRequestV1 associadoRequestV1);

  }
