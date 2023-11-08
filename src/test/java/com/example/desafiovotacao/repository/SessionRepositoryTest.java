package com.example.desafiovotacao.repository;

import com.example.desafiovotacao.dto.SessionReturnDTO;
import com.example.desafiovotacao.entity.RulingEntity;
import com.example.desafiovotacao.entity.SessionEntity;
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
public class SessionRepositoryTest {

    @Autowired
    private RulingRepository rulingRepository;
    @Autowired
    private SessionRepository sessionRepository;

    @Mock
    private RulingEntity createdRuling;
    private SessionEntity createdSession;

    @BeforeEach
    void setup() {
        createdRuling = rulingRepository.save(
                RulingEntity.builder()
                        .creationDate(new Date())
                        .description("Mock ruling description")
                        .title("Mock ruling")
                        .build()
        );
        createdSession = sessionRepository.save(
                SessionEntity.builder()
                        .creationDate(new Date())
                        .ruling(createdRuling)
                        .duration(60)
                        .build()
        );
    }

    @Test
    void shouldReturnRecentSessionByRuling(){
        Optional<SessionEntity> foundSession = sessionRepository.findLatestByRulingId(createdRuling.getId());
        Assertions.assertFalse(foundSession.isEmpty());
        Assertions.assertEquals(
                createdSession,
                foundSession.get()
        );
    }

    @Test
    void shouldReturnSessionDTOList() {
        List<SessionReturnDTO> returnDTOList = sessionRepository.listAllReturns();
        Assertions.assertNotNull(returnDTOList);
        Assertions.assertEquals(1, returnDTOList.size());
    }

    @Test
    void shouldReturnSessionDTO() {
        Optional<SessionReturnDTO> sessionReturnDTO = sessionRepository.findReturnById(createdSession.getId());
        Assertions.assertFalse(sessionReturnDTO.isEmpty());
        Assertions.assertEquals(
                SessionReturnDTO.builder()
                        .id(createdSession.getId())
                        .creationDate(DateUtils.formatDate(createdSession.getCreationDate()))
                        .rulingTitle(createdSession.getRuling().getTitle())
                        .duration(createdSession.getDuration())
                        .rulingId(createdRuling.getId())
                        .build(),
                sessionReturnDTO.get()
        );
    }
}
