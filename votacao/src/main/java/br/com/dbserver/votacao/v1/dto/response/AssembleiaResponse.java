package br.com.dbserver.votacao.v1.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AssembleiaResponse {
	private Long id;
	private LocalDateTime inicio;
	private LocalDateTime fim;
	List<PautaResponse> pautas;
}
