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

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private VoteMapper voteMapper;
    @Autowired
    private VotingService votingService;

    public VoteDTO create(VoteCreatedDTO voteCreatedDTO) {
        logger.info("Starting vote creation: {}", voteCreatedDTO);

        Voting voting = votingService.findByIdAgenda(voteCreatedDTO.getIdAgenda());
        if (votingService.isOpen(voting)) {

            Vote vote = voteMapper.toEntity(voteCreatedDTO);
            VoteDTO voteDTO = voteMapper.toDTO(this.save(vote));
            logger.info("Vote created: {}", voteDTO);
            return voteDTO;

        } else {
            logger.error("Voting has already ended");
            throw new ConflictException("Voting has already ended");
        }
    }

    private Vote save(Vote vote) {
        try {
            return voteRepository.save(vote);
        } catch (DuplicateKeyException e) {
            logger.error("Error saving vote, user has already voted");
            throw new ConflictException("User has already voted");
        } catch (RuntimeException e) {
            logger.error("Error saving vote", e);
            throw new BusinessException();
        }
    }

    public List<Vote> findAllByIdAgenda(String idVoting) {
        return voteRepository.findAllByIdAgenda(idVoting);
    }

}
