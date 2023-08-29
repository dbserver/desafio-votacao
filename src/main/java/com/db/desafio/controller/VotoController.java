package com.db.desafio.controller;



import com.db.desafio.dto.VotoDto;
import com.db.desafio.service.VotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/sessoes")
public class VotoController {

        @Autowired
        private VotoService votoService;
        @PostMapping("/{sessaoId}/votos")
        ResponseEntity<Void> salvarVoto(@PathVariable Long sessaoId, @Valid @RequestBody VotoDto votoDto) {
            votoService.salvarVoto(sessaoId,votoDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

}
