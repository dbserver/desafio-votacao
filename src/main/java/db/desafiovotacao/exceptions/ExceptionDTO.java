package db.desafiovotacao.exceptions;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ExceptionDTO {
    protected String titulo;
    protected int status;
    protected String detalhe;
    protected String mensagem;
    protected LocalDateTime dataHora;
}
