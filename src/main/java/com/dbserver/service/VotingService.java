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

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private VotingRepository votingRepository;
    @Autowired
    private VotingMapper votingMapper;

    @Autowired
    private AgendaService agendaService;

    public VotingDTO create(VotingCreateDTO votingCreateDTO) {
        agendaService.verifyIfexistsById(votingCreateDTO.getIdAgenda());
        Voting voting = votingMapper.toEntity(votingCreateDTO);
        return votingMapper.toDTO(this.save(voting));
    }

    private Voting save(Voting voting) {
        try {
            return votingRepository.save(voting);
        } catch (DuplicateKeyException e) {
            throw new ConflictException("Voting already started");
        } catch (RuntimeException e) {
            throw new BusinessException();
        }
    }

    public VotingDTO getById(String id) {
        return votingMapper.toDTO(this.findById(id));
    }

    public Voting findById(String idAgenda) {
        return votingRepository.findById(idAgenda)
                .orElseThrow(() -> {
                    LOGGER.error("Voting not found: {}", idAgenda);
                    throw new EntityNotFoundException("Voting not found");
                });
    }

    public Voting findByIdAgenda(String idAgenda) {
        return votingRepository.findByIdAgenda(idAgenda)
                .orElseThrow(() -> {
                    LOGGER.error("Voting not found: {}", idAgenda);
                    throw new EntityNotFoundException("Voting not found");
                });
    }

    public boolean isOpen(Voting voting){
        LocalDateTime endDate = voting.getEndDate();
        LocalDateTime now = LocalDateTime.now();
        return now.isBefore(endDate);
    }

}
