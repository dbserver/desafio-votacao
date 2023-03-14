package com.dbserver.api;

import com.dbserver.model.dto.AgendaCreateDTO;
import com.dbserver.model.dto.AgendaDTO;
import com.dbserver.model.dto.AgendaVotingStatusDTO;
import com.dbserver.service.AgendaService;
import com.dbserver.service.VotingStatusService;
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
    @Autowired
    private VotingStatusService votingStatusService;

    @PostMapping
    public ResponseEntity<AgendaDTO> create(@Valid @RequestBody AgendaCreateDTO agendaCreateDTO) {
        AgendaDTO agendaDTO = agendaService.create(agendaCreateDTO);
        return new ResponseEntity<>(agendaDTO, CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaDTO> getById(@PathVariable String id) {
        AgendaDTO agendaDTO = agendaService.getAgendaById(id);
        return new ResponseEntity<>(agendaDTO, OK);
    }

    @GetMapping
    public ResponseEntity<List<AgendaDTO>> getAll() {
        List<AgendaDTO> agendas = agendaService.getAll();
        return new ResponseEntity<>(agendas, OK);
    }

    @GetMapping("/{id}/voting/status")
    public ResponseEntity<AgendaVotingStatusDTO> getAgendaVotingStatus(@PathVariable String id) {
        AgendaVotingStatusDTO agendaVotingStatusDTO = votingStatusService.getAgendaVotingStatus(id);
        return new ResponseEntity<>(agendaVotingStatusDTO, OK);
    }

}
