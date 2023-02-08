package br.com.adonias.desafiovotacao.controllers;

import br.com.adonias.desafiovotacao.business.VotingAgendaBusiness;
import br.com.adonias.desafiovotacao.dto.VotingAgendaDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("agendas")
public class VotingAgendaController {

    @Autowired
    private VotingAgendaBusiness business;

    @ApiOperation("Returns all voting agendas")
    @GetMapping
    public ResponseEntity<List<VotingAgendaDTO>> getAllVotingAgendas(){
        return business.getAllVotingAgendas();
    }

    @ApiOperation("Return a voting agenda by id")
    @GetMapping("{id}")
    public ResponseEntity<VotingAgendaDTO> getVotingAgendaById(@PathVariable Long id){
        return business.getVotingAgendaById(id);
    }

    @ApiOperation("Create a new voting agenda")
    @PostMapping
    public ResponseEntity<VotingAgendaDTO> create(@RequestBody VotingAgendaDTO votingAgenda){
        return business.save(votingAgenda);
    }

    @ApiOperation("Update a voting agenda")
    @PutMapping
    public ResponseEntity<VotingAgendaDTO> update(@RequestBody VotingAgendaDTO votingAgenda){
        return business.save(votingAgenda);
    }

    @ApiOperation("Delete a existing voting agenda by id")
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        business.delete(id);
    }
}
