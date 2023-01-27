package br.com.occ.desafiovotacao.v1.controller;

import br.com.occ.desafiovotacao.config.exception.ApiException;
import br.com.occ.desafiovotacao.v1.dto.AssociadoDto;
import br.com.occ.desafiovotacao.v1.dto.AssociadoStatusDto;
import br.com.occ.desafiovotacao.v1.enums.CpfStatusEnum;
import br.com.occ.desafiovotacao.v1.model.Associado;
import br.com.occ.desafiovotacao.v1.service.IAssociadoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Api(value = "Associado", description = "Realiza operações referentes aos associados")
@RestController
@RequestMapping(value = "/v1/associado", produces = MediaType.APPLICATION_JSON_VALUE)
public class AssociadoController {

    @Autowired
    IAssociadoService service;

    @Autowired
    ModelMapper modelMapper;

    @ApiOperation(value = "Realiza consulta pelo id de um Associado")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Associado encontrado", response = AssociadoDto.class),
            @ApiResponse(code = 404, message = "Não foi possível retornar um associado para o id informado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<AssociadoDto> getById(@PathVariable Long id){

        Optional<Associado> associadoOptional = service.findById(id);

        if(associadoOptional.isEmpty())
            throw new ApiException("Associado não encontrado", HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(associadoOptional.get().toDto(modelMapper, AssociadoDto.class));
    }

    @ApiOperation(value = "Realiza consulta de asssociados aptos a voto")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Lista de Associados aptos pra votar", response = AssociadoDto.class),
            @ApiResponse(code = 401, message = "Não existe associados aptos"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/ativos")
    public ResponseEntity<List<AssociadoDto>> getAssociadosAptos(){
        List<AssociadoDto> associadoDtoList = service.findAllAtivos().stream()
                .map(associado -> associado.toDto(modelMapper, AssociadoDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(associadoDtoList);
    }

    @ApiOperation(value = "Realiza consulta se CPF")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cpf apto pra votar", response = AssociadoStatusDto.class),
            @ApiResponse(code = 404, message = "Cpf não apto para votar"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/valid-cpf/{cpf}")
    public ResponseEntity<AssociadoStatusDto> validarCpfApto(@PathVariable String cpf){
        AssociadoStatusDto associadoStatusDto = service.getStatusCpf(cpf);
        if (associadoStatusDto.getStatus() == CpfStatusEnum.ABLE_TO_VOTE)
            return new ResponseEntity<>(associadoStatusDto, HttpStatus.OK);
        else
            return new ResponseEntity<>(associadoStatusDto, HttpStatus.NOT_FOUND);
    }
}
