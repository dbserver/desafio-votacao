package br.com.adonias.desafiovotacao.controllers;

import br.com.adonias.desafiovotacao.business.SessionBusiness;
import br.com.adonias.desafiovotacao.dto.SessionDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sessions")
public class SessionController {

    @Autowired
    private SessionBusiness sessionBusiness;

    @ApiOperation(value = "Returns all sessions")
    @GetMapping
    public ResponseEntity<List<SessionDTO>> getAllSessions(){
        return sessionBusiness.getAllSessions();
    }

    @ApiOperation(value = "Return a session by id")
    @GetMapping(value = "{id}")
    public ResponseEntity<SessionDTO> getSessionById(@PathVariable("id") Long id){
        return sessionBusiness.getSessionById(id);
    }

    @ApiOperation("Create a new session")
    @PostMapping
    public ResponseEntity<SessionDTO> create(@RequestBody SessionDTO session){
        return sessionBusiness.create(session);
    }

    @ApiOperation("Delete a existing session by id")
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        sessionBusiness.delete(id);
    }

    @ApiOperation("Update a session")
    @PutMapping
    public ResponseEntity<SessionDTO> update(@RequestBody SessionDTO session){
        return sessionBusiness.update(session);
    }
}
