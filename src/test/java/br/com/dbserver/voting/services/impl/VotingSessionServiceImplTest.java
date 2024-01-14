package br.com.dbserver.voting.services.impl;

import br.com.dbserver.voting.converters.vottingsession.VotingSessionToVotingSessionResponseDtoMapper;
import br.com.dbserver.voting.dtos.vote.ResultOfTheVoteDTO;
import br.com.dbserver.voting.dtos.votingsession.VotingSessionRequestDTO;
import br.com.dbserver.voting.dtos.votingsession.VotingSessionResponseDTO;
import br.com.dbserver.voting.enums.StatusVotingSessionEnum;
import br.com.dbserver.voting.exceptions.ExistingResourceException;
import br.com.dbserver.voting.exceptions.NotFoundException;
import br.com.dbserver.voting.exceptions.VotingException;
import br.com.dbserver.voting.helpers.ScheduleCreator;
import br.com.dbserver.voting.helpers.VotingSessionCreator;
import br.com.dbserver.voting.models.Schedule;
import br.com.dbserver.voting.models.VotingSession;
import br.com.dbserver.voting.repositories.ScheduleRepository;
import br.com.dbserver.voting.repositories.VotingSessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

@SpringBootTest
class VotingSessionServiceImplTest {

    @InjectMocks
    private VotingSessionServiceImpl votingSessionService;

    @Mock
    private VotingSessionRepository votingSessionRepositoryMock;

    @Mock
    private ScheduleRepository scheduleRepositoryMock;

    @Mock
    private VotingSessionToVotingSessionResponseDtoMapper votingSessionToVotingSessionResponseDtoMapper;


    @BeforeEach
    void setup() {
        when(scheduleRepositoryMock.findById(any())).thenReturn(Optional.of(ScheduleCreator.scheduleValid()));
        when(votingSessionRepositoryMock.save(any(VotingSession.class))).thenReturn(VotingSessionCreator.votingSession());
    }

    @Test
    void openVotingShouldReturnVotingSessionResponseDTO() {
        VotingSessionRequestDTO requestDTO = new VotingSessionRequestDTO("d6df5158-cd61-48f3-a8cb-0660c24d1a23", null);

        Schedule schedule = new Schedule();
        schedule.setId(UUID.randomUUID());

        VotingSessionResponseDTO expectedResponseDTO = VotingSessionCreator.votingSessionResponseDTO();
        doReturn(Optional.of(schedule)).when(scheduleRepositoryMock).findById(any());
        doReturn(false).when(votingSessionRepositoryMock).existsByScheduleIdAndStatus(any(), eq(StatusVotingSessionEnum.OPEN));
        doReturn(expectedResponseDTO).when(votingSessionToVotingSessionResponseDtoMapper).map(any(), any());

        VotingSessionResponseDTO result = votingSessionService.openVoting(requestDTO);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expectedResponseDTO);
    }

    @Test
    void openVotingShouldThrowExistingResourceExceptionWhenSessionExists() {
        VotingSessionRequestDTO requestDTO = new VotingSessionRequestDTO("d6df5158-cd61-48f3-a8cb-0660c24d1a23", null);

        Schedule schedule = new Schedule();
        schedule.setId(UUID.randomUUID());;

        doReturn(Optional.of(schedule)).when(scheduleRepositoryMock).findById(any());
        doReturn(true).when(votingSessionRepositoryMock).existsByScheduleIdAndStatus(any(), eq(StatusVotingSessionEnum.OPEN));

        assertThrows(ExistingResourceException.class, () -> votingSessionService.openVoting(requestDTO));
    }

    @Test
    void listAllShouldReturnPageOfVotingSessionResponseDTO() {
        Pageable pageable = Pageable.unpaged();
        VotingSessionResponseDTO expectedResponseDTO = VotingSessionCreator.votingSessionResponseDTO();

        Page<VotingSession> votingSessionPage = new PageImpl<>(List.of(new VotingSession()));
        doReturn(votingSessionPage).when(votingSessionRepositoryMock).findAll(pageable);
        doReturn(expectedResponseDTO).when(votingSessionToVotingSessionResponseDtoMapper).map(any(), any());

        Page<VotingSessionResponseDTO> result = votingSessionService.listAll(pageable);

        assertThat(result).isNotNull();
        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0)).isEqualTo(expectedResponseDTO);
    }

    @Test
    public void shouldCloseOpenVotingSessionWhenSuccessfully() {
        VotingSession votingSession = VotingSessionCreator.votingSession();

        // Mock do comportamento do repositório
        when(votingSessionRepositoryMock.findById(votingSession.getId())).thenReturn(Optional.of(VotingSessionCreator.votingSession()));
        when(votingSessionRepositoryMock.save(any(VotingSession.class))).thenReturn(VotingSessionCreator.votingSession());
        when(votingSessionRepositoryMock.voteProgress()).thenReturn(List.of(VotingSessionCreator.resultOfTheVoteDTOValid()));

        // Executa o método que queremos testar
        ResultOfTheVoteDTO result = votingSessionService.closeVoting(votingSession.getId().toString());

        // Verificações
        assertNotNull(result);
        assertEquals(StatusVotingSessionEnum.CLOSE, result.getStatus());
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenSessionNotFound() {
        // Criação de dados de exemplo
        String sessionId = "123e4567-e89b-12d3-a456-426614174001";

        // Mock do comportamento do repositório quando a sessão não é encontrada
        when(votingSessionRepositoryMock.findById(UUID.fromString(sessionId))).thenReturn(Optional.empty());

        // Executa o método que queremos testar e espera uma exceção
        assertThrows(NotFoundException.class, () -> votingSessionService.closeVoting(sessionId));

        // Verificações
        verify(votingSessionRepositoryMock, never()).save(any(VotingSession.class));
    }

    @Test
    public void shouldThrowVotingExceptionAlreadyClosed() {
        // Criação de dados de exemplo
        String sessionId = "123e4567-e89b-12d3-a456-426614174001";
        VotingSession votingSession = new VotingSession();
        votingSession.setId(UUID.fromString(sessionId));
        votingSession.setStatus(StatusVotingSessionEnum.CLOSE);

        // Mock do comportamento do repositório
        when(votingSessionRepositoryMock.findById(UUID.fromString(sessionId))).thenReturn(Optional.of(votingSession));

        // Executa o método que queremos testar e espera uma exceção
        assertThrows(VotingException.class, () -> votingSessionService.closeVoting(sessionId));

        // Verificações
        verify(votingSessionRepositoryMock, never()).save(any(VotingSession.class));
    }

}