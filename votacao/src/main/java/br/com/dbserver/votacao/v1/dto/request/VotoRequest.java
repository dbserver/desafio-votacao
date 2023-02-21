package br.com.dbserver.votacao.v1.dto.request;

import br.com.dbserver.votacao.v1.enums.VotoEnum;
import br.com.dbserver.votacao.validator.CpfOuCnpj;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VotoRequest implements Serializable {

	@NotNull(message = "Pauta ID não pode ser null")
	private Long pautaId;

	@CpfOuCnpj
	private String documentoAssociado;

	@NotNull(message = "Voto não pode ser null : deve ser 'SIM' ou 'NAO'")
	private VotoEnum valor;
}