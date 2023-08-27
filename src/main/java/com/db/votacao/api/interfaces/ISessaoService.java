package com.db.votacao.api.interfaces;

import com.db.votacao.api.model.Sessao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ISessaoService {
    Sessao criarSessao(Sessao sessao);

    Sessao consultarSessaoPorId(UUID idSessao);

    List<Sessao> consultarSessoesPorFiltros(LocalDateTime dataCriacao, LocalDateTime inicioSessao, LocalDateTime finalSessao);
}
