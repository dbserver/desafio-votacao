package br.com.dbserver.votacao.v1.exception.dto;

import br.com.dbserver.votacao.v1.exception.dto.ExceptionDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ValidationExceptionDto extends ExceptionDto {
	private final String campo;
	private final String mensagemCampo;
}
