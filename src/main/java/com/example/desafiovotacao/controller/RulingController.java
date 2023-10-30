package com.example.desafiovotacao.controller;

import com.example.desafiovotacao.dto.CountingResultsDTO;
import com.example.desafiovotacao.dto.CreatedRulingDTO;
import com.example.desafiovotacao.dto.RegisterRulingDTO;
import com.example.desafiovotacao.dto.RulingReturnDTO;
import com.example.desafiovotacao.service.implementations.RulingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ruling")
@AllArgsConstructor
public class RulingController {

    private final RulingService rulingService;

    @PostMapping("/create")
    public ResponseEntity<CreatedRulingDTO> save(@RequestBody RegisterRulingDTO ruling){
        return new ResponseEntity<>(rulingService.create(ruling), HttpStatus.OK);
    }

    @PostMapping("/countVotes/{rulingId}")
    public ResponseEntity<CountingResultsDTO> countVotes(@PathVariable Integer rulingId){
        return ResponseEntity.status(HttpStatus.OK).body(rulingService.countVotes(rulingId));
    }

    @GetMapping("/list")
    public ResponseEntity<List<RulingReturnDTO>> list() {
        return new ResponseEntity<>(rulingService.listAll(), HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<RulingReturnDTO> listById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(rulingService.getRulingReturnIfExists(id));
    }
}
