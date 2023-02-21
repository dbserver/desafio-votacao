package br.com.dbserver.votacao.v1.dto.response;

import br.com.dbserver.votacao.v1.enums.PautaStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PautaResponse {
	private Long id;
	private String descricao;
	private List<PautaVotoResponse> votos;
	private LocalDateTime inicio;
	private LocalDateTime fim;
	private PautaStatusEnum status;
}
