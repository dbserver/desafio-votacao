package br.com.adonias.desafiovotacao.controllers;

import br.com.adonias.desafiovotacao.business.SessionBusiness;
import br.com.adonias.desafiovotacao.dto.SessionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sessions")
public class SessionController {

    @Autowired
    private SessionBusiness sessionBusiness;

    @GetMapping
    public ResponseEntity<List<SessionDTO>> getAllVotes(){
        return sessionBusiness.getAllSessions();
    }

    @GetMapping("{id}")
    public ResponseEntity<SessionDTO> getSessionById(@PathVariable("id") Long id){
        return sessionBusiness.getSessionById(id);
    }

    @PostMapping
    public ResponseEntity<SessionDTO> create(@RequestBody SessionDTO session){
        return sessionBusiness.save(session);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        sessionBusiness.delete(id);
    }

    @PutMapping
    public ResponseEntity<SessionDTO> update(@RequestBody SessionDTO session){
        return sessionBusiness.save(session);
    }
}
