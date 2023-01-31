package br.com.adonias.desafiovotacao.business;

import br.com.adonias.desafiovotacao.business.services.impl.SessionServiceImpl;
import br.com.adonias.desafiovotacao.business.services.impl.VoteServiceImpl;
import br.com.adonias.desafiovotacao.dto.VoteDTO;
import br.com.adonias.desafiovotacao.entities.Session;
import br.com.adonias.desafiovotacao.entities.Vote;
import br.com.adonias.desafiovotacao.mapper.VoteMapper;
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
public class VoteBusiness {
    @Autowired
    private VoteServiceImpl voteService;

    @Autowired
    private SessionServiceImpl sessionService;

    @Autowired
    private VoteMapper mapper;

    public ResponseEntity<VoteDTO> getVoteById(Long id) {
        try{
            log.info("Getting votes with id {}", id);
            Optional<Vote> result = voteService.getVoteById(id);
            if(result.isPresent()){
                return ResponseEntity.ok(mapper.convertToDto(result.get()));
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            log.error("Occurred an error >>> {} {}", e.getMessage(), e.getCause());
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<List<VoteDTO>> getAllVotesByAgendaId(Long id) {
        try{
            log.info("Getting votes with id {}", id);
            List<Vote> result = voteService.getVotesByAgendaId(id);
            if(result != null){
                return ResponseEntity.ok(result.stream()
                        .map(r -> mapper.convertToDto(r))
                        .collect(Collectors.toList()));
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            log.error("Occurred an error >>> {} {}", e.getMessage(), e.getCause());
            return ResponseEntity.internalServerError().build();
        }
    }
    public ResponseEntity<List<VoteDTO>> getAllVotes() {
        try{
            log.info("Getting votes");
            List<Vote> result = voteService.getAllVotes();
            if(result != null){
                return ResponseEntity.ok(result.stream()
                        .map(r -> mapper.convertToDto(r))
                        .collect(Collectors.toList()));
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            log.error("Occurred an error >>> {} {}", e.getMessage(), e.getCause());
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<VoteDTO> create(VoteDTO vote) {
        try{
            if (vote != null && vote.getAgendaId() != null) {
                Session session = sessionService.getSessionById(vote.getAgendaId()).get();
                if (validateVote(vote, session)) {
                    log.info("Saving {}", vote.toString());
                    Vote result = voteService.create(mapper.convertToEntity(vote));
                    return ResponseEntity.status(HttpStatus.CREATED).body(mapper.convertToDto(result));
                }
            }
            return ResponseEntity.badRequest().build();
        }
        catch (Exception e){
            log.error("Occurred an error >>> {} {}", e.getMessage(), e.getCause());
            return ResponseEntity.internalServerError().build();
        }
    }


    public ResponseEntity<VoteDTO> update(VoteDTO vote) {
        try{
            if (vote != null && vote.getId() != null) {
                Session session = sessionService.getSessionById(vote.getAgendaId()).get();
                if (validateVote(vote, session)) {
                    log.info("Saving {}", vote.toString());

                    Vote result = voteService.create(mapper.convertToEntity(vote));
                    return ResponseEntity.ok(mapper.convertToDto(result));
                }
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            log.error("Occurred an error >>> {} {}", e.getMessage(), e.getCause());
            return ResponseEntity.internalServerError().build();
        }
    }

    public void delete(Long id) {
        try {
            log.info("Remove vote with id {}", id);
            voteService.delete(id);
        }catch (Exception e){
            log.error("Occurred an error >>> {} {}", e.getMessage(), e.getCause());
        }
    }

    private static boolean validateVote(VoteDTO vote, Session session) {
        return session != null
                && (vote.getDateTime().isAfter(session.getStartDate()) ||  vote.getDateTime().isEqual(session.getStartDate()))
                && (vote.getDateTime().isBefore(session.getEndDate()) || vote.getDateTime().isEqual(session.getEndDate()));
    }
}
