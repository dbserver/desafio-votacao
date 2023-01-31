package br.com.occ.desafiovotacao.v1.controller;

import br.com.occ.desafiovotacao.v1.dto.VotoDto;
import br.com.occ.desafiovotacao.v1.model.Voto;
import br.com.occ.desafiovotacao.v1.service.IVotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(value = "Voto")
@RestController
@RequestMapping(value = "/v1/voto", produces = MediaType.APPLICATION_JSON_VALUE)
public class VotoController {

    @Autowired
    IVotoService service;

    @Autowired
    ModelMapper modelMapper;

    @ApiOperation(value = "Realiza consulta pelo id de um Voto")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Voto encontrado", response = VotoDto.class),
            @ApiResponse(code = 404, message = "Não foi possível retornar um voto para o id informado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<VotoDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id).toDto(modelMapper, VotoDto.class));
    }

    @ApiOperation(value = "Realiza consulta de votos de um associado")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Lista de votos por pauta de um associado", response = VotoDto.class),
            @ApiResponse(code = 404, message = "Não existe votos para o associado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/associado/{id}")
    public ResponseEntity<List<VotoDto>> getVotosByAssociado(@PathVariable("id") Long associadoId){
        List<VotoDto> votoDtos = service.findAllByAssociado(associadoId).stream()
                .map(voto -> voto.toDto(modelMapper, VotoDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(votoDtos);
    }

    @ApiOperation(value = "Realiza o voto de um associado")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cadastra o voto de um associado", response = VotoDto.class),
            @ApiResponse(code = 400, message = "CPF não está apto para realizar voto"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping
    public ResponseEntity<VotoDto> votar(@RequestBody VotoDto votoDto){
        Voto voto = service.votar(votoDto.toEntity(modelMapper, Voto.class));

        return ResponseEntity.ok(voto.toDto(modelMapper, new VotoDto()));
    }
}
