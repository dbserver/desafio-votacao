package br.com.dbserver.votacao.v1.mapper;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
public class Resposta<T> {
	private int totalPaginas;
	private List<T> lista;
}