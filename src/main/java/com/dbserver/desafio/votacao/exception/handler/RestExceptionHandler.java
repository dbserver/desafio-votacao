package com.dbserver.desafio.votacao.exception.handler;

import com.dbserver.desafio.votacao.exception.PautaInexistenteException;
import com.dbserver.desafio.votacao.exception.PautaSemSessaoException;
import com.dbserver.desafio.votacao.exception.PautaSemVotoException;
import com.dbserver.desafio.votacao.exception.SessaoFinalizadaException;
import com.dbserver.desafio.votacao.exception.VotoJaRealizadoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(VotoJaRealizadoException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErroNotificacao> handleVotoJaRealizadoException(VotoJaRealizadoException e) {

        return getErroNotificacaoResponseEntity(e.getMessage());
    }

    @ExceptionHandler(SessaoFinalizadaException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErroNotificacao> handleSessaoFinalizadaException(SessaoFinalizadaException e) {

        return getErroNotificacaoResponseEntity(e.getMessage());
    }

    @ExceptionHandler(PautaSemSessaoException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErroNotificacao> handlePautaSemSessaoException(PautaSemSessaoException e) {

        return getErroNotificacaoResponseEntity(e.getMessage());
    }

    @ExceptionHandler(PautaInexistenteException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErroNotificacao> handlePautaInexistenteException(PautaInexistenteException e) {

        return getErroNotificacaoResponseEntity(e.getMessage());
    }

    @ExceptionHandler(PautaSemVotoException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErroNotificacao> handlePautaSemVotoException(PautaSemVotoException e) {

        return getErroNotificacaoResponseEntity(e.getMessage());
    }

    @ExceptionHandler(value = {Exception.class, ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErroNotificacao> handleInternalServerErrorException(Exception e) {

        return getErroNotificacaoResponseEntity(e.getMessage());
    }

    public ResponseEntity<ErroNotificacao> handleDefaultException(Exception e) {

        return getErroNotificacaoResponseEntity(e.getMessage());
    }

    private static ResponseEntity<ErroNotificacao> getErroNotificacaoResponseEntity(String e) {
        return new ResponseEntity<>(
                ErroNotificacao.builder().mensagem(e).build(),
                new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
