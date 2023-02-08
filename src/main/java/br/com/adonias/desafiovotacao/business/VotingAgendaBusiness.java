package br.com.adonias.desafiovotacao.business;

import br.com.adonias.desafiovotacao.business.services.IVotingAgendaService;
import br.com.adonias.desafiovotacao.dto.VotingAgendaDTO;
import br.com.adonias.desafiovotacao.entities.VotingAgenda;
import br.com.adonias.desafiovotacao.mapper.VotingAgendaMapper;
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
public class VotingAgendaBusiness {
    @Autowired
    private IVotingAgendaService votingAgendaService;

    @Autowired
    private VotingAgendaMapper votingAgendaMapper;

    public ResponseEntity<VotingAgendaDTO> getVotingAgendaById(Long id) {
        try{
            log.info("Getting agenda with id {}", id);
            Optional<VotingAgenda> result = votingAgendaService.getVotingAgendaById(id);
            if(result.isPresent()){
                return ResponseEntity.ok(votingAgendaMapper.convertToDto(result.get()));
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            log.error("Occurred an error >>> {} {}", e.getMessage(), e.getCause());
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<List<VotingAgendaDTO>> getAllVotingAgendas() {
        try{
            log.info("Getting voting agendas");
            List<VotingAgenda> result = votingAgendaService.getAllVotingAgendas();
            if(result != null){
                return ResponseEntity.ok(result.stream()
                        .map(r -> votingAgendaMapper.convertToDto(r))
                        .collect(Collectors.toList()));
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            log.error("Occurred an error >>> {} {}", e.getMessage(), e.getCause());
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<VotingAgendaDTO> save(VotingAgendaDTO agenda) {
        try{
            if (agenda != null) {
                log.info("Saving {}", agenda.toString());
                VotingAgenda result = votingAgendaService.save(votingAgendaMapper.convertToEntity(agenda));
                if (agenda.getId() == null) {
                    return ResponseEntity.status(HttpStatus.CREATED).body(votingAgendaMapper.convertToDto(result));
                }
                return ResponseEntity.ok(votingAgendaMapper.convertToDto(result));
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            log.error("Occurred an error >>> {} {}", e.getMessage(), e.getCause());
            return ResponseEntity.internalServerError().build();
        }
    }

    public void delete(Long id) {
        try {
            log.info("Remove agenda with id {}", id);
            votingAgendaService.delete(id);
        }catch (Exception e){
            log.error("Occurred an error >>> {} {}", e.getMessage(), e.getCause());
        }
    }

}
