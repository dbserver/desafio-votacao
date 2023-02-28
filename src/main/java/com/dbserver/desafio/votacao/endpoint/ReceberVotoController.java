package com.dbserver.desafio.votacao.endpoint;

import com.dbserver.desafio.votacao.endpoint.constant.EndpointURL;
import com.dbserver.desafio.votacao.endpoint.dto.VotoDTO;
import com.dbserver.desafio.votacao.endpoint.mapper.VotoDtoParaVotoMapper;
import com.dbserver.desafio.votacao.endpoint.mapper.VotoParaVotoDTOMapper;
import com.dbserver.desafio.votacao.usecase.assembleia.ReceberVotoUseCase;
import com.dbserver.desafio.votacao.usecase.domain.Voto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ReceberVotoController {
    private final ReceberVotoUseCase receberVotoUseCase;

    @PostMapping(value = EndpointURL.RECEBER_VOTO_URL)
    public ResponseEntity<VotoDTO> receberVoto(@RequestBody VotoDTO votoDTO) {

        Voto voto = VotoDtoParaVotoMapper.INSTANCE.map(votoDTO);

        Voto votoEfetuado = receberVotoUseCase.execute(voto);

        return ResponseEntity.ok(
                VotoParaVotoDTOMapper.INSTANCE.map(votoEfetuado));
    }
}

