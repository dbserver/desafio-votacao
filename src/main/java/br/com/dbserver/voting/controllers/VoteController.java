package br.com.dbserver.voting.controllers;

import br.com.dbserver.voting.dtos.vote.ResultOfTheVoteDTO;
import br.com.dbserver.voting.dtos.vote.VoteRequestDTO;
import br.com.dbserver.voting.dtos.vote.VoteResponseDTO;
import br.com.dbserver.voting.helpers.Constants;
import br.com.dbserver.voting.services.VoteService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.dbserver.voting.helpers.Util.localDateTimeToString;
import static java.time.LocalDateTime.now;

@RestController
@RequestMapping(path = Constants.API_VERSION + "/vote")
public class VoteController {

    private final Logger logger = LoggerFactory.getLogger(VoteController.class);
    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping
    public ResponseEntity<VoteResponseDTO> createVote(@RequestBody @Valid VoteRequestDTO voteRequestDTO){
        logger.info("Votando em uma pauta, inicio -  {}", localDateTimeToString(now()));
        VoteResponseDTO voteResponseDTO = voteService.voting(voteRequestDTO);
        logger.info("Votando em uma pauta, fim -  {}", localDateTimeToString(now()));
        return new ResponseEntity<>(voteResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/progress")
    public ResponseEntity<List<ResultOfTheVoteDTO>> listVoteInProgress(){
        logger.info("Listando votacoes em andamento, inicio -  {}", localDateTimeToString(now()));
        List<ResultOfTheVoteDTO> resultOfTheVoteDTOS = voteService.listVoteInProgress();
        logger.info("Listando votacoes em andamento, fim -  {}", localDateTimeToString(now()));
        return new ResponseEntity<>(resultOfTheVoteDTOS, HttpStatus.OK);
    }
}
