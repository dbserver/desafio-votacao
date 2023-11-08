package com.example.desafiovotacao.services;

import com.example.desafiovotacao.dto.CountingResultsDTO;
import com.example.desafiovotacao.dto.CreatedRulingDTO;
import com.example.desafiovotacao.dto.RegisterRulingDTO;
import com.example.desafiovotacao.dto.RulingReturnDTO;
import com.example.desafiovotacao.entity.AssociateEntity;
import com.example.desafiovotacao.entity.RulingEntity;
import com.example.desafiovotacao.entity.SessionEntity;
import com.example.desafiovotacao.entity.VoteEntity;
import com.example.desafiovotacao.exception.ValidationExceptions;
import com.example.desafiovotacao.repository.AssociateRepository;
import com.example.desafiovotacao.repository.RulingRepository;
import com.example.desafiovotacao.repository.SessionRepository;
import com.example.desafiovotacao.repository.VoteRepository;
import com.example.desafiovotacao.service.implementations.RulingServiceImpl;
import com.example.desafiovotacao.utils.CpfUtils;
import com.example.desafiovotacao.utils.DateUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
public class RulingServiceTest {

    @Autowired
    private RulingServiceImpl rulingService;
    @Autowired
    private RulingRepository rulingRepository;
    @Autowired
    private AssociateRepository associateRepository;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private VoteRepository voteRepository;

    private RegisterRulingDTO registerRulingDTO;
    private RulingEntity defaultCreateRuling;

    @BeforeEach
    void setup() {
        registerRulingDTO = RegisterRulingDTO.builder()
                .title("Test title")
                .description("Test description")
                .build();
        defaultCreateRuling = RulingEntity.builder()
                .title("Title test")
                .description("Description test")
                .creationDate(new Date())
                .build();
    }

    @AfterEach
    void teardown() {
        voteRepository.deleteAll();
        sessionRepository.deleteAll();
        rulingRepository.deleteAll();
        associateRepository.deleteAll();
    }

    @Test
    void shouldCreateRuling() {
        CreatedRulingDTO createdRuling = rulingService.create(registerRulingDTO);
        RulingEntity searchRuling = rulingRepository.findById(createdRuling.getRulingId()).get();

        assertNotNull(createdRuling);
        assertEquals(searchRuling.getId(), createdRuling.getRulingId());
        assertEquals(searchRuling.getTitle(), createdRuling.getTitle());
        assertEquals(searchRuling.getDescription(), createdRuling.getDescription());
    }

    @Test
    void shouldThrowValidationExceptionAtRulingCreation() {
        assertThrows(ValidationExceptions.class, () -> {
           rulingService.create(
                   RegisterRulingDTO.builder()
                           .description(null)
                           .title(null)
                           .build()
           );
        });
    }

    @Test
    void shouldCountVotes() {
        AssociateEntity newAssociate = associateRepository.save(
                AssociateEntity.builder()
                        .cpf(CpfUtils.generateCPF())
                        .name("Name test")
                        .build()
        );
        RulingEntity newRuling = rulingRepository.save(defaultCreateRuling);
        SessionEntity newSession = sessionRepository.save(
                SessionEntity.builder()
                        .creationDate(new Date())
                        .duration(-1)
                        .ruling(newRuling)
                        .build()
        );
        voteRepository.save(
                VoteEntity.builder()
                        .vote(true)
                        .session(newSession)
                        .associate(newAssociate)
                        .build()
        );

        CountingResultsDTO countedVotes = rulingService.countVotes(newRuling.getId());

        assertNotNull(countedVotes);
        assertEquals(0, countedVotes.getAgainstVotes());
        assertEquals(1, countedVotes.getInFavorVotes());
        assertEquals(newRuling.getDescription(), countedVotes.getDescription());
        assertEquals(newRuling.getTitle(), countedVotes.getTitle());
        assertEquals("Sim", countedVotes.getResult());
        assertEquals(DateUtils.formatDate(newRuling.getCreationDate()), countedVotes.getCreationDate());
        assertEquals(DateUtils.formatDate(newSession.getCreationDate()), countedVotes.getSessionDate());
    }

    @Test
    void shouldThrowRulingEndedExceptionWhenCountingVotes() {
        RulingEntity newRuling = rulingRepository.save(
                RulingEntity.builder()
                        .title("Title test count")
                        .description("Description test count")
                        .results(true)
                        .creationDate(new Date())
                        .voteCountDate(new Date())
                        .build()
        );

        assertThrows(ValidationExceptions.class, () -> {
           rulingService.countVotes(newRuling.getId());
        });
    }

