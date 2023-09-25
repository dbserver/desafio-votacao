package com.desafio.votacao.dto.request;

import java.io.Serializable;

import com.desafio.votacao.enums.VotoEnum;
import com.desafio.votacao.validator.CpfValidator;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VotoRequest implements Serializable {
 
	private static final long serialVersionUID = 1L;

	@NotNull(message = "Pauta ID não pode ser null")
	private Long pautaId;

	@CpfValidator
	private String cpf;

	@NotNull(message = "Voto não pode ser null : deve ser 'SIM' ou 'NAO'")
	private VotoEnum valor;

	public VotoRequest(@NotNull(message = "Pauta ID não pode ser null") Long pautaId, String cpf,
			@NotNull(message = "Voto não pode ser null : deve ser 'SIM' ou 'NAO'") VotoEnum valor) {
		this.pautaId = pautaId;
		this.cpf = cpf;
		this.valor = valor;
	}
	
	
}
