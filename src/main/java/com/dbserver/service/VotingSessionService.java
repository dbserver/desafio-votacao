package com.dbserver.service;

import com.dbserver.exception.BusinessException;
import com.dbserver.exception.ConflictException;
import com.dbserver.exception.EntityNotFoundException;
import com.dbserver.model.dto.VotingSessionCreateDTO;
import com.dbserver.model.dto.VotingSessionDTO;
import com.dbserver.model.entity.VotingSession;
import com.dbserver.model.mapper.VotingSessionMapper;
import com.dbserver.repository.VotingSessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VotingSessionService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private VotingSessionRepository votingSessionRepository;
    @Autowired
    private VotingSessionMapper votingSessionMapper;
    @Autowired
    private AgendaService agendaService;

    public VotingSessionDTO create(VotingSessionCreateDTO votingSessionCreateDTO) {
        logger.info("Starting voting session creation: {}", votingSessionCreateDTO);
        agendaService.findById(votingSessionCreateDTO.getIdAgenda());
        VotingSession voting = votingSessionMapper.toEntity(votingSessionCreateDTO);
        VotingSessionDTO votingSessionDTO = votingSessionMapper.toDTO(this.save(voting));
        logger.info("Voting session created: {}", voting);
        return votingSessionDTO;
    }

    private VotingSession save(VotingSession voting) {
        try {
            return votingSessionRepository.save(voting);
        } catch (DuplicateKeyException e) {
            logger.error("Error saving voting session. Voting already started");
            throw new ConflictException("Voting session already started");
        } catch (RuntimeException e) {
            logger.error("Error saving voting session", e);
            throw new BusinessException();
        }
    }

    public VotingSessionDTO getById(String id) {
        logger.info("Starting voting session search: {}", id);
        VotingSessionDTO votingSessionDTO = votingSessionMapper.toDTO(this.findById(id));
        logger.info("Voting session found: {}", votingSessionDTO);
        return votingSessionDTO;
    }

    public VotingSession findById(String id) {
        return votingSessionRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Voting session not found: {}", id);
                    throw new EntityNotFoundException("Voting session not found: " + id);
                });
    }

    public VotingSession findByIdAgenda(String idAgenda) {
        return votingSessionRepository.findByIdAgenda(idAgenda)
                .orElseThrow(() -> {
                    logger.error("Voting session not found for agenda: {}", idAgenda);
                    throw new EntityNotFoundException("Voting session not found for agenda: " + idAgenda);
                });
    }

    public boolean isOpen(VotingSession voting) {
        LocalDateTime endDate = voting.getEndDate();
        LocalDateTime now = LocalDateTime.now();
        return now.isBefore(endDate);
    }

}
