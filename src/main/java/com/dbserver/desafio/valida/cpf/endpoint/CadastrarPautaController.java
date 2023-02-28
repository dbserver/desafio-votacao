package com.dbserver.desafio.valida.cpf.endpoint;

import com.dbserver.desafio.valida.cpf.usecase.domain.Pauta;
import com.dbserver.desafio.valida.cpf.usecase.pauta.SalvarPautaUsecase;
import com.dbserver.desafio.valida.cpf.endpoint.constant.EndpointURL;
import com.dbserver.desafio.valida.cpf.endpoint.dto.PautaDTO;
import com.dbserver.desafio.valida.cpf.endpoint.mapper.PautaDtoParaPautaMapper;
import com.dbserver.desafio.valida.cpf.endpoint.mapper.PautaParaPautaDtoMapper;
import com.dbserver.desafio.valida.cpf.endpoint.swagger.CadastrarPautaControllerSwagger;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class CadastrarPautaController implements CadastrarPautaControllerSwagger {

    private final SalvarPautaUsecase salvarPautaUsecase;

    @PostMapping(value = EndpointURL.CADASTRAR_PAUTA_URL)
    public ResponseEntity<PautaDTO> cadastrarPauta(@Valid @RequestBody PautaDTO pautaDTO) {

        Pauta pauta = PautaDtoParaPautaMapper.INSTANCE.map(pautaDTO);

        Pauta pautaCadastrada = salvarPautaUsecase.execute(pauta);

        return ResponseEntity.ok(PautaParaPautaDtoMapper.INSTANCE.map(pautaCadastrada));
    }
}
