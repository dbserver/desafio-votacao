package db.desafiovotacao;

import db.desafiovotacao.exceptions.ConflictException;
import db.desafiovotacao.exceptions.NoContentException;
import db.desafiovotacao.exceptions.NotFoundException;
import db.desafiovotacao.exceptions.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDTO> handlerNotFoundException(NotFoundException exception) {
        return new ResponseEntity<>(
                ExceptionDTO.builder()
                        .dataHora(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .titulo("Not Found Exception")
                        .detalhe(exception.getMessage())
                        .mensagem("Cheque a documentação da API")
                        .build(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ExceptionDTO> handlerNotFoundException(ConflictException exception) {
        return new ResponseEntity<>(
                ExceptionDTO.builder()
                        .dataHora(LocalDateTime.now())
                        .status(HttpStatus.CONFLICT.value())
                        .titulo("Conflict Exception")
                        .detalhe(exception.getMessage())
                        .mensagem("Cheque a documentação da API")
                        .build(),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<ExceptionDTO> handlerNotFoundException(NoContentException exception) {
        return new ResponseEntity<>(
                ExceptionDTO.builder()
                        .dataHora(LocalDateTime.now())
                        .status(HttpStatus.CONFLICT.value())
                        .titulo("No Content Exception")
                        .detalhe(exception.getMessage())
                        .mensagem("Cheque a documentação da API")
                        .build(),
                HttpStatus.CONFLICT
        );
    }
}
