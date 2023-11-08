package com.example.desafiovotacao.services;

import com.example.desafiovotacao.dto.ComputingVoteDTO;
import com.example.desafiovotacao.dto.VotedDTO;
import com.example.desafiovotacao.entity.AssociateEntity;
import com.example.desafiovotacao.entity.RulingEntity;
import com.example.desafiovotacao.entity.SessionEntity;
import com.example.desafiovotacao.entity.VoteEntity;
import com.example.desafiovotacao.exception.ValidationExceptions;
import com.example.desafiovotacao.exception.VoteExceptions;
import com.example.desafiovotacao.repository.AssociateRepository;
import com.example.desafiovotacao.repository.RulingRepository;
import com.example.desafiovotacao.repository.SessionRepository;
import com.example.desafiovotacao.repository.VoteRepository;
import com.example.desafiovotacao.service.implementations.VoteServiceImpl;
import com.example.desafiovotacao.utils.CpfUtils;
import com.example.desafiovotacao.utils.DateUtils;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
public class VoteServiceTest {

    @Autowired
    private VoteServiceImpl voteService;
    @Autowired
    private AssociateRepository associateRepository;
    @Autowired
    private RulingRepository rulingRepository;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private VoteRepository voteRepository;

    private AssociateEntity defaultAssociate;
    private RulingEntity defaultRuling;
    private RulingEntity closedRuling;
    private SessionEntity defaultSession;
    private SessionEntity sessionNotRunning;

    @BeforeEach
    void setup() {
        defaultAssociate = associateRepository.save(
                AssociateEntity.builder()
                        .name("Name test")
                        .cpf(CpfUtils.generateCPF())
                        .creationDate(new Date())
                        .build()
        );
        defaultRuling = rulingRepository.save(
                RulingEntity.builder()
                        .title("Title test")
                        .description("Description test")
                        .creationDate(new Date())
                        .build()
        );
        closedRuling = rulingRepository.save(
                RulingEntity.builder()
                        .title("Title test")
                        .description("Description test")
                        .results(true)
                        .voteCountDate(new Date())
                        .creationDate(new Date())
                        .build()
        );
        defaultSession = sessionRepository.save(
                SessionEntity.builder()
                        .ruling(defaultRuling)
                        .duration(60)
                        .creationDate(new Date())
                        .build()
        );
        sessionNotRunning = sessionRepository.save(
                SessionEntity.builder()
                        .ruling(defaultRuling)
                        .duration(-10)
                        .creationDate(new Date())
                        .build()
        );
    }

    @AfterEach
    void teardown() {
        voteRepository.deleteAll();
        sessionRepository.deleteAll();
        rulingRepository.deleteAll();
        associateRepository.deleteAll();
    }

    @Test
    void shouldComputeVote() {
        VotedDTO votedDTO = voteService.create(
                ComputingVoteDTO.builder()
                        .vote(true)
                        .sessionId(defaultSession.getId())
                        .cpf(defaultAssociate.getCpf())
                        .build()
        );
        VoteEntity foundVote = voteRepository.findById(votedDTO.getVoteId()).get();

        assertNotNull(votedDTO);
        assertEquals(foundVote.getVote() ? "Sim" : "Não", votedDTO.getComputedVote());
        assertEquals(foundVote.getId(), votedDTO.getVoteId());
        assertEquals(foundVote.getSession().getId(), votedDTO.getSessionId());
        assertEquals(foundVote.getSession().getRuling().getTitle(), votedDTO.getTopic());
        assertEquals(DateUtils.formatDate(foundVote.getSession().getCreationDate()), votedDTO.getSessionDate());
        assertEquals(foundVote.getAssociate().getCpf(), votedDTO.getCpfAssociate());
    }

    @Test
    void shouldInvalidateInformationWhileComputingVote() {
        assertThrows(ValidationExceptions.class, () -> {
           voteService.create(
                   ComputingVoteDTO.builder()
                           .cpf(null)
                           .sessionId(null)
                           .vote(null)
                           .build()
           );
        });
    }

    @Test
    void shouldThrowInvalidCPFWhileComputingVote() {
        assertThrows(ValidationExceptions.class, () -> {
            voteService.create(
                    ComputingVoteDTO.builder()
                            .cpf("00000000000")
                            .sessionId(defaultSession.getId())
                            .vote(true)
                            .build()
            );
        });
    }

    @Test
    void shouldThrowAlreadyVotedWhileComputingVote() {
        voteRepository.save(
                VoteEntity.builder()
                        .associate(defaultAssociate)
                        .session(defaultSession)
                        .vote(true)
                        .creationDate(new Date())
                        .build()
        );

        assertThrows(VoteExceptions.class, () -> {
           voteService.create(
                   ComputingVoteDTO.builder()
                       .vote(true)
                       .sessionId(defaultSession.getId())
                       .cpf(defaultAssociate.getCpf())
                       .build()
           );
        });
    }

    @Test
    void shouldThrowSessionClosedExceptionWhileComputingVote() {
        assertThrows(VoteExceptions.class, () -> {
            voteService.create(
                    ComputingVoteDTO.builder()
                            .vote(true)
                            .sessionId(sessionNotRunning.getId())
                            .cpf(defaultAssociate.getCpf())
                            .build()
            );
        });
    }

    @Test
    void shouldThrowFaultyInformationAtValidateComputingVoteInformation() {
        assertThrows(ValidationExceptions.class, () -> {
            voteService.validateComputingVoteInformation(new ComputingVoteDTO());
        });
    }

}
