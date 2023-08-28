package com.db.desafio.service;


import com.db.desafio.entity.Associado;
import com.db.desafio.exception.AssociadoException;
import com.db.desafio.repository.AssociadoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;
import java.util.Optional;


import static com.db.desafio.util.AssociadoFactory.ListaDeAssociadosFactory;
import static com.db.desafio.util.AssociadoFactory.associadoFactory;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AssociadoServiceTest {
    @InjectMocks
    private AssociadoService associadoService;
    @Mock
    private AssociadoRepository associadoRepository;
    private static final Long ASSOCIADO_ID = 1L;

    @Test
    @DisplayName("Deve retornar uma lista com todos os associados")
    void deveRetornarListaDeAssociados() {
        List<Associado> resultadoEsperado = ListaDeAssociadosFactory();
        when(associadoRepository.findAll()).thenReturn(ListaDeAssociadosFactory());

        List<Associado> resultadoAtual = associadoService.obterAssociados();

        assertThat(resultadoAtual).usingRecursiveComparison().isEqualTo(resultadoEsperado);
        verify(associadoRepository).findAll();
    }

    @Test
    @DisplayName("Deve obter um  associado passando um id")
    void deveobterAssociadoPorId() {
        Associado resultadoEsperado = associadoFactory();
        when(associadoRepository.findById(ASSOCIADO_ID)).thenReturn(Optional.of(associadoFactory()));

        Associado resultadoAtual = associadoService.obterAssociadoPorId(ASSOCIADO_ID);

        assertThat(resultadoAtual).isEqualTo(resultadoEsperado);
        verify(associadoRepository).findById(ASSOCIADO_ID);
    }

    @Test
    @DisplayName("Deve retornar uma exception quando associado não existir")
    void deveRetornarExceptionQuandoAssociadoInexistente() {
        when(associadoRepository.findById(ASSOCIADO_ID)).thenReturn(Optional.empty());

        assertThrows(AssociadoException.class, () ->
                associadoService.obterAssociadoPorId(ASSOCIADO_ID));

    }

    @Test
    @DisplayName("Deve criar um  associado")
    void deveCriarAssociado() {
        when(associadoRepository.findByCpf(any())).thenReturn(Optional.empty());
        when(associadoRepository.save(associadoFactory())).thenReturn(associadoFactory());

        associadoService.criarAssociado(associadoFactory());

        verify(associadoRepository, times(1)).save(associadoFactory());
        verify(associadoRepository, times(1)).findByCpf(any());
    }

    @Test
    @DisplayName("Deve retornar uma exception quando assocido já existir")
    void deveRetornarExceptionQuandoAssociadoExistente() {
        when(associadoRepository.findByCpf(associadoFactory().getCpf())).thenReturn(Optional.of(associadoFactory()));

        assertThrows(AssociadoException.class, () ->
                associadoService.criarAssociado(associadoFactory()));

    }

    @Test
    @DisplayName("Deve deletar um associado existente")
    void deveDeletarAssociadoexistente() {
        when(associadoRepository.findById(ASSOCIADO_ID)).thenReturn(Optional.of(associadoFactory()));
        doNothing().when(associadoRepository).deleteById(ASSOCIADO_ID);

        associadoService.deletarAssociado(ASSOCIADO_ID);

        verify(associadoRepository).deleteById(ASSOCIADO_ID);
    }
    @Test
    @DisplayName("Deve retornar uma exception quando tentar deletar associado não existente")
    void deveRetornarExceptionQuandoDeletarAssociadoInexistente() {
        when(associadoRepository.findById(ASSOCIADO_ID)).thenReturn(Optional.empty());

        assertThrows(AssociadoException.class, () ->
                associadoService.deletarAssociado(ASSOCIADO_ID));
    }

}
