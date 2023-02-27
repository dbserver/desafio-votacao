package com.dbserver.desafio.votacao.endpoint;

import com.dbserver.desafio.votacao.endpoint.constant.EndpointURL;
import com.dbserver.desafio.votacao.endpoint.dto.PautaDuracaoDTO;
import com.dbserver.desafio.votacao.endpoint.dto.PautaSessaoDTO;
import com.dbserver.desafio.votacao.endpoint.dto.VotoDTO;
import com.dbserver.desafio.votacao.endpoint.mapper.PautaParaPautaSessaoDtoMapper;
import com.dbserver.desafio.votacao.endpoint.mapper.VotoDtoParaVotoMapper;
import com.dbserver.desafio.votacao.endpoint.mapper.VotoParaVotoDTOMapper;
import com.dbserver.desafio.votacao.exception.SessaoFinalizadaException;
import com.dbserver.desafio.votacao.exception.VotoJaRealizadoException;
import com.dbserver.desafio.votacao.usecase.assembleia.ReceberVotoUseCase;
import com.dbserver.desafio.votacao.usecase.domain.Pauta;
import com.dbserver.desafio.votacao.usecase.domain.Voto;
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
public class ReceberVotoController {
    private final ReceberVotoUseCase receberVotoUseCase;

    @PostMapping(value = EndpointURL.RECEBER_VOTO_URL)
    public ResponseEntity<VotoDTO> receberVoto(@RequestBody VotoDTO votoDTO) throws VotoJaRealizadoException, SessaoFinalizadaException {

        Voto voto = VotoDtoParaVotoMapper.INSTANCE.map(votoDTO);

        Voto votoEfetuado = receberVotoUseCase.execute(voto);

        VotoDTO votoDTOResponse = VotoParaVotoDTOMapper.INSTANCE.map(votoEfetuado);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(votoDTOResponse);
    }
}

