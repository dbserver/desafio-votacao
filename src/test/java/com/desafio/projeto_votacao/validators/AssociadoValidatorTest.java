package com.desafio.projeto_votacao.validators;

import com.desafio.projeto_votacao.repository.AssociadoRepository;
import com.desafio.projeto_votacao.utils.AssociadoValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AssociadoValidatorTest {

    @Mock
    private AssociadoRepository associadoRepository;

    @InjectMocks
    private AssociadoValidator associadoValidator;

    @Test
    @DisplayName("Teste de remoção de máscara de CPF")
    void testRemoverMascaraCPF() {
        String cpfComMascara = "123.456.789-09";
        String cpfSemMascara = "12345678909";

        assertEquals(cpfSemMascara, associadoValidator.removerMascaraCPF(cpfComMascara));
    }

    @Test
    @DisplayName("Teste de existência de associado com CPF")
    void testExisteAssociadoComCPF() {
        when(associadoRepository.existsByCpf("12345678909")).thenReturn(true);
        when(associadoRepository.existsByCpf("11111111111")).thenReturn(false);

        assertTrue(associadoValidator.existeAssociadoComCPF("12345678909"));
        assertFalse(associadoValidator.existeAssociadoComCPF("11111111111"));
    }
}