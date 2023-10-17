package com.challenge.service;

import com.challenge.dto.StaveRequestDto;
import com.challenge.dto.StaveSessionRequestDto;
import com.challenge.model.Stave;
import com.challenge.model.StaveSession;
import com.challenge.repository.AssociateRepository;
import com.challenge.repository.StaveRepository;
import com.challenge.repository.StaveSessionRepository;
import com.challenge.repository.VoteRepository;
import com.challenge.service.impl.StaveServiceImpl;
import com.challenge.stubs.StaveSessionStub;
import com.challenge.stubs.StaveStub;
import com.challenge.stubs.VoteStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.List;
import java.util.Optional;

import static com.challenge.enums.SessionStatusEnum.OPEN;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class StaveServiceTest {
    @InjectMocks
    private StaveServiceImpl service;
    @Mock
    private VoteRepository voteRepository;
    @Mock
    private StaveSessionRepository staveSessionRepository;
    @Mock
    private AssociateRepository associateRepository;
    @Mock
    private StaveRepository staveRepository;

    @Test
    void createStave_whenSuccessful() throws Exception {
        Stave stave = StaveStub.build(null);
        service.create(StaveRequestDto.builder().title("Stave Test").build());
        verify(staveRepository, times(1)).save(stave);
    }

    @Test
    void createSession_whenSuccessful() throws Exception {
        StaveSession session = StaveSessionStub.build();
        when(staveRepository.getReferenceById(any())).thenReturn(session.getStave());
        when(staveSessionRepository.findByStaveId(any())).thenReturn(Optional.of(session));

        service.session(session.getStave().getId(), StaveSessionRequestDto.builder().build());
        verify(staveSessionRepository, times(1)).findByStaveId(session.getStave().getId());
        verify(staveRepository, times(1)).getReferenceById(session.getStave().getId());
        verify(staveSessionRepository, times(1)).save(StaveSession.builder()
                .duration(1)
                .status(OPEN)
                .stave(session.getStave())
                .build());
    }

    @Test
    void countVotes_whenSuccessful() throws Exception {
        Long staveId = 1L;
        StaveSession session = StaveSessionStub.buildClose();
        when(staveRepository.getReferenceById(any())).thenReturn(session.getStave());
        when(staveSessionRepository.findByStaveId(any())).thenReturn(Optional.of(session));
        when(voteRepository.findByStaveSession(session)).thenReturn(List.of(VoteStub.build(session)));
        service.countVotes(staveId);
        verify(staveRepository, times(1)).getReferenceById(staveId);
        verify(staveSessionRepository, times(1)).findByStaveId(staveId);
        verify(voteRepository, times(1)).findByStaveSession(session);
    }
}
