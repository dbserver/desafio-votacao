package br.tec.db.desafio.api.v1.handler;

import br.tec.db.desafio.api.v1.handler.dto.HandlerBusinessExceptionResponse;
import br.tec.db.desafio.api.v1.handler.dto.HandlerNotFoundExceptionResponse;
import br.tec.db.desafio.exception.BusinessException;

import br.tec.db.desafio.exception.NotFoundException;
import br.tec.db.desafio.util.FormatUtil;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.time.Instant;


@ControllerAdvice
public class DesafioExceptionHandler {

    private static final FormatUtil formatUtil = new FormatUtil();

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<HandlerBusinessExceptionResponse> handlerBusinessException(BusinessException ex) {

        HandlerBusinessExceptionResponse error = new HandlerBusinessExceptionResponse(
                formatUtil.dataFormatada(String.valueOf(Instant.now())),
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);

    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<HandlerNotFoundExceptionResponse> handlerNotFoundException(BusinessException ex) {

        HandlerNotFoundExceptionResponse error = new HandlerNotFoundExceptionResponse(
                formatUtil.dataFormatada(String.valueOf(Instant.now())),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }
}

