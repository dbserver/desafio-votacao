package com.example.votacaodesafio.controller;

import com.example.votacaodesafio.domain.dto.VotacaoResultadoResponse;
import com.example.votacaodesafio.domain.entity.SessaoVotacao;
import com.example.votacaodesafio.service.GenericService;
import com.example.votacaodesafio.service.SessaoVotacaoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class SessaoVotacaoController extends GenericRestController<SessaoVotacao, Long> {

    @Autowired
    private SessaoVotacaoService sessaoVotacaoService;

    public SessaoVotacaoController(GenericService<SessaoVotacao, Long> service) {
        super(service);
    }

    @GetMapping("/{id}/result")
    public ResponseEntity<VotacaoResultadoResponse> getResult(@PathVariable Long id) {
        VotacaoResultadoResponse resultDTO = sessaoVotacaoService.getResult(id);
        return ResponseEntity.ok(resultDTO);
    }

}
