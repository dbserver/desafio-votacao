package br.com.dbserver.votacao.v1.exception.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class ExceptionDto {

    protected String titulo;
    protected int status;
    protected String detalhe;
    protected String mensagem;
    protected LocalDateTime dataHora;

}
