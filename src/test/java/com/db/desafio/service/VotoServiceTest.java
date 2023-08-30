package com.db.desafio.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.db.desafio.util.factory.AssociadoFactory.associadoFactory;
import static com.db.desafio.util.factory.PautaFactory.pautaFactory;
import static com.db.desafio.util.factory.SessaoFactory.sessaoFactory;
import static com.db.desafio.util.factory.VotoFactory.votoDtoFactory;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VotoServiceTest {
    @InjectMocks
    private VotoService votoService;
    @Mock
    private PautaService pautaService;
    @Mock
    private VotoRepository votoRepository;

   @Mock
    private SessaoVotacaoService sessaoVotacaoService;
   @Mock
   private AssociadoService associadoService;
    public static final Long ID = 1L;



    @Test
    @DisplayName("Deve Salvar um novo voto")
    void deveSalvarVoto()  {
        when(pautaService.obterPautaPorId(ID)).thenReturn(pautaFactory());
        when(sessaoVotacaoService.retonarSesaoAberta(ID)).thenReturn(sessaoFactory());
        when(associadoService.obterAssociadoPorCpf(any())).thenReturn(associadoFactory());
        when(votoRepository.existsByPautaIdAndAssociadoId(ID,ID)).thenReturn(false);

       votoService.salvarVoto(ID,votoDtoFactory());

        verify(sessaoVotacaoService, times(1)).retonarSesaoAberta(ID);
        verify(pautaService, times(1)).obterPautaPorId(ID);
        verify(votoRepository, times(1)).existsByPautaIdAndAssociadoId(ID,ID);
        verify(associadoService, times(1)).obterAssociadoPorCpf(any());
    }
    @Test
    @DisplayName("Deve retor uma exceção quando associado ja votou")
    void NaoDeveSalvarQaundoassociadoVotou()  {
        when(pautaService.obterPautaPorId(ID)).thenReturn(pautaFactory());
        when(sessaoVotacaoService.retonarSesaoAberta(ID)).thenReturn(sessaoFactory());
        when(associadoService.obterAssociadoPorCpf(any())).thenReturn(associadoFactory());
        when(votoRepository.existsByPautaIdAndAssociadoId(ID,ID)).thenReturn(true);

        assertThrows(VotoException.class, () ->
                votoService.salvarVoto(ID,votoDtoFactory()));

    }

}
