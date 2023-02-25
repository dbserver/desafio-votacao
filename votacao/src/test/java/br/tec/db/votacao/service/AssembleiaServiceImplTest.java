package br.tec.db.votacao.service;

import br.tec.db.votacao.dto.AssembleiaDTO;
import br.tec.db.votacao.enums.AssembleiaStatusEnum;
import br.tec.db.votacao.model.Assembleia;
import br.tec.db.votacao.repository.AssembleiaRepository;
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
class AssembleiaServiceImplTest {

    @Mock
    private AssembleiaRepository assembleiaRepository;

    @InjectMocks
    private AssembleiaServiceImpl assembleiaService;

    @Test
    void deveCriarAssembleia() {
        Assembleia assembleia = new Assembleia();
        assembleia.setStatus(AssembleiaStatusEnum.INICIADA);
        when(assembleiaRepository.save(assembleia)).thenReturn(assembleia);
        AssembleiaDTO assembleiaDTO = assembleiaService.criarAssembleia(new AssembleiaDTO(assembleia));
        assertNotNull(assembleiaDTO);
        assertEquals(assembleia.getStatus(), AssembleiaStatusEnum.INICIADA);
    }

    @Test
    void deveLancarExcecaoAoCriarAssembleiaSeNaoSalvar() {
        Assembleia assembleia = new Assembleia();
        assembleia.setStatus(AssembleiaStatusEnum.INICIADA);
        when(assembleiaRepository.save(assembleia)).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, () -> assembleiaService.criarAssembleia(new AssembleiaDTO(assembleia)));
    }

    @Test
    void deveBuscarTodasAssembleias() {
        List<Assembleia> assembleias = new ArrayList<>();
        Assembleia assembleia = new Assembleia();
        Assembleia assembleia2 = new Assembleia();
        assembleias.add(assembleia);
        assembleias.add(assembleia2);
        when(assembleiaRepository.findAll()).thenReturn(assembleias);
        List<AssembleiaDTO> assembleiaDTOS = assembleiaService.buscarTodasAssembleias();
        assertNotNull(assembleiaDTOS);
        assertEquals(assembleiaDTOS.size(), 2);
    }

    @Test
    void deveBuscarAssembleiaPorId() {
        Assembleia assembleia = new Assembleia();
        assembleia.setStatus(AssembleiaStatusEnum.INICIADA);
        when(assembleiaRepository.findById(1L)).thenReturn(java.util.Optional.of(assembleia));
        AssembleiaDTO assembleiaDTO = assembleiaService.buscarAssembleiaPorId(1L);
        assertNotNull(assembleiaDTO);
        assertEquals(assembleia.getStatus(), AssembleiaStatusEnum.INICIADA);
    }

    @Test
    void deveLancarExcecaoAoBuscarAssembleiaPorIdInexistente() {
        when(assembleiaRepository.findById(1L)).thenReturn(java.util.Optional.empty());
        assertThrows(RuntimeException.class, () -> assembleiaService.buscarAssembleiaPorId(1L));
    }

    @Test
    void deveFinalizarAssembleia() {
        Assembleia assembleia = new Assembleia();
        assembleia.setStatus(AssembleiaStatusEnum.INICIADA);
        when(assembleiaRepository.findById(1L)).thenReturn(java.util.Optional.of(assembleia));
        assembleiaService.finalizarAssembleia(1L);
        assertEquals(assembleia.getStatus(), AssembleiaStatusEnum.ENCERRADA);
    }

    @Test
    void deveLancarExcecaoAoFinalizarAssembleiaJaEncerrada() {
        Assembleia assembleia = new Assembleia();
        assembleia.setStatus(AssembleiaStatusEnum.ENCERRADA);
        when(assembleiaRepository.findById(1L)).thenReturn(java.util.Optional.of(assembleia));
        assertThrows(RuntimeException.class, () -> assembleiaService.finalizarAssembleia(1L));
    }

    @Test
    void deveLancarExcecaoAoFinalizarAssembleiaInexistente() {
        when(assembleiaRepository.findById(1L)).thenReturn(java.util.Optional.empty());
        assertThrows(RuntimeException.class, () -> assembleiaService.finalizarAssembleia(1L));
    }

}