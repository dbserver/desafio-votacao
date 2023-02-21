package br.com.dbserver.votacao.v1.dto.response;

import br.com.dbserver.votacao.v1.enums.PautaStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PautaResultadoResponse {
	private Long id;
	private String descricao;
	private Long votoSim;
	private Long votoNao;
	private PautaStatusEnum status;
}