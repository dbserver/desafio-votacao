package com.dbserver.desafio.valida.cpf.endpoint;

import com.dbserver.desafio.valida.cpf.endpoint.constant.EndpointURL;
import com.dbserver.desafio.valida.cpf.endpoint.swagger.IniciarPautaControllerSwagger;
import com.dbserver.desafio.valida.cpf.usecase.domain.Pauta;
import com.dbserver.desafio.valida.cpf.usecase.pauta.IniciarPautaUsecase;
import com.dbserver.desafio.valida.cpf.endpoint.dto.PautaDuracaoDTO;
import com.dbserver.desafio.valida.cpf.endpoint.dto.PautaSessaoDTO;
import com.dbserver.desafio.valida.cpf.endpoint.mapper.PautaParaPautaSessaoDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
public class IniciarPautaController implements IniciarPautaControllerSwagger {

    private final IniciarPautaUsecase iniciarPautaUsecase;

    @PostMapping(value = EndpointURL.INICIAR_PAUTA_URL)
    public ResponseEntity<PautaSessaoDTO> iniciarPauta(@Valid @RequestBody PautaDuracaoDTO pautaDuracaoDTO) {

        Pauta pautaIniciada = iniciarPautaUsecase.execute(
                                                    pautaDuracaoDTO.getIdPauta(),
                                                    pautaDuracaoDTO.getDuracaoSessao());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(PautaParaPautaSessaoDtoMapper.INSTANCE.map(pautaIniciada));
    }
}
