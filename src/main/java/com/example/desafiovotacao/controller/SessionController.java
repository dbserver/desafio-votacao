package com.example.desafiovotacao.controller;

import com.example.desafiovotacao.dto.CreatedSessionDTO;
import com.example.desafiovotacao.dto.SessionReturnDTO;
import com.example.desafiovotacao.dto.StartSessionDTO;
import com.example.desafiovotacao.service.implementations.SessionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/session")
@AllArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @PostMapping("/create")
    public ResponseEntity<CreatedSessionDTO> create(@RequestBody StartSessionDTO startSessionDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(sessionService.create(startSessionDTO));
    }

    @GetMapping("/list")
    public ResponseEntity<List<SessionReturnDTO>> list() {
        return ResponseEntity.status(HttpStatus.OK).body(sessionService.listAll());
    }

    @GetMapping("/list/{sessionId}")
    public ResponseEntity<SessionReturnDTO> findById(@PathVariable Integer sessionId) {
        return ResponseEntity.status(HttpStatus.OK).body(sessionService.listById(sessionId));
    }

}
