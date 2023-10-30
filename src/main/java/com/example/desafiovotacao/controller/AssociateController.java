package com.example.desafiovotacao.controller;

import com.example.desafiovotacao.dto.CreatedAssociateDTO;
import com.example.desafiovotacao.dto.RegisterAssociateDTO;
import com.example.desafiovotacao.service.implementations.AssociateService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/associate")
@AllArgsConstructor
public class AssociateController {

    private final AssociateService associateService;

    @PostMapping("/create")
    public ResponseEntity<CreatedAssociateDTO> create(@RequestBody RegisterAssociateDTO associate) {
        return ResponseEntity.status(HttpStatus.OK).body(associateService.create(associate));
    }

}
