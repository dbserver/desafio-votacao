package br.com.dbserver.voting.controllers;

import br.com.dbserver.voting.dtos.CpfValidationResponseDTO;
import br.com.dbserver.voting.dtos.vote.ResultOfTheVoteDTO;
import br.com.dbserver.voting.dtos.vote.VoteRequestDTO;
import br.com.dbserver.voting.dtos.vote.VoteResponseDTO;
import br.com.dbserver.voting.enums.StatusCpfEnum;
import br.com.dbserver.voting.exceptions.NotFoundException;
import br.com.dbserver.voting.helpers.*;
import br.com.dbserver.voting.services.CpfValidationService;
import br.com.dbserver.voting.services.VoteService;
import br.com.dbserver.voting.services.VotingCacheService;
import br.com.dbserver.voting.services.impl.CpfValidationServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class VoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private VoteController voteController;

    @Mock
    private VoteService voteServiceMock;

    @Mock
    private VotingCacheService votingCacheService;

    @Mock
    private CpfValidationService cpfValidationService;

    @BeforeEach
    void setup(){
        when(voteServiceMock.voting(any(VoteRequestDTO.class))).thenReturn(VoteCreator.voteResponseDTO());
        when(voteServiceMock.listVoteInProgress()).thenReturn(List.of(VoteCreator.resultOfTheVoteDTOValid()));
        when(votingCacheService.getCachedVotingSession(any())).thenReturn(Optional.of(VotingSessionCreator.votingSession()));
        when(votingCacheService.getCachedAssociate(anyString())).thenReturn(Optional.of(AssociateCreator.associateValid()));
        when(cpfValidationService.validateCpf(anyString())).thenReturn(StatusCpfEnum.ABLE_TO_VOTE.name());
    }

    @Test
    public void shouldVoteSuccessfullyWhenVoteRequestIsCorrect(){
        VoteRequestDTO requestDTO = VoteCreator.voteRequestDTOValid();
        assertThatCode(() -> voteController.createVote(requestDTO))
                .doesNotThrowAnyException();

        ResponseEntity<VoteResponseDTO> entity = voteController.createVote(requestDTO);
        assertThat(entity).isNotNull();
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(VoteCreator.voteResponseDTO().getVote()).isEqualTo(entity.getBody().getVote());
    }

    @Test
    public void shouldListVoteInProgressWhenVotingSessionIsOpen(){
        String expectedTitleSchedule = VoteCreator.resultOfTheVoteDTOValid().getSchedule().getTitle();
        List<ResultOfTheVoteDTO> results = voteController.listVoteInProgress().getBody();

        assertThat(results).isNotEmpty().hasSize(1);
        assertThat(results).isNotEmpty();
        assertThat(results.get(0).getSchedule().getTitle()).isEqualTo(expectedTitleSchedule);
    }

}