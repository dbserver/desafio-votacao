package com.example.desafiovotacao.repository;

import com.example.desafiovotacao.entity.AssociateEntity;
import com.example.desafiovotacao.utils.CpfUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class AssociateRepositoryTest {

    @Autowired
    private AssociateRepository associateRepository;

    @Mock
    private AssociateEntity createdAssociate;

    @BeforeEach
    void setup() {
        createdAssociate = associateRepository.save(AssociateEntity.builder()
                        .cpf(CpfUtils.generateCPF())
                        .name("User teste")
                        .build()
            );
    }

    @Test
    void shouldFindByCPF(){
        Optional<AssociateEntity> foundAssociate = associateRepository.findByCpf(createdAssociate.getCpf());
        Assertions.assertNotNull(foundAssociate.get());
    }

}
