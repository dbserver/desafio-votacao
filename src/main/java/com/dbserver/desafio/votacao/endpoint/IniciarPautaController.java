package com.dbserver.desafio.votacao.endpoint;

import com.dbserver.desafio.votacao.endpoint.constant.EndpointURL;
import com.dbserver.desafio.votacao.endpoint.dto.PautaDuracaoDTO;
import com.dbserver.desafio.votacao.endpoint.dto.PautaSessaoDTO;
import com.dbserver.desafio.votacao.endpoint.mapper.PautaParaPautaSessaoDtoMapper;
import com.dbserver.desafio.votacao.endpoint.swagger.IniciarPautaControllerSwagger;
import com.dbserver.desafio.votacao.usecase.domain.Pauta;
import com.dbserver.desafio.votacao.usecase.pauta.IniciarPautaUsecase;
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
public class IniciarPautaController implements IniciarPautaControllerSwagger {

    private final IniciarPautaUsecase iniciarPautaUsecase;

    @PostMapping(value = EndpointURL.INICIAR_PAUTA_URL)
    public ResponseEntity<PautaSessaoDTO> iniciarPauta(@RequestBody PautaDuracaoDTO pautaDuracaoDTO) {

        Pauta pautaIniciada = iniciarPautaUsecase.execute(
                pautaDuracaoDTO.getIdPauta(),
                pautaDuracaoDTO.getDuracaoSessao());

        PautaSessaoDTO pautaSessaoDTOResponse = PautaParaPautaSessaoDtoMapper.INSTANCE.map(pautaIniciada);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pautaSessaoDTOResponse);
    }
}
