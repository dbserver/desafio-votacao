package br.com.dbserver.votacao.v1.dto.response;

import br.com.dbserver.votacao.v1.enums.VotoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VotoResponse {
	private Long id;
	private PautaResponse pauta;
	private AssociadoResponse associado;
	private VotoEnum valor;
}
