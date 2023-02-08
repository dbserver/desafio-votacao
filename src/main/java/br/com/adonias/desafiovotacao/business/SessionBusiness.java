package br.com.adonias.desafiovotacao.business;

import br.com.adonias.desafiovotacao.business.services.ISessionService;
import br.com.adonias.desafiovotacao.dto.SessionDTO;
import br.com.adonias.desafiovotacao.dto.VoteDTO;
import br.com.adonias.desafiovotacao.entities.Session;
import br.com.adonias.desafiovotacao.entities.Vote;
import br.com.adonias.desafiovotacao.mapper.SessionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class SessionBusiness {
    @Autowired
    private ISessionService sessionService;

    @Autowired
    private SessionMapper sessionMapper;

    public ResponseEntity<SessionDTO> getSessionById(Long id) {
        try{
            log.info("Getting session with id {}", id);
            Optional<Session> result = sessionService.getSessionById(id);
            if(result.isPresent()){
                return ResponseEntity.ok(sessionMapper.convertToDto(result.get()));
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            log.error("Occurred an error >>> {} {}", e.getMessage(), e.getCause());
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<List<SessionDTO>> getAllSessions() {
        try{
            log.info("Getting sessions");
            List<Session> result = sessionService.getAllSessions();
            if(result != null){
                return ResponseEntity.ok(result.stream()
                        .map(r -> sessionMapper.convertToDto(r))
                        .collect(Collectors.toList()));
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            log.error("Occurred an error >>> {} {}", e.getMessage(), e.getCause());
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<SessionDTO> create(SessionDTO session) {
        log.info("Saving {}", session.toString());
        try{
            if (validateSession(session)) {
                Session result = sessionService.save(sessionMapper.convertToEntity(session));
                session = sessionMapper.convertToDto(result);
                return ResponseEntity.status(HttpStatus.CREATED).body(session);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            log.error("Occurred an error >>> {} {}", e.getMessage(), e.getCause());
            return ResponseEntity.internalServerError().build();
        }
    }

    private boolean validateSession(SessionDTO session) {
        return session != null && session.getAgenda_id() != null && !sessionService.exists(session.getAgenda_id());
    }

    public ResponseEntity<SessionDTO> update(SessionDTO session) {
        log.info("Updating {}", session.toString());
        try{
            if (sessionService.exists(session.getAgenda_id())) {
                Session result = sessionService.save(sessionMapper.convertToEntity(session));
                session = sessionMapper.convertToDto(result);
                return ResponseEntity.ok(session);
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
