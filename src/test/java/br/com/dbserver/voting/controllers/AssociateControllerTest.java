package br.com.dbserver.voting.controllers;

import br.com.dbserver.voting.dtos.AssociateDTO;
import br.com.dbserver.voting.helpers.AssociateCreator;
import br.com.dbserver.voting.helpers.Constants;
import br.com.dbserver.voting.services.AssociateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class AssociateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private AssociateController associateController;

    @Mock
    private AssociateService associateServiceMock;

    @BeforeEach
    void setup() {
        PageImpl<AssociateDTO> associatePage = new PageImpl<>(List.of(AssociateCreator.associateDTOValid()));
        when(associateServiceMock.listAll(any())).thenReturn(associatePage);
        doNothing().when(associateServiceMock).createAssociate(AssociateCreator.associateDTOValid());
    }

    @Test
    public void shouldCreateAssociateSuccessfully(){
        AssociateDTO associateDTO = AssociateCreator.createAssociateDtoValid();
        assertThatCode(() -> associateController.createAssociate(associateDTO))
                .doesNotThrowAnyException();
        ResponseEntity<Void> entity = associateController.createAssociate(associateDTO);
        assertThat(entity).isNotNull();
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void shouldCreateAssociateValidationFailure() throws Exception {
        AssociateDTO associateDTO = AssociateCreator.createAssociateDtoInvalid();

        doNothing().when(associateServiceMock).createAssociate(any(AssociateDTO.class));

        mockMvc.perform(MockMvcRequestBuilders.post(Constants.API_VERSION + "/associate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(associateDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void shouldListAllAssociatesSuccessfully(){
        Pageable pageable = Pageable.unpaged();
        String expectedCpf = AssociateCreator.associateDTOValid().cpf();
        Page<AssociateDTO> associatePage = associateController.listAll(pageable).getBody();

        assertThat(associatePage).isNotEmpty().hasSize(1);
        assertThat(associatePage.toList()).isNotEmpty();
        assertThat(associatePage.toList().get(0).cpf()).isEqualTo(expectedCpf);
    }
}