package br.com.adonias.desafiovotacao.business;

import br.com.adonias.desafiovotacao.business.services.impl.SessionServiceImpl;
import br.com.adonias.desafiovotacao.business.services.impl.VoteServiceImpl;
import br.com.adonias.desafiovotacao.dto.VoteDTO;
import br.com.adonias.desafiovotacao.entities.Vote;
import br.com.adonias.desafiovotacao.entities.VotingAgenda;
import br.com.adonias.desafiovotacao.mapper.VoteMapper;
import br.com.adonias.desafiovotacao.mapper.VotingAgendaMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<VoteDTO> save(VoteDTO vote) {
        try{
            if (vote != null) {
                log.info("Saving {}", vote.toString());
                Vote result = voteService.save(mapper.convertToEntity(vote));
                if (vote.getId() == null) {
                    return ResponseEntity.status(HttpStatus.CREATED).body(mapper.convertToDto(result));
                }
                return ResponseEntity.ok(mapper.convertToDto(result));
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
}
