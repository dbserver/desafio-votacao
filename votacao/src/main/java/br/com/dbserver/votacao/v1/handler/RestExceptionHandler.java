package br.com.dbserver.votacao.v1.handler;

import br.com.dbserver.votacao.v1.exception.BadRequestException;
import br.com.dbserver.votacao.v1.exception.NotFoundException;
import br.com.dbserver.votacao.v1.exception.ValidationException;
import br.com.dbserver.votacao.v1.exception.dto.BadRequestExceptionDto;
import br.com.dbserver.votacao.v1.exception.dto.NotFoundExceptionDto;
import br.com.dbserver.votacao.v1.exception.dto.MethodArgumentNotValidExceptionDto;
import br.com.dbserver.votacao.v1.exception.dto.ValidationExceptionDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

		String campos = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
		String mensagemCampo = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

		return new ResponseEntity<>(
				MethodArgumentNotValidExceptionDto.builder()
						.dataHora(LocalDateTime.now())
						.status(HttpStatus.BAD_REQUEST.value())
						.titulo("Bad Request Exception, Campos Inválidos")
						.detalhe("Cheque os campos com erro")
						.mensagem(exception.getClass().getName())
						.campo(campos)
						.mensagemCampo(mensagemCampo)
						.build(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<NotFoundExceptionDto> handlerNotFoundException(NotFoundException exception) {
		return new ResponseEntity<>(
				NotFoundExceptionDto.builder()
						.dataHora(LocalDateTime.now())
						.status(HttpStatus.NOT_FOUND.value())
						.titulo("Not Found Exception")
						.detalhe(exception.getMessage())
						.mensagem("Cheque a documentação da API")
						.build(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<BadRequestExceptionDto> handlerNotFoundException(BadRequestException exception) {
		return new ResponseEntity<>(
				BadRequestExceptionDto.builder()
						.dataHora(LocalDateTime.now())
						.status(HttpStatus.BAD_REQUEST.value())
						.titulo("Not Found Exception")
						.detalhe(exception.getMessage())
						.mensagem("Cheque a documentação da API")
						.build(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ValidationExceptionDto> handlerDataValidationException(ValidationException exception) {
		return new ResponseEntity<>(
				ValidationExceptionDto.builder()
						.dataHora(LocalDateTime.now())
						.status(HttpStatus.BAD_REQUEST.value())
						.titulo("Dados inválidos!")
						.detalhe(exception.getMessage())
						.mensagem("Cheque a documentação da API")
						.build(), HttpStatus.BAD_REQUEST);
	}
}