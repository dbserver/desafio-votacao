package br.com.occ.desafiovotacao.config.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {ApiException.class})
    protected ResponseEntity<Object> handleConflict(ApiException ex, WebRequest request) {

        if (ex.getStatus().is4xxClientError()) {
            log.warn("Falha ao realizar requisição {}", ex.getMessage());
        } else {
            log.error("Erro ao realizar requisição", ex);
        }

        return ex.getResponseEntity();
    }

    @ExceptionHandler(value = {ServiceException.class})
    protected ResponseEntity<Object> handleConflict(ServiceException ex, WebRequest request) {

        if (ex.getStatus().is4xxClientError()) {
            log.warn("Falha ao executar servico {}", ex.getMessage());
        } else {
            log.error("Erro ao executar servico", ex);
        }

        return ex.getResponseEntity();
    }
}
