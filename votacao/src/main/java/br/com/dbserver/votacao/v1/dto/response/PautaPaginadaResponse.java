package br.com.dbserver.votacao.v1.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
@AllArgsConstructor
@Getter
@Builder
public class PautaPaginadaResponse {
	private int totalPaginas;
	private List<PautaResponse> pataResponses;
}
