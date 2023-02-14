package br.com.dbserver.votacao.v1.handler;

import java.util.List;

public record ApiErro(String mensagem, List<ApiErroDetalhe> erros) {
}
