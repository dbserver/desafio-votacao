package br.com.dbserver.voting.services.impl;

import br.com.dbserver.voting.dtos.AssociateDTO;
import br.com.dbserver.voting.helpers.AssociateCreator;
import br.com.dbserver.voting.models.Associate;
import br.com.dbserver.voting.repositories.AssociateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class AssociateServiceImplTest {

    @Autowired
    private AssociateServiceImpl associateService;

    @MockBean
    private AssociateRepository associateRepositoryMock;

    @BeforeEach
    void setup() {
        when(associateRepositoryMock.save(any(Associate.class))).thenReturn(AssociateCreator.associateValid());
        when(associateRepositoryMock.findById(any())).thenReturn(Optional.of(AssociateCreator.associateValid()));
        PageImpl<Associate> associatePage = new PageImpl<>(List.of(AssociateCreator.associateValid()));
        when(associateRepositoryMock.findAll(any(Pageable.class))).thenReturn(associatePage);
    }

    @Test
    public void createSchedule_WhenSuccessful() {
        assertThatCode(() -> associateService.createAssociate(AssociateCreator.createAssociateDtoValid())).doesNotThrowAnyException();
    }

    @Test
    public void shouldListAllAssociatesSuccessfully(){
        Pageable pageable = Pageable.unpaged();
        String expectedCpf = AssociateCreator.createAssociateDtoValid().cpf();
        Page<AssociateDTO> associatePage = associateService.listAll(pageable);

        assertThat(associatePage).isNotEmpty().hasSize(1);
        assertThat(associatePage.toList()).isNotEmpty();
        assertThat(associatePage.toList().get(0).cpf()).isEqualTo(expectedCpf);
    }

}