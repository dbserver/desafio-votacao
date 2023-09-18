package com.desafio.projeto_votacao.validators;

import com.desafio.projeto_votacao.repository.VotoRepository;
import com.desafio.projeto_votacao.utils.VotoValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("VotoValidator Tests")
class VotoValidatorTest {

    @Mock
    private VotoRepository votoRepository;

    @InjectMocks
    private VotoValidator votoValidator;

    @Test
    @DisplayName("Test associadoJaVotou() returns true")
    void testAssociadoJaVotou_True() {
        String cpf = "123.456.789-09";

        when(votoRepository.existsByAssociadoCpf(cpf)).thenReturn(true);

        assertTrue(votoValidator.associadoJaVotou(cpf));
    }

    @Test
    @DisplayName("Test associadoJaVotou() returns false")
    void testAssociadoJaVotou_False() {
        String cpf = "123.456.789-09";

        when(votoRepository.existsByAssociadoCpf(cpf)).thenReturn(false);

        assertFalse(votoValidator.associadoJaVotou(cpf));
    }
}