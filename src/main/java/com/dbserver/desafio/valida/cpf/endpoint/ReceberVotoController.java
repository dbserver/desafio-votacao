package com.dbserver.desafio.valida.cpf.endpoint;

import com.dbserver.desafio.valida.cpf.endpoint.swagger.ReceberVotoControllerSwagger;
import com.dbserver.desafio.valida.cpf.usecase.domain.Voto;
import com.dbserver.desafio.valida.cpf.endpoint.constant.EndpointURL;
import com.dbserver.desafio.valida.cpf.endpoint.dto.VotoDTO;
import com.dbserver.desafio.valida.cpf.endpoint.mapper.VotoDtoParaVotoMapper;
import com.dbserver.desafio.valida.cpf.endpoint.mapper.VotoParaVotoDTOMapper;
import com.dbserver.desafio.valida.cpf.usecase.assembleia.ReceberVotoUseCase;
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

