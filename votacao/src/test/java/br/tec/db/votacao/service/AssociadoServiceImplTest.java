package br.tec.db.votacao.service;

import br.tec.db.votacao.dto.AssociadoDTO;
import br.tec.db.votacao.enums.AssociadoStatusEnum;
import br.tec.db.votacao.model.Associado;
import br.tec.db.votacao.repository.AssociadoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AssociadoServiceImplTest {

    @Mock
    private AssociadoRepository associadoRepository;

    @InjectMocks
    private AssociadoServiceImpl associadoService;

    @Test
    void deveSalvarUmAssociado() {
        Associado associado = new Associado();
        associado.setCpf("123456789");
        associado.setNome("Joao da Silva");
        associado.setStatus(AssociadoStatusEnum.PODE_VOTAR);
        when(associadoRepository.save(associado)).thenReturn(associado);
        AssociadoDTO associadoDTO = associadoService.salvarAssociado(new AssociadoDTO(associado));
        assertNotNull(associadoDTO);
        assertEquals(associado.getCpf(), "123456789");
    }

    @Test
    void deveLancarExcecaoAoCriarAssociadoSeNaoSalvar() {
        Associado associado = new Associado();
        associado.setCpf("123456789");
        associado.setNome("Joao da Silva");
        associado.setStatus(AssociadoStatusEnum.PODE_VOTAR);
        when(associadoRepository.save(associado)).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, () -> associadoService.salvarAssociado(new AssociadoDTO(associado)));
    }

    @Test
    void deveBuscarTodosOsAssociados() {
        List<Associado> associados = new ArrayList<>();
        Associado associado = new Associado();
        Associado associado2 = new Associado();
        associados.add(associado);
        associados.add(associado2);
        when(associadoRepository.findAll()).thenReturn(associados);
        List<AssociadoDTO> associadoDTOS = associadoService.buscarTodosOsAssociados();
        assertNotNull(associadoDTOS);
        assertEquals(associadoDTOS.size(), 2);
    }

    @Test
    void deveBuscarAssociadoPorId() {
        Associado associado = new Associado();
        associado.setCpf("123456789");
        associado.setNome("Joao da Silva");
        associado.setStatus(AssociadoStatusEnum.PODE_VOTAR);
        when(associadoRepository.findById(1L)).thenReturn(java.util.Optional.of(associado));
        AssociadoDTO associadoDTO = associadoService.buscarAssociadoPorId(1L);
        assertNotNull(associadoDTO);
        assertEquals(associado.getCpf(), "123456789");
    }

    @Test
    void deveLancarExcecaoAoBuscarAssociadoPorIdSeNaoEncontrar() {
        when(associadoRepository.findById(10L)).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, () -> associadoService.buscarAssociadoPorId(10L));
    }
}

