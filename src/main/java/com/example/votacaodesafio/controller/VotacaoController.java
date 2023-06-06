package com.example.votacaodesafio.controller;

import com.example.votacaodesafio.domain.dto.VotacaoDTO;
import com.example.votacaodesafio.domain.dto.VotacaoResponse;
import com.example.votacaodesafio.service.VotacaoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/voto")
@AllArgsConstructor
public class VotacaoController {

    private final VotacaoService votacaoService;


    @PostMapping("/{associateId}")
    public ResponseEntity<VotacaoResponse> vote(@PathVariable Long associateId, @RequestBody VotacaoDTO votacaoDTO) {
        VotacaoResponse result = votacaoService.votar(associateId, votacaoDTO);
        return ResponseEntity.ok(result);
    }

}
