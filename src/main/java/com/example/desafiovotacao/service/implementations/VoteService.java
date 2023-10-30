package com.example.desafiovotacao.service.implementations;

import com.example.desafiovotacao.dto.ComputingVoteDTO;
import com.example.desafiovotacao.dto.VotedDTO;
import com.example.desafiovotacao.entity.VoteEntity;
import com.example.desafiovotacao.exception.ValidationExceptions;
import com.example.desafiovotacao.exception.VoteExceptions;
import com.example.desafiovotacao.repository.VoteRepository;
import com.example.desafiovotacao.service.interfaces.VoteInterface;
import com.example.desafiovotacao.utils.CpfUtils;
import com.example.desafiovotacao.utils.DateUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VoteService implements VoteInterface {

    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private AssociateService associateService;
    @Autowired
    private SessionService sessionService;

    @Override
    public VotedDTO create(ComputingVoteDTO computingVoteDTO){
        if(computingVoteDTO.getVote() == null || computingVoteDTO.getCpf() == null || computingVoteDTO.getSessionId() == null) {
            ValidationExceptions.faultyInformation();
        }
        if(!CpfUtils.validateCPF(computingVoteDTO.getCpf())){
            ValidationExceptions.invalidCpf();
        }

        VoteEntity newVote = new VoteEntity();
        newVote.setVote(computingVoteDTO.getVote());
        newVote.setAssociate(associateService.getAssociateByCpfIfExists(computingVoteDTO.getCpf()));
        newVote.setSession(sessionService.getSessionByIdIfExists(computingVoteDTO.getSessionId()));

        Optional<VoteEntity> existingVote = voteRepository.findByCpfAndSession(computingVoteDTO.getCpf(), computingVoteDTO.getSessionId());
        if(existingVote.isPresent()){
            VoteExceptions.alreadyVoted();
        }
        if(!newVote.getSession().isSessionRunning()){
            VoteExceptions.sessionClosed();
        }

        VoteEntity savedVoted = voteRepository.save(newVote);

        return VotedDTO.builder()
                .voteId(savedVoted.getId())
                .computedVote(savedVoted.getVote() ? "Sim" : "Não")
                .cpfAssociate(savedVoted.getAssociate().getCpf())
                .sessionId(savedVoted.getSession().getId())
                .sessionDate(DateUtils.formatDate(savedVoted.getSession().getCreationDate()))
                .topic(savedVoted.getSession().getRuling().getTitle())
                .build();
    }
}
