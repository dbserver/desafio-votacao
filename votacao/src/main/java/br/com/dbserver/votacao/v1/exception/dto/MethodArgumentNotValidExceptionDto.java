package br.com.dbserver.votacao.v1.exception.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class MethodArgumentNotValidExceptionDto extends ExceptionDto {
	private final String campo;
	private final String mensagemCampo;
}
