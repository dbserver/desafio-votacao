package br.com.occ.desafiovotacao.v1.controller;

import br.com.occ.desafiovotacao.config.exception.ApiException;
import br.com.occ.desafiovotacao.v1.dto.PautaDto;
import br.com.occ.desafiovotacao.v1.model.Pauta;
import br.com.occ.desafiovotacao.v1.service.IPautaService;
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

@Api(value = "Pauta", description = "Realiza operações referentes as pautas de votação")
@RestController
@RequestMapping(value = "/v1/pauta", produces = MediaType.APPLICATION_JSON_VALUE)
public class PautaController {

    @Autowired
    IPautaService service;

    @Autowired
    ModelMapper modelMapper;

    @ApiOperation(value = "Cadastra uma nova pauta")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Pauta cadastrada", response = PautaDto.class),
            @ApiResponse(code = 400, message = "Não foi possível cadastrar a pauta"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping
    public ResponseEntity<PautaDto> salvar(@RequestBody PautaDto pautaDto){
        return ResponseEntity.ok(service.save(pautaDto.toEntity(modelMapper, Pauta.class)).toDto(modelMapper, PautaDto.class));
    }

    @ApiOperation(value = "Realiza consulta pelo id de uma Pauta")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Pauta encontrada", response = PautaDto.class),
            @ApiResponse(code = 404, message = "Não foi possível retornar a pauta para o id informado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<PautaDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id).toDto(modelMapper, PautaDto.class));
    }

    @ApiOperation(value = "Lista todas as pautas")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Lista todas as Pautas", response = PautaDto.class),
            @ApiResponse(code = 400, message = "Não existe pautas cadastradas"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping
    public ResponseEntity<List<PautaDto>> getPautas(){
        List<PautaDto> pautaDtos = service.findAll().stream()
                .map(pauta -> pauta.toDto(modelMapper, PautaDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(pautaDtos);
    }

    @ApiOperation(value = "Lista todas as pautas disponíveis para votar")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Lista pautas disponíveis para votar", response = PautaDto.class),
            @ApiResponse(code = 400, message = "Não existe associados aptos"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/ativas")
    public ResponseEntity<List<PautaDto>> getPautasAtivas(){
        List<PautaDto> pautaDtos = service.findAllAtivas().stream()
                .map(pauta -> pauta.toDto(modelMapper, PautaDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(pautaDtos);
    }
}
