package com.desafiovotacao.service.interfaces;

import com.desafiovotacao.domain.VotoAssociado;

public interface IBuscarVotacaoService {

    VotoAssociado buscar(String associadoId, String secaoId);

}
