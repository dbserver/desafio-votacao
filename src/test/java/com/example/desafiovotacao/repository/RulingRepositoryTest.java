package com.example.desafiovotacao.repository;

import com.example.desafiovotacao.dto.RulingReturnDTO;
import com.example.desafiovotacao.dto.VotesDTO;
import com.example.desafiovotacao.entity.AssociateEntity;
import com.example.desafiovotacao.entity.RulingEntity;
import com.example.desafiovotacao.entity.SessionEntity;
import com.example.desafiovotacao.entity.VoteEntity;
import com.example.desafiovotacao.utils.CpfUtils;
import com.example.desafiovotacao.utils.DateUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class RulingRepositoryTest {

    @Autowired
    private RulingRepository rulingRepository;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private AssociateRepository associateRepository;
    @Autowired
    private VoteRepository voteRepository;

    @Mock
    private RulingEntity createdRuling;
    private AssociateEntity createdAssociate;
    private SessionEntity createdSession;
    private VoteEntity computedVote;

    @BeforeEach
    void setup() {
        createdAssociate = associateRepository.save(
                AssociateEntity.builder()
                        .creationDate(new Date())
                        .name("User test")
                        .cpf(CpfUtils.generateCPF())
                        .build()
            );
        createdRuling = rulingRepository.save(
                RulingEntity.builder()
                        .creationDate(new Date())
                        .title("Mock ruling")
                        .description("Mock ruling description")
                        .build()
        );
        createdSession = sessionRepository.save(
                SessionEntity.builder()
                        .creationDate(new Date())
                        .ruling(createdRuling)
                        .duration(60)
                        .build()
        );
        computedVote = voteRepository.save(
                VoteEntity.builder()
                        .vote(true)
                        .creationDate(new Date())
                        .associate(createdAssociate)
                        .session(createdSession)
                        .build()
        );
    }

    @Test
    void shouldReturnRulingDTO() {
        Optional<RulingReturnDTO> returnDTO = rulingRepository.listReturnById(createdRuling.getId());
        Assertions.assertNotNull(returnDTO);
        Assertions.assertFalse(returnDTO.isEmpty());
        Assertions.assertEquals(
                RulingReturnDTO.builder()
                        .id(createdRuling.getId())
                        .creationDate(DateUtils.formatDate(createdRuling.getCreationDate()))
                        .result("NÃO CONTABILIZADO")
                        .resultDate(DateUtils.formatDate(createdRuling.getVoteCountDate()))
                        .description(createdRuling.getDescription())
                        .title(createdRuling.getTitle())
                        .build(),
                returnDTO.get()
        );
    }

    @Test
    void shouldReturnRulingListDTO() {
        List<RulingReturnDTO> returnDTOList = rulingRepository.listAllReturn();
        Assertions.assertNotNull(returnDTOList);
        Assertions.assertEquals(returnDTOList.size(), 1);
    }

    @Test
    void shouldCountVotesDTO() {
        VotesDTO votesDTO = rulingRepository.countVotes(computedVote.getSession().getId());
        Assertions.assertNotNull(votesDTO);
        Assertions.assertEquals(votesDTO, new VotesDTO(1L, 0L));
    }

}
