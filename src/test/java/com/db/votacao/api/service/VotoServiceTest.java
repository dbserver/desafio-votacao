package com.db.votacao.api.service;

import com.db.votacao.api.enums.EnumOpcoesVoto;
import com.db.votacao.api.model.Voto;
import com.db.votacao.api.repository.VotoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class VotoServiceTest {

    @Mock
    private VotoRepository votoRepository;

    @InjectMocks
    private VotoService votoService;

    @Test
    public void deveCalcularTotalizadorVotos() {
        // Arrange
        UUID pautaId = UUID.randomUUID();
        long totalSim = 5;
        long totalNao = 3;

        when(votoRepository.countByPauta_IdPautaAndVoto(pautaId, EnumOpcoesVoto.SIM)).thenReturn(totalSim);
        when(votoRepository.countByPauta_IdPautaAndVoto(pautaId, EnumOpcoesVoto.NAO)).thenReturn(totalNao);

        // Act
        Map<EnumOpcoesVoto, Long> totalizador = votoService.calcularTotalizadorVotos(pautaId);

        // Assert
        assertEquals(totalSim, totalizador.get(EnumOpcoesVoto.SIM));
        assertEquals(totalNao, totalizador.get(EnumOpcoesVoto.NAO));
    }

    @Test
    public void deveCriarVoto() {
        // Arrange
        Voto voto = new Voto();
        when(votoRepository.save(voto)).thenReturn(voto);

        // Act
        Voto resultado = votoService.criarVoto(voto);

        // Assert
        assertEquals(voto, resultado);
        verify(votoRepository, times(1)).save(voto);
    }
}
