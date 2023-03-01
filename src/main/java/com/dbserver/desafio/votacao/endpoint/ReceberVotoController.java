package com.dbserver.desafio.votacao.endpoint;

import com.dbserver.desafio.votacao.endpoint.swagger.ReceberVotoControllerSwagger;
import com.dbserver.desafio.votacao.usecase.domain.Voto;
import com.dbserver.desafio.votacao.endpoint.constant.EndpointURL;
import com.dbserver.desafio.votacao.endpoint.dto.VotoDTO;
import com.dbserver.desafio.votacao.endpoint.mapper.VotoDtoParaVotoMapper;
import com.dbserver.desafio.votacao.endpoint.mapper.VotoParaVotoDTOMapper;
import com.dbserver.desafio.votacao.usecase.assembleia.ReceberVotoUseCase;
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
public class ReceberVotoController implements ReceberVotoControllerSwagger {
    private final ReceberVotoUseCase receberVotoUseCase;

    @PostMapping(value = EndpointURL.RECEBER_VOTO_URL)
    public ResponseEntity<VotoDTO> receberVoto(@Valid @RequestBody VotoDTO votoDTO) {

        Voto voto = VotoDtoParaVotoMapper.INSTANCE.map(votoDTO);

        return ResponseEntity.ok(
                VotoParaVotoDTOMapper.INSTANCE.map(receberVotoUseCase.execute(voto)));
    }
}

