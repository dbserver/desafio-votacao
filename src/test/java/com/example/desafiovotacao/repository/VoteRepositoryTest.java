package com.example.desafiovotacao.repository;

import com.example.desafiovotacao.entity.AssociateEntity;
import com.example.desafiovotacao.entity.RulingEntity;
import com.example.desafiovotacao.entity.SessionEntity;
import com.example.desafiovotacao.entity.VoteEntity;
import com.example.desafiovotacao.utils.CpfUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.Optional;

@DataJpaTest
public class VoteRepositoryTest {

    @Autowired
    private AssociateRepository associateRepository;
    @Autowired
    private RulingRepository rulingRepository;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private VoteRepository voteRepository;

    @Mock
    private AssociateEntity createdAssociate;
    private RulingEntity createdRuling;
    private SessionEntity createdSession;
    private VoteEntity computedVote;

    @BeforeEach
    void setup() {
        createdAssociate = associateRepository.save(
                AssociateEntity.builder()
                        .creationDate(new Date())
                        .cpf(CpfUtils.generateCPF())
                        .name("Test user")
                        .build()
        );
        createdRuling = rulingRepository.save(
                RulingEntity.builder()
                        .title("Mock title")
                        .description("Mock description")
                        .creationDate(new Date())
                        .build()
        );
        createdSession = sessionRepository.save(
                SessionEntity.builder()
                        .ruling(createdRuling)
                        .creationDate(new Date())
                        .duration(60)
                        .build()
        );
        computedVote = voteRepository.save(
                VoteEntity.builder()
                        .vote(true)
                        .session(createdSession)
                        .associate(createdAssociate)
                        .creationDate(new Date())
                        .build()
        );
    }

    @Test
    void shouldFindCpfByAssociate() {
        Optional<VoteEntity> vote = voteRepository.findByCpfAndSession(createdAssociate.getCpf(), createdSession.getId());
        Assertions.assertFalse(vote.isEmpty());
        Assertions.assertEquals(
                createdAssociate.getCpf(),
                vote.get());
    }

}
