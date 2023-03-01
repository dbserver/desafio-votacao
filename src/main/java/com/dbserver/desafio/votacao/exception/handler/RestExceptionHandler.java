package com.dbserver.desafio.votacao.exception.handler;

import com.dbserver.desafio.votacao.exception.PautaInexistenteException;
import com.dbserver.desafio.votacao.exception.PautaSemSessaoException;
import com.dbserver.desafio.votacao.exception.PautaSemVotoException;
import com.dbserver.desafio.votacao.exception.SessaoFinalizadaException;
import com.dbserver.desafio.votacao.exception.VotoJaRealizadoException;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(VotoJaRealizadoException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErroNotificacao> handleVotoJaRealizadoException(VotoJaRealizadoException e) {

        return getErroNotificacaoResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SessaoFinalizadaException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErroNotificacao> handleSessaoFinalizadaException(SessaoFinalizadaException e) {

        return getErroNotificacaoResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PautaSemSessaoException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErroNotificacao> handlePautaSemSessaoException(PautaSemSessaoException e) {

        return getErroNotificacaoResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PautaInexistenteException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErroNotificacao> handlePautaInexistenteException(PautaInexistenteException e) {

        return getErroNotificacaoResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PautaSemVotoException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErroNotificacao> handlePautaSemVotoException(PautaSemVotoException e) {

        return getErroNotificacaoResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErroNotificacao> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        return getErroNotificacaoResponseEntity("Verificar campos obrigatórios do payload", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErroNotificacao> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {

        return getErroNotificacaoResponseEntity("Verificar os formatos corretos dos campos do payload", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FeignException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErroNotificacao> handleFeignException(FeignException e) {

        return getErroNotificacaoResponseEntity("CPF Inválido", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ErroNotificacao> handleDefaultException(Exception e) {

        return getErroNotificacaoResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private static ResponseEntity<ErroNotificacao> getErroNotificacaoResponseEntity(String e, HttpStatus internalServerError) {
        return new ResponseEntity<>(
                ErroNotificacao.builder().mensagem(e).build(),
                new HttpHeaders(), internalServerError);
    }
}
