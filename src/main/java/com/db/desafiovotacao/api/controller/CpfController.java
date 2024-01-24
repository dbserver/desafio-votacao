package com.db.desafiovotacao.api.controller;

import com.db.desafiovotacao.api.domain.StatusCpf;
import com.db.desafiovotacao.api.exception.CPFInvalidException;
import com.db.desafiovotacao.api.record.CpfValidationRecord;
import com.db.desafiovotacao.api.service.CpfValidationFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "4-CPF", description = "CPF management APIs")
@RestController
@RequestMapping("/cpf")
public class CpfController {

    private final CpfValidationFacade cpfValidationFacade;

    @Autowired
    public CpfController(CpfValidationFacade cpfValidationFacade) {
        this.cpfValidationFacade = cpfValidationFacade;
    }

    @Operation(summary = "CPF validation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agenda created with success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CpfValidationRecord.class)) })})
    @GetMapping("/validate/{cpf}")
    public ResponseEntity<CpfValidationRecord> validateCpf(@Parameter(description = "CPF information")@PathVariable String cpf) throws CPFInvalidException {
        try{
            return ResponseEntity.ok(cpfValidationFacade.validateCpf(cpf));
        }catch(CPFInvalidException e){
            throw e;
        }
    }

    @ExceptionHandler(CPFInvalidException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<CpfValidationRecord> handleAgendaNotFoundException() {
        return new ResponseEntity<>(new CpfValidationRecord(StatusCpf.UNABLE_TO_VOTE), HttpStatus.NOT_FOUND);
    }
}
