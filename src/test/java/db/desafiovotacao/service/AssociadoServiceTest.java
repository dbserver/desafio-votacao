package db.desafiovotacao.service;

import db.desafiovotacao.exceptions.ConflictException;
import db.desafiovotacao.exceptions.NotFoundException;
import db.desafiovotacao.model.Associado;
import db.desafiovotacao.repository.AssociadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AssociadoServiceTest {

    AssociadoService associadoService;

    @Mock
    AssociadoRepository associadoRepository;

    @Mock
    Associado associado;


    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        associadoService = new AssociadoService(associadoRepository);

        associado = Associado.builder()
                .id(1L)
                .CPF("000.000.000-00")
                .build();
    }

    @Test
    void deveCriarAssociadoSucesso() {

        Mockito.when(associadoRepository.findByCPF(associado.getCPF())).thenReturn(Optional.empty());
        Mockito.when(associadoRepository.save(associado)).thenReturn(associado);

        Associado cadastrado = associadoService.criarAssociado(associado);

        assertAll("cadastro de associado",
                () -> assertNotNull(cadastrado),
                () -> assertEquals(associado.getId(), cadastrado.getId()),
                () -> assertEquals(associado.getCPF(), cadastrado.getCPF())
        );
    }

    @Test
    void deveCriarAssociadoException() {

        Mockito.when(associadoRepository.findByCPF(associado.getCPF())).thenReturn(Optional.of(associado));

        assertThrows(ConflictException.class,
                () -> associadoService.criarAssociado(associado)
        );
    }

    @Test
    void deveBuscarAssociadoPorCPFSucesso() {

        Mockito.when(associadoRepository.findByCPF(associado.getCPF())).thenReturn(Optional.of(associado));

        Associado encontrado = associadoService.buscarPorCPF(associado.getCPF());

        assertAll("busca de associado",
                () -> assertNotNull(encontrado),
                () -> assertEquals(associado.getId(), encontrado.getId()),
                () -> assertEquals(associado.getCPF(), encontrado.getCPF())
        );
    }

    @Test
    void deveBuscarAssociadoPorCPFException() {

        Mockito.when(associadoRepository.findByCPF(associado.getCPF())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                () -> associadoService.buscarPorCPF(associado.getCPF())
        );
    }

    @Test
    void deveRetornarVerdadeiroSeAssociadoEstiverCadastrado(){

        Mockito.when(associadoRepository.findByCPF(associado.getCPF())).thenReturn(Optional.of(associado));

        assertTrue(associadoService.associadoEstaCadastrado(associado));
    }

    @Test
    void deveRetornarfalsoSeAssociadoNaoEstiverCadastrado(){

        Mockito.when(associadoRepository.findByCPF(associado.getCPF())).thenReturn(Optional.empty());

        assertFalse(associadoService.associadoEstaCadastrado(associado));
    }
}