package br.com.dbserver.voting.controllers;

import br.com.dbserver.voting.dtos.votingsession.VotingSessionRequestDTO;
import br.com.dbserver.voting.dtos.votingsession.VotingSessionResponseDTO;
import br.com.dbserver.voting.helpers.Constants;
import br.com.dbserver.voting.services.VotingSessionService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.com.dbserver.voting.helpers.Util.localDateTimeToString;
import static java.time.LocalDateTime.now;

@RestController
@RequestMapping(path = Constants.API_VERSION + "/session")
public class VotingSessionController {

    final VotingSessionService votingSessionService;
    private static final Logger logger = LoggerFactory.getLogger(VotingSessionController.class);

    public VotingSessionController(VotingSessionService votingSessionService) {
        this.votingSessionService = votingSessionService;
    }

    @PostMapping(path = "/open")
    public ResponseEntity<VotingSessionResponseDTO> openVoting(@RequestBody @Valid VotingSessionRequestDTO votingSessionRequestDTO){
        logger.info("Iniciando votacao de uma pauta, inicio -  {}", localDateTimeToString(now()));
        VotingSessionResponseDTO openVoting = votingSessionService.openVoting(votingSessionRequestDTO);
        logger.info("Iniciando votacao de uma pauta, fim -  {}", localDateTimeToString(now()));
        return new ResponseEntity<>(openVoting, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<VotingSessionResponseDTO>> listAll(Pageable pageable){
        logger.info("Listando sessoes abertas de votacao para uma pauta, inicio -  {}", localDateTimeToString(now()));
        Page<VotingSessionResponseDTO> openVotes = votingSessionService.listAll(pageable);
        logger.info("Listando sessoes abertas de votacao para uma pauta, fim -  {}", localDateTimeToString(now()));
        return new ResponseEntity<>(openVotes, HttpStatus.OK);
    }

}
