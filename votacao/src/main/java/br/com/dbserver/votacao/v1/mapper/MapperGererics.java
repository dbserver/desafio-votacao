package br.com.dbserver.votacao.v1.mapper;

import org.springframework.data.domain.Page;

import java.util.function.Function;

public class MapperGererics<T, R> {
	public MapperGererics() {
	}
	/**
	 * Esta classe converte um tipo de objeto pagable em outro mais amigavel
	 * @param page  Pagable que será coonvertido
	 * @param mapper  Método responsavel pela conversão
	 * @return  Será retornado o mesmo tipo que for retornado do @param mapper
	 */
	public Resposta<R> toPagina(Page<T> page, Function<T, R> mapper) {
		Resposta<R> resposta = new Resposta<>();
		resposta.setLista(page.getContent().stream()
				.map(mapper).toList());
		resposta.setTotalPaginas(page.getTotalPages());
		return resposta;
	}
}
