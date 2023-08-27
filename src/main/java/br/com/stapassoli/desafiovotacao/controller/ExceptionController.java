package br.com.stapassoli.desafiovotacao.controller;

import br.com.stapassoli.desafiovotacao.dto.VotacaoExceptionDTO;
import br.com.stapassoli.desafiovotacao.exceptions.VotacaoApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {VotacaoApiException.class})
    protected ResponseEntity<VotacaoExceptionDTO> handleConflict(RuntimeException exception, WebRequest request) {
        String mensagemErro = exception.getMessage();
        return ResponseEntity.badRequest().body(VotacaoExceptionDTO.builder().mensagemErro(mensagemErro).build());
    }

}
