package com.dbserver.desafio.valida.cpf.endpoint;

import com.dbserver.desafio.valida.cpf.endpoint.constant.EndpointURL;
import com.dbserver.desafio.valida.cpf.endpoint.dto.PautaIdDTO;
import com.dbserver.desafio.valida.cpf.endpoint.dto.VotosPautaDTO;
import com.dbserver.desafio.valida.cpf.endpoint.mapper.VotosPautaParaVotosPautaDTOMapper;
import com.dbserver.desafio.valida.cpf.endpoint.swagger.ContabilizarVotosControllerSwagger;
import com.dbserver.desafio.valida.cpf.usecase.domain.VotosPauta;
import com.dbserver.desafio.valida.cpf.usecase.assembleia.ContabilizarVotosUsecase;
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
public class ContabilizarVotosController implements ContabilizarVotosControllerSwagger {

    private final ContabilizarVotosUsecase contabilizarVotosUsecase;

    @PostMapping(value = EndpointURL.CONTABILIZAR_VOTOS_URL)
    public ResponseEntity<VotosPautaDTO> contabilizarVotos(@Valid @RequestBody PautaIdDTO PautaIdDTO) {

        VotosPauta votosPautaResultado = contabilizarVotosUsecase.execute(PautaIdDTO.getIdPauta());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(VotosPautaParaVotosPautaDTOMapper.INSTANCE.map(votosPautaResultado));
    }
}
