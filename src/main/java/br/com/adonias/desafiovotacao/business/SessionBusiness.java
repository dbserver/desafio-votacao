package br.com.adonias.desafiovotacao.business;

import br.com.adonias.desafiovotacao.business.services.ISessionService;
import br.com.adonias.desafiovotacao.dto.VoteDTO;
import br.com.adonias.desafiovotacao.entities.Session;
import br.com.adonias.desafiovotacao.entities.Vote;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class SessionBusiness {
    @Autowired
    private ISessionService sessionService;

    public ResponseEntity<Session> getSessionById(Long id) {
        try{
            log.info("Getting session with id {}", id);
            Optional<Session> result = sessionService.getSessionById(id);
            if(result.isPresent()){
                return ResponseEntity.ok(result.get());
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            log.error("Occurred an error >>> {} {}", e.getMessage(), e.getCause());
            return ResponseEntity.internalServerError().build();
        }
    }


    public ResponseEntity<Session> save(Session session) {
        try{
            if (session != null) {
                log.info("Saving {}", session.toString());
                Session result = sessionService.save(session);
                if (session.getAgenda_id() == null) {
                    return ResponseEntity.status(HttpStatus.CREATED).body(result);
                }
                return ResponseEntity.ok(result);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            log.error("Occurred an error >>> {} {}", e.getMessage(), e.getCause());
            return ResponseEntity.internalServerError().build();
        }
    }

    public void delete(Long id) {
        try {
            log.info("Remove session with id {}", id);
            sessionService.deleteById(id);
        }catch (Exception e){
            log.error("Occurred an error >>> {} {}", e.getMessage(), e.getCause());
        }
    }
}
