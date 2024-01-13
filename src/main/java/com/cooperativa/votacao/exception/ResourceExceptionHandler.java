package com.cooperativa.votacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cooperativa.votacao.dto.ErroDTO;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(SessaoException.class)
	public ResponseEntity<ErroDTO> handleExcecaoRegraNegocio(SessaoException e,
			HttpServletRequest request, HandlerMethod handlerMethod) {	
		ErroDTO erroDTO = new ErroDTO(400,HttpStatus.BAD_REQUEST.name(), e.getMessage());
		return new  ResponseEntity<ErroDTO>(erroDTO, HttpStatus.BAD_REQUEST);
	}
	

}
