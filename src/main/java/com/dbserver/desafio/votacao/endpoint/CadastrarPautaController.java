package com.dbserver.desafio.votacao.endpoint;

import com.dbserver.desafio.votacao.endpoint.constant.EndpointURL;
import com.dbserver.desafio.votacao.endpoint.dto.PautaDTO;
import com.dbserver.desafio.votacao.endpoint.mapper.PautaDtoParaPautaMapper;
import com.dbserver.desafio.votacao.endpoint.mapper.PautaParaPautaDtoMapper;
import com.dbserver.desafio.votacao.endpoint.swagger.CadastrarPautaControllerSwagger;
import com.dbserver.desafio.votacao.usecase.domain.Pauta;
import com.dbserver.desafio.votacao.usecase.pauta.SalvarPautaUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class CadastrarPautaController implements CadastrarPautaControllerSwagger {

    private final SalvarPautaUsecase salvarPautaUsecase;

    @PostMapping(value = EndpointURL.CADASTRAR_PAUTA_URL)
    public ResponseEntity<PautaDTO> cadastrarPauta(@RequestBody PautaDTO pautaDTO) {

        log.info("[CadastrarPautaController] Requisição HTTP POST recebida.");

        Pauta pauta = PautaDtoParaPautaMapper.INSTANCE.map(pautaDTO);

        Pauta pautaCadastrada = salvarPautaUsecase.execute(pauta);

        PautaDTO pautaDTOResponse = PautaParaPautaDtoMapper.INSTANCE.map(pautaCadastrada);

        return ResponseEntity.ok(pautaDTOResponse);
    }
}
