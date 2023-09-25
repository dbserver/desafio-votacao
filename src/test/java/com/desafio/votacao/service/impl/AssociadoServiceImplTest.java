package com.desafio.votacao.service.impl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.desafio.votacao.dto.response.AssociadoDTO;
import com.desafio.votacao.entity.Associado;
import com.desafio.votacao.repository.AssociadoRepository;

public class AssociadoServiceImplTest {

    @InjectMocks
    private AssociadoServiceImpl associadoService;

    @Mock
    private AssociadoRepository associadoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSalvarAssociado_ValidData() {
        AssociadoDTO validAssociadoDTO = new AssociadoDTO();
        when(associadoRepository.save(any(Associado.class))).thenReturn(new Associado());
        ResponseEntity<AssociadoDTO> response = associadoService.salvar(validAssociadoDTO);
        assertEquals(200, response.getStatusCodeValue()); 
    } 

    @Test
    public void testBuscarPorCPF_ExistingAssociado() {
        String existingCpf = "12345678900";
        Associado existingAssociado = new Associado();
        existingAssociado.setCpf(existingCpf);
        when(associadoRepository.findFirstByCpf(existingCpf)).thenReturn(existingAssociado);
        Associado result = associadoService.buscarPorCPF(existingCpf);
        assertNotNull(result);
        assertEquals(existingCpf, result.getCpf());
    }

    @Test
    public void testBuscarPorCPF_NonExistingAssociado() {
        String nonExistingCpf = "98765432100";
        when(associadoRepository.findFirstByCpf(nonExistingCpf)).thenReturn(null);
        Associado result = associadoService.buscarPorCPF(nonExistingCpf);
        assertNull(result);
    }
}
