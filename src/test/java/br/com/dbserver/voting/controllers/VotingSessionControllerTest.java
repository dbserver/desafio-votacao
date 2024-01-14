package br.com.dbserver.voting.controllers;

import br.com.dbserver.voting.dtos.votingsession.VotingSessionRequestDTO;
import br.com.dbserver.voting.dtos.votingsession.VotingSessionResponseDTO;
import br.com.dbserver.voting.helpers.Constants;
import br.com.dbserver.voting.helpers.VotingSessionCreator;
import br.com.dbserver.voting.services.VotingSessionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class VotingSessionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private VotingSessionController votingSessionController;

    @Mock
    private VotingSessionService votingSessionServiceMock;

    @BeforeEach
    void setup() {
        Page<VotingSessionResponseDTO> votingSessionPage = new PageImpl<>(List.of(VotingSessionCreator.votingSessionResponseDTO()));
        when(votingSessionServiceMock.openVoting(any(VotingSessionRequestDTO.class))).thenReturn(VotingSessionCreator.votingSessionResponseDTO());
        when(votingSessionServiceMock.listAll(any())).thenReturn(votingSessionPage);
    }

    @Test
    public void shouldCreateVotingSessionSuccessfully() {
        VotingSessionRequestDTO requestDTO = VotingSessionCreator.votingSessionRequestDTO();
        ResponseEntity<VotingSessionResponseDTO> responseEntity = votingSessionController.openVoting(requestDTO);

        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody().getIdSessionVoting()).isEqualTo(VotingSessionCreator.votingSessionResponseDTO().getIdSessionVoting());
    }

    @Test
    public void shouldCreateVotingSessionValidationFailure() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post(Constants.API_VERSION + "/voting-session/open")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(VotingSessionCreator.votingSessionRequestDTOInvalid())))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void shouldListAllSessionOpenSuccessfully() {
        Pageable pageable = Pageable.unpaged();
        String expectedTitle = VotingSessionCreator.votingSessionResponseDTO().getSchedule().title();
        ResponseEntity<Page<VotingSessionResponseDTO>> responseEntity = votingSessionController.listAll(pageable);

        assertThat(responseEntity.getBody()).isNotEmpty().hasSize(1);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        Page<VotingSessionResponseDTO> votingSessionResponseDTOPage = responseEntity.getBody();
        assertThat(votingSessionResponseDTOPage).isNotNull();
        assertThat(votingSessionResponseDTOPage.getContent()).hasSize(1);
        assertThat(votingSessionResponseDTOPage.toList().get(0).getSchedule().title()).isEqualTo(expectedTitle);
    }


}