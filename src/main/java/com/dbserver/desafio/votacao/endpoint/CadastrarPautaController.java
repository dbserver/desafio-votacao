package com.dbserver.desafio.votacao.endpoint;

import com.dbserver.desafio.votacao.endpoint.constant.EndpointURL;
import com.dbserver.desafio.votacao.endpoint.dto.PautaDTO;
import com.dbserver.desafio.votacao.endpoint.mapper.PautaDtoParaPautaMapper;
import com.dbserver.desafio.votacao.endpoint.mapper.PautaParaPautaDtoMapper;
import com.dbserver.desafio.votacao.usecase.domain.Pauta;
import com.dbserver.desafio.votacao.usecase.pauta.CadastrarPautaUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class CadastrarPautaController {

    private final CadastrarPautaUsecase cadastrarPautaUsecase;

    @PostMapping(value = EndpointURL.CADASTRAR_PAUTA_URL)
    public ResponseEntity<PautaDTO> cadastrarPauta(@RequestBody PautaDTO pautaDTO) {

        Pauta pauta = PautaDtoParaPautaMapper.INSTANCE.map(pautaDTO);

        Pauta pautaCadastrada = cadastrarPautaUsecase.execute(pauta);

        PautaDTO pautaDTOResponse = PautaParaPautaDtoMapper.INSTANCE.map(pautaCadastrada);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pautaDTOResponse);
    }
}
