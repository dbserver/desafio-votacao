package com.example.desafiovotacao.services;

import com.example.desafiovotacao.dto.CreatedSessionDTO;
import com.example.desafiovotacao.dto.SessionReturnDTO;
import com.example.desafiovotacao.dto.StartSessionDTO;
import com.example.desafiovotacao.entity.RulingEntity;
import com.example.desafiovotacao.entity.SessionEntity;
import com.example.desafiovotacao.exception.ValidationExceptions;
import com.example.desafiovotacao.repository.RulingRepository;
import com.example.desafiovotacao.repository.SessionRepository;
import com.example.desafiovotacao.service.implementations.SessionServiceImpl;
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
public class SessionServiceTest {

    @Autowired
    private SessionServiceImpl sessionService;
    @Autowired
    private RulingRepository rulingRepository;
    @Autowired
    private SessionRepository sessionRepository;

    private RulingEntity defaultRuling;
    private SessionEntity defaultSession;

    @BeforeEach
    void setup() {
        defaultRuling = rulingRepository.save(
                RulingEntity.builder()
                        .title("Title test")
                        .description("Description test")
                        .creationDate(new Date())
                        .build()
        );
        defaultSession = sessionRepository.save(
                SessionEntity.builder()
                        .ruling(defaultRuling)
                        .creationDate(new Date())
                        .duration(60)
                        .build()
        );
    }

    @AfterEach
    void teardown() {
        sessionRepository.deleteAll();
        rulingRepository.deleteAll();
    }

    @Test
    void shouldCreateSession() {
        CreatedSessionDTO createdSessionDTO = sessionService.create(
                    StartSessionDTO.builder()
                            .rulingId(defaultRuling.getId())
                            .duration(60)
                            .build()
            );
        SessionEntity newSession = sessionRepository.findById(createdSessionDTO.getSessionId()).get();

        assertNotNull(createdSessionDTO);
        assertEquals(newSession.getId(), createdSessionDTO.getSessionId());
        assertEquals(newSession.getDuration(), createdSessionDTO.getDuration());
    }

    @Test
    void shouldThrowFaultyInformationAtCreation() {
        assertThrows(ValidationExceptions.class, () -> {
            sessionService.create(
                    StartSessionDTO.builder()
                            .rulingId(null)
                            .duration(60)
                            .build()
            );
        });
    }

    @Test
    void shouldThrowRulingDoesNotExistAtCreation() {
        assertThrows(ValidationExceptions.class, () -> {
            sessionService.create(
                    StartSessionDTO.builder()
                            .rulingId(0)
                            .duration(60)
                            .build()
            );
        });
    }

    @Test
    void shouldListAll() {
        sessionRepository.save(
                SessionEntity.builder()
                        .duration(60)
                        .creationDate(new Date())
                        .ruling(defaultRuling)
                        .build()
        );
        List<SessionReturnDTO> sessionReturnDTOS = sessionService.listAll();
        List<SessionEntity> sessionEntities = sessionRepository.findAll();

        assertNotNull(sessionReturnDTOS);
        assertEquals(sessionEntities.size(), sessionReturnDTOS.size());
    }

    @Test
    void shouldFindReturnById() {
        SessionReturnDTO sessionReturnDTO = sessionService.listById(defaultSession.getId());

        assertNotNull(sessionReturnDTO);
        assertEquals(defaultSession.getRuling().getId(), sessionReturnDTO.getRulingId());
        assertEquals(defaultSession.getId(), sessionReturnDTO.getId());
        assertEquals(defaultSession.getDuration(), sessionReturnDTO.getDuration());
        assertEquals(defaultSession.getRuling().getTitle(), sessionReturnDTO.getRulingTitle());
        assertEquals(DateUtils.formatDate(defaultSession.getCreationDate()), sessionReturnDTO.getCreationDate());
    }

    @Test
    void shouldGetByRulingId() {
        SessionEntity session = sessionService.getSessionByRulingId(defaultRuling.getId());

        assertNotNull(session);
        assertEquals(defaultSession.getRuling().getId(), session.getRuling().getId());
        assertEquals(defaultSession.getRuling().getTitle(), session.getRuling().getTitle());
        assertEquals(defaultSession.getRuling().getDescription(), session.getRuling().getDescription());
        assertEquals(defaultSession.getRuling().getResults(), session.getRuling().getResults());
        assertEquals(defaultSession.getRuling().getVoteCountDate(), session.getRuling().getVoteCountDate());
        assertEquals(defaultSession.getDuration(), session.getDuration());
        assertEquals(defaultSession.getId(), session.getId());
    }

    @Test
    void shouldThrowSessionDontExistByRulingId() {
        assertThrows(ValidationExceptions.class, () -> {
            sessionService.getSessionByRulingId(0);
        });
    }

    @Test
    void shouldGetSessionByIdIfExists() {
        SessionEntity session = sessionService.getSessionByIdIfExists(defaultSession.getId());

        assertNotNull(session);
        assertEquals(defaultSession.getRuling().getId(), session.getRuling().getId());
        assertEquals(defaultSession.getRuling().getTitle(), session.getRuling().getTitle());
        assertEquals(defaultSession.getRuling().getDescription(), session.getRuling().getDescription());
        assertEquals(defaultSession.getRuling().getResults(), session.getRuling().getResults());
        assertEquals(defaultSession.getRuling().getVoteCountDate(), session.getRuling().getVoteCountDate());
        assertEquals(defaultSession.getDuration(), session.getDuration());
        assertEquals(defaultSession.getId(), session.getId());
    }

    @Test
    void shouldThrowSessionExceptionByIdIfExists() {
        assertThrows(ValidationExceptions.class, () -> {
           sessionService.getSessionByIdIfExists(0);
        });
    }

}
