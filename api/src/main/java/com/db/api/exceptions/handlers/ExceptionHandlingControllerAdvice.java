package com.db.api.exceptions.handlers;

import com.db.api.exceptions.*;
import com.db.api.models.ErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandlingControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        ErrorResponse errorResponse = new ErrorResponse("Erro de validação", errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(ParametrosInvalidosException.class)
    public ResponseEntity<ErrorResponse> handleParametrosInvalidosException(ParametrosInvalidosException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Parâmetros inválidos", Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Corpo da requisição inválido", Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AssociadoJaCadastradoException.class)
    public ResponseEntity<ErrorResponse> handleAssociadoJaCadastradoException(AssociadoJaCadastradoException ex) {
        ErrorResponse errorResponse = new ErrorResponse("CPF já cadastrado", Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RegistroNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleRegistroNaoEncontradoException(RegistroNaoEncontradoException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Registro não encontrado", Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> tratarErrosInesperados(RuntimeException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Erro inesperado na nossa API", Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Dados inválidos", Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
