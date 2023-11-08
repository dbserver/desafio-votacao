package com.example.desafiovotacao.services;

import com.example.desafiovotacao.dto.CreatedAssociateDTO;
import com.example.desafiovotacao.dto.RegisterAssociateDTO;
import com.example.desafiovotacao.entity.AssociateEntity;
import com.example.desafiovotacao.exception.ValidationExceptions;
import com.example.desafiovotacao.exception.enums.implementations.AssociateErrorMessages;
import com.example.desafiovotacao.exception.enums.implementations.InformationErrorMessages;
import com.example.desafiovotacao.repository.AssociateRepository;
import com.example.desafiovotacao.service.implementations.AssociateServiceImpl;
import com.example.desafiovotacao.utils.CpfUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
public class AssociateServiceTest {

    @Autowired
    private AssociateServiceImpl associateService;
    @Autowired
    private AssociateRepository associateRepository;
    private RegisterAssociateDTO registerAssociateDTO;


    @BeforeEach
    void setup() {
        registerAssociateDTO = RegisterAssociateDTO.builder()
                .name("Name test")
                .cpf(CpfUtils.generateCPF())
                .build();
    }

    @Test
    void shouldCreateAssociate() {
        CreatedAssociateDTO createdAssociate = associateService.create(registerAssociateDTO);
        AssociateEntity searchAssociate = associateRepository.findById(createdAssociate.getId()).get();

        assertNotNull(createdAssociate);
        assertEquals(searchAssociate.getCpf(), createdAssociate.getCpf());
        assertEquals(searchAssociate.getId(), createdAssociate.getId());
    }

    @Test
    void shouldCreationThrowInvalidCPFException() {
        ValidationExceptions exception = assertThrows(ValidationExceptions.class, () -> {
            associateService.create(
                    RegisterAssociateDTO.builder()
                            .cpf("00000000000")
                            .name(registerAssociateDTO.getName())
                            .build()
            );
        });

        assertEquals(InformationErrorMessages.INVALID_CPF.getDescription(), exception.getMessage());
    }

    @Test
    void shouldCreationThrowFaultyInformationException() {
        ValidationExceptions exception = assertThrows(ValidationExceptions.class, () -> {
            associateService.create(
                    RegisterAssociateDTO.builder()
                            .cpf(null)
                            .name(null)
                            .build()
            );
        });

        assertEquals(InformationErrorMessages.FAULTY_INFORMATION.getDescription(), exception.getMessage());
    }

    @Test
    void shouldCreationThrowAlreadyRegisteredException() {
        assertThrows(ValidationExceptions.class, () -> {
            AssociateEntity associate = associateRepository.save(AssociateEntity.builder()
                            .name("Test user")
                            .cpf(CpfUtils.generateCPF())
                    .build());

            associateService.create(
                    RegisterAssociateDTO.builder()
                            .cpf(associate.getCpf())
                            .name(associate.getName())
                            .build()
            );
        });
    }

    @Test
    void shouldGetAssociateByCPF() {
        AssociateEntity newAssociate = associateRepository.save(
                AssociateEntity.builder()
                    .name("Test user")
                    .cpf(CpfUtils.generateCPF())
                    .build()
        );

        AssociateEntity testAssociate = associateService.getAssociateByCpfIfExists(newAssociate.getCpf());

        assertNotNull(testAssociate);
        assertEquals(newAssociate.getId(), testAssociate.getId());
        assertEquals(newAssociate.getCpf(), testAssociate.getCpf());
        assertEquals(newAssociate.getName(), testAssociate.getName());
    }

    @Test
    void shouldThrowAssociateNotFoundExceptionOnGetAssociateByCPF() {
        ValidationExceptions exception = assertThrows(ValidationExceptions.class, () -> {
            associateService.getAssociateByCpfIfExists(CpfUtils.generateCPF());
        });

        assertEquals(AssociateErrorMessages.ASSOCIATE_NOT_FOUNT.getDescription(), exception.getMessage());
    }

}
