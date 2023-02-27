package com.dbserver.desafio.votacao.endpoint;

import com.dbserver.desafio.votacao.endpoint.constant.EndpointURL;
import com.dbserver.desafio.votacao.endpoint.dto.PautaDTO;
import com.dbserver.desafio.votacao.endpoint.dto.VotosPautaDTO;
import com.dbserver.desafio.votacao.endpoint.mapper.VotosPautaParaVotosPautaDTOMapper;
import com.dbserver.desafio.votacao.usecase.assembleia.ContabilizarVotosUsecase;
import com.dbserver.desafio.votacao.usecase.domain.VotosPauta;
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
public class ContabilizarVotosController {

    private final ContabilizarVotosUsecase contabilizarVotosUsecase;

    @PostMapping(value = EndpointURL.CONTABILIZAR_VOTOS_URL)
    public ResponseEntity<VotosPautaDTO> contabilizarVotos(@RequestBody PautaDTO pautaDTO) {

        VotosPauta votosPautaResultado = contabilizarVotosUsecase.execute(pautaDTO.getIdPauta());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(VotosPautaParaVotosPautaDTOMapper.INSTANCE.map(votosPautaResultado));
    }
}
