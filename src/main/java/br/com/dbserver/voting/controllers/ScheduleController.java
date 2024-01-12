package br.com.dbserver.voting.controllers;

import br.com.dbserver.voting.dtos.ScheduleDTO;
import br.com.dbserver.voting.helpers.Constants;
import br.com.dbserver.voting.services.ScheduleService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.dbserver.voting.helpers.Util.localDateTimeToString;
import static java.time.LocalDateTime.now;

@RestController
@RequestMapping(path = Constants.API_VERSION + "/schedule")
public class ScheduleController {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);
    final ScheduleService service;

    public ScheduleController(ScheduleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> createSchedule(@RequestBody @Valid ScheduleDTO scheduleDTO){
        logger.info("Criando uma pauta, inicio -  {}", localDateTimeToString(now()));
        service.createSchedule(scheduleDTO);
        logger.info("Criando uma pauta, fim -  {}", localDateTimeToString(now()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<ScheduleDTO>> listAll(Pageable pageable){
        logger.info("Listando pautas, inicio -  {}", localDateTimeToString(now()));
        Page<ScheduleDTO> schedules = service.listAll(pageable);
        logger.info("Listando pautas, fim -  {}", localDateTimeToString(now()));
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

}
