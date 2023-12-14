package com.challenge.votation.controller;


import com.challenge.votation.model.Agenda;
import com.challenge.votation.model.Vote;
import com.challenge.votation.service.AgendaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/agendas")
public class AgendaController {

    private final AgendaService agendaService;

    @PostMapping
    public ResponseEntity<Agenda> createAgenda(@RequestBody @Valid Agenda agenda) {
        log.info("Creating Agenda: {}", agenda);
        Agenda agendaCreated = agendaService.createAgenda(agenda);

        return new ResponseEntity<>(agendaCreated, HttpStatus.CREATED);
    }

    @PostMapping("/{agendaId}")
    public ResponseEntity<Agenda> openAgenda(@PathVariable Long agendaId, @RequestBody Agenda agenda) {
        log.info("Opening Agenda Id: {}, with these information: {}", agendaId, agenda);
        Agenda agendaOpened = agendaService.openAgenda(agendaId, agenda);

        return new ResponseEntity<>(agendaOpened, HttpStatus.OK);
    }


    @PostMapping("/{agendaId}/votes")
    public ResponseEntity<Void> vote(@PathVariable Long agendaId, @RequestBody @Valid Vote vote) {
        log.info("Voting Agenda Id: {}, with these information: {}", agendaId, vote);
        agendaService.vote(agendaId, vote);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{agendaId}")
    public ResponseEntity<Agenda> getAgendaStatus(@PathVariable Long agendaId) {
        log.info("Getting Status Agenda Id: {}", agendaId);
        Agenda agenda = agendaService.getAgendaStatus(agendaId);

        return new ResponseEntity<>(agenda, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Agenda>> getAgendasStatus(Pageable pageable) {
        log.info("Getting Status Agendas");
        Page<Agenda> agendas = agendaService.getAgendasStatus(pageable);

        return new ResponseEntity<>(agendas, HttpStatus.OK);
    }
}
