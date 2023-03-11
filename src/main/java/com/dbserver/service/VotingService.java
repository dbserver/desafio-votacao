package com.dbserver.service;

import com.dbserver.exception.BusinessException;
import com.dbserver.exception.ConflictException;
import com.dbserver.exception.EntityNotFoundException;
import com.dbserver.model.dto.VotingCreateDTO;
import com.dbserver.model.dto.VotingDTO;
import com.dbserver.model.entity.Voting;
import com.dbserver.model.mapper.VotingMapper;
import com.dbserver.repository.VotingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VotingService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private VotingRepository votingRepository;
    @Autowired
    private VotingMapper votingMapper;
    @Autowired
    private AgendaService agendaService;

    public VotingDTO create(VotingCreateDTO votingCreateDTO) {
        logger.info("Starting voting creation: {}", votingCreateDTO);
        agendaService.findById(votingCreateDTO.getIdAgenda());
        Voting voting = votingMapper.toEntity(votingCreateDTO);
        VotingDTO votingDTO = votingMapper.toDTO(this.save(voting));
        logger.info("Voting created: {}", voting);
        return votingDTO;
    }

    private Voting save(Voting voting) {
        try {
            return votingRepository.save(voting);
        } catch (DuplicateKeyException e) {
            logger.error("Error saving voting. Voting already started");
            throw new ConflictException("Voting already started");
        } catch (RuntimeException e) {
            logger.error("Error saving voting", e);
            throw new BusinessException();
        }
    }

    public VotingDTO getById(String id) {
        logger.info("Starting voting search: {}", id);
        VotingDTO votingDTO = votingMapper.toDTO(this.findById(id));
        logger.info("Voting found: {}", votingDTO);
        return votingDTO;
    }

    public Voting findById(String id) {
        return votingRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Voting not found: {}", id);
                    throw new EntityNotFoundException("Voting not found: " + id);
                });
    }

    public Voting findByIdAgenda(String idAgenda) {
        return votingRepository.findByIdAgenda(idAgenda)
                .orElseThrow(() -> {
                    logger.error("Voting not found for id: {}", idAgenda);
                    throw new EntityNotFoundException("Voting not found for idAgenda: " + idAgenda);
                });
    }

    public boolean isOpen(Voting voting) {
        LocalDateTime endDate = voting.getEndDate();
        LocalDateTime now = LocalDateTime.now();
        return now.isBefore(endDate);
    }

}
