package br.com.occ.desafiovotacao.v1.controller;

import br.com.occ.desafiovotacao.v1.dto.PautaDto;
import br.com.occ.desafiovotacao.v1.dto.SessaoDto;
import br.com.occ.desafiovotacao.v1.model.Sessao;
import br.com.occ.desafiovotacao.v1.service.ISessaoService;
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

@Api(value = "Sessao")
@RestController
@RequestMapping(value = "/v1/sessao", produces = MediaType.APPLICATION_JSON_VALUE)
public class SessaoController {

    @Autowired
    ISessaoService service;

    @Autowired
    ModelMapper modelMapper;

    @ApiOperation(value = "Cadastra uma nova sessão")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Sessão cadastrada", response = SessaoDto.class),
            @ApiResponse(code = 400, message = "Não foi possível cadastrar a sessão"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping("/pauta/{id}")
    public ResponseEntity<SessaoDto> salvar(@RequestBody SessaoDto sessaoDto, @PathVariable("id") Long idPauta){
        return ResponseEntity.ok(service.save(
                sessaoDto, idPauta).toDto(modelMapper, SessaoDto.class)
        );
    }

    @ApiOperation(value = "Realiza consulta pelo id de uma Sessão")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Sessão encontrada", response = PautaDto.class),
            @ApiResponse(code = 404, message = "Não foi possível retornar a sessão para o id informado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<SessaoDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id).toDto(modelMapper, SessaoDto.class));
    }

    @ApiOperation(value = "Lista todas as sessões")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Lista todas as Pautas", response = PautaDto.class),
            @ApiResponse(code = 400, message = "Não existe pautas cadastradas"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping
    public ResponseEntity<List<SessaoDto>> getPautas(){
        List<SessaoDto> sessaoDtos = service.findAll().stream()
                .map(sessao -> sessao.toDto(modelMapper, SessaoDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(sessaoDtos);
    }

    @ApiOperation(value = "Lista todas as sessões disponíveis para votar")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Lista sessões disponíveis para votar", response = PautaDto.class),
            @ApiResponse(code = 400, message = "Não existe sessões abertas"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/ativas")
    public ResponseEntity<List<SessaoDto>> getSessoesAtivas(){
        List<SessaoDto> sessaoDtos = service.findAllAtivas().stream()
                .map(sessao -> sessao.toDto(modelMapper, SessaoDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(sessaoDtos);
    }
}