    @Test
    void shouldThrowSessionStillRunningWhenCountingVotes() {
        RulingEntity newRuling = rulingRepository.save(defaultCreateRuling);
        sessionRepository.save(
                SessionEntity.builder()
                        .creationDate(new Date())
                        .duration(60)
                        .ruling(newRuling)
                        .build()
        );

        assertThrows(ValidationExceptions.class, () -> {
           rulingService.countVotes(newRuling.getId());
        });
    }

    @Test
    void shouldThrowTiedVoteWhenCountingVotes() {
        AssociateEntity newAssociate = associateRepository.save(
                AssociateEntity.builder()
                        .cpf(CpfUtils.generateCPF())
                        .name("Name test")
                        .build()
        );
        AssociateEntity anotherNewAssociate = associateRepository.save(
                AssociateEntity.builder()
                        .cpf(CpfUtils.generateCPF())
                        .name("Name test")
                        .build()
        );
        RulingEntity newRuling = rulingRepository.save(defaultCreateRuling);
        SessionEntity newSession = sessionRepository.save(
                SessionEntity.builder()
                        .creationDate(new Date())
                        .duration(-1)
                        .ruling(newRuling)
                        .build()
        );
        voteRepository.save(
                VoteEntity.builder()
                        .vote(true)
                        .session(newSession)
                        .associate(newAssociate)
                        .build()
        );
        voteRepository.save(
                VoteEntity.builder()
                        .vote(false)
                        .session(newSession)
                        .associate(anotherNewAssociate)
                        .build()
        );

        assertThrows(ValidationExceptions.class, () -> {
            rulingService.countVotes(newRuling.getId());
        });
    }

    @Test
    void shouldReturnAllList() {
        rulingRepository.save(defaultCreateRuling);
        rulingRepository.save(defaultCreateRuling);

        List<RulingEntity> allRulingsRep = rulingRepository.findAll();
        List<RulingReturnDTO> listedRuling = rulingService.listAll();

        assertNotNull(listedRuling);
        assertEquals(allRulingsRep.size(), listedRuling.size());
    }

    @Test
    void shouldReturnRulingEntityById() {
        RulingEntity newRuling = rulingRepository.save(defaultCreateRuling);

        RulingEntity getRulingEntity = rulingService.getRulingEntityIfExists(newRuling.getId());

        assertNotNull(getRulingEntity);
        assertEquals(newRuling.getId(), getRulingEntity.getId());
        assertEquals(newRuling.getTitle(), getRulingEntity.getTitle());
        assertEquals(newRuling.getDescription(), getRulingEntity.getDescription());
        assertEquals(newRuling.getResults(), getRulingEntity.getResults());
        assertEquals(newRuling.getVoteCountDate(), getRulingEntity.getVoteCountDate());
    }

    @Test
    void shouldReturnRulingDoesNotExistOnGetRulingEntity() {
        assertThrows(ValidationExceptions.class, () -> {
            rulingService.getRulingEntityIfExists(1);
        });
    }

    @Test
    void shouldGetRulingReturn() {
        RulingEntity newRuling = rulingRepository.save(defaultCreateRuling);
        RulingReturnDTO rulingReturnDTO = rulingService.getRulingReturnIfExists(newRuling.getId());

        assertNotNull(rulingReturnDTO);
        assertEquals(newRuling.getId(), rulingReturnDTO.getId());
        assertEquals(newRuling.getTitle(), rulingReturnDTO.getTitle());
        assertEquals(newRuling.getDescription(), rulingReturnDTO.getDescription());
        assertEquals(DateUtils.formatDate(newRuling.getCreationDate()), rulingReturnDTO.getCreationDate());
        assertEquals(DateUtils.formatDate(newRuling.getVoteCountDate()), rulingReturnDTO.getResultDate());
        assertEquals("NÃO CONTABILIZADO", rulingReturnDTO.getResult());
    }

    @Test
    void shouldThrowRulingExceptionOnGetRulingReturn() {
        assertThrows(ValidationExceptions.class, () -> {
           rulingService.getRulingReturnIfExists(1);
        });
    }


    @Test
    void shouldThrowFaultyInformationAtValidateRegisterRuling() {
        assertThrows(ValidationExceptions.class, () -> {
           rulingService.validateRegisterRulingDTO(new RegisterRulingDTO());
        });
    }

}
