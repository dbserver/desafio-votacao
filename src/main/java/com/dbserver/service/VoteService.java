package com.dbserver.service;

import com.dbserver.exception.BusinessException;
import com.dbserver.exception.ConflictException;
import com.dbserver.model.dto.VoteCreatedDTO;
import com.dbserver.model.dto.VoteDTO;
import com.dbserver.model.entity.Vote;
import com.dbserver.model.entity.Voting;
import com.dbserver.model.mapper.VoteMapper;
import com.dbserver.repository.VoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VoteService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private VoteMapper voteMapper;
    @Autowired
    private VotingService votingService;

    public VoteDTO create(VoteCreatedDTO voteCreatedDTO) {
        LOGGER.info("Starting vote creation: {}", voteCreatedDTO);

        Voting voting = votingService.findByIdAgenda(voteCreatedDTO.getIdAgenda());
        if (votingService.isOpen(voting)) {

            Vote vote = voteMapper.toEntity(voteCreatedDTO);
            VoteDTO voteDTO = voteMapper.toDTO(this.save(vote));
            LOGGER.info("Vote created: {}", voteDTO);
            return voteDTO;

        } else {
            LOGGER.error("Voting has already ended");
            throw new ConflictException("Voting has already ended");
        }
    }

    private Vote save(Vote vote) {
        try {
            return voteRepository.save(vote);
        } catch (DuplicateKeyException e) {
            LOGGER.error("Error saving vote", e);
            throw new ConflictException("User has already voted");
        } catch (RuntimeException e) {
            LOGGER.error("Error saving vote", e);
            throw new BusinessException();
        }
    }

    public List<Vote> findAllByIdAgenda(String idVoting) {
        return voteRepository.findAllByIdAgenda(idVoting);
    }

}
