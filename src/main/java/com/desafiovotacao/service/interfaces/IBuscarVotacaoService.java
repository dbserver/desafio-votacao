package com.desafiovotacao.service.interfaces;

import com.desafiovotacao.domain.VotoAssociado;

import java.util.List;

public interface IBuscarVotacaoService {

    VotoAssociado buscarPorAssociadoAndSessao(String associadoId, String secaoId);

    List<VotoAssociado> buscarTodosPorPauta(String pautaId);

}
