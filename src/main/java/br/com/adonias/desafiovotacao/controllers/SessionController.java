package br.com.adonias.desafiovotacao.controllers;

import br.com.adonias.desafiovotacao.business.SessionBusiness;
import br.com.adonias.desafiovotacao.entities.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sessions")
public class SessionController {

    @Autowired
    private SessionBusiness sessionBusiness;

    @GetMapping("{id}")
    public ResponseEntity<Session> getSessionById(@PathVariable("id") Long id){
        return sessionBusiness.getSessionById(id);
    }

    @PostMapping
    public ResponseEntity<Session> create(@RequestBody Session session){
        return sessionBusiness.save(session);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        sessionBusiness.delete(id);
    }

    @PutMapping
    public ResponseEntity<Session> update(@RequestBody Session session){
        return sessionBusiness.save(session);
    }
}
