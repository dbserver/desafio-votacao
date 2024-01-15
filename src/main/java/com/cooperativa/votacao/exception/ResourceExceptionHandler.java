package com.cooperativa.votacao.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static Logger log = LoggerFactory.getLogger(ResourceExceptionHandler.class);
	
	@ExceptionHandler(SessaoException.class)
	public ResponseEntity<ErroDTO> handleSessaoException(SessaoException e,
			HttpServletRequest request, HandlerMethod handlerMethod) {	
		log.error(e.getMessage());
		
		ErroDTO erroDTO = new ErroDTO(400,HttpStatus.BAD_REQUEST.name(), e.getMessage());
		return new  ResponseEntity<ErroDTO>(erroDTO, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(PautaNaoEncontradaException.class)
	public ResponseEntity<ErroDTO> handlePautaNaoEncontradaException(PautaNaoEncontradaException e,
			HttpServletRequest request, HandlerMethod handlerMethod) {	
		log.error(e.getMessage());
		
		ErroDTO erroDTO = new ErroDTO(404,HttpStatus.NOT_FOUND.name(), e.getMessage());
		return new  ResponseEntity<ErroDTO>(erroDTO, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(VotoInvalidoException.class)
	public ResponseEntity<ErroDTO> handleVotoInvalidoException(VotoInvalidoException e,
			HttpServletRequest request, HandlerMethod handlerMethod) {	
		log.error(e.getMessage());
		
		ErroDTO erroDTO = new ErroDTO(400,HttpStatus.BAD_REQUEST.name(), e.getMessage());
		return new  ResponseEntity<ErroDTO>(erroDTO, HttpStatus.BAD_REQUEST);
	}
	

}
