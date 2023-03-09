package com.dbserver.controller;

import com.dbserver.model.dto.AgendaRequestDTO;
import com.dbserver.model.dto.AgendaDTO;
import com.dbserver.service.AgendaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/agenda")
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody AgendaRequestDTO agendaRequestDTO) {
        AgendaDTO agendaDTO = agendaService.create(agendaRequestDTO);
        return new ResponseEntity(agendaDTO, CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaDTO> getById(@PathVariable String id) {
        AgendaDTO agendaDTO = agendaService.getById(id);
        return new ResponseEntity<>(agendaDTO, OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendaDTO> update(
            @PathVariable String id,
            @Valid @RequestBody AgendaRequestDTO agendaRequestDTO
    ) {
        AgendaDTO agendaDTO = agendaService.update(id, agendaRequestDTO);
        return new ResponseEntity<>(agendaDTO, OK);
    }

    @GetMapping
    public ResponseEntity<List<AgendaDTO>> getAll() {
        List<AgendaDTO> agendas = agendaService.getAll();
        return new ResponseEntity<>(agendas, OK);
    }

    @GetMapping("/{id}/voting/status")
    public ResponseEntity<AgendaDTO> getAgendaVotingStatus(@PathVariable String id) {
        return new ResponseEntity<>(OK);
    }

}
