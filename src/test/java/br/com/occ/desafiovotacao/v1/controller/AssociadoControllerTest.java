package br.com.occ.desafiovotacao.v1.controller;

import br.com.occ.desafiovotacao.config.exception.ServiceException;
import br.com.occ.desafiovotacao.v1.dto.AssociadoDto;
import br.com.occ.desafiovotacao.v1.dto.AssociadoStatusDto;
import br.com.occ.desafiovotacao.v1.service.IAssociadoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static br.com.occ.desafiovotacao.utils.EntityUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class AssociadoControllerTest {

    private static final String ASSOCIADO_NAO_ENCONTRADO = "Associado não localizado";
    private static final String ASSOCIADOS_ATIVOS_NAO_ENCONTRADOS = "Não foi encontrado associados ativos";

    @InjectMocks
    private AssociadoController controller;

    @Mock
    private IAssociadoService service;
    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenSaveThenReturnSuccess() {
        when(service.save(any())).thenReturn(criarAssociado(ATIVO));
        when(modelMapper.map(any(), any())).thenReturn(criarAssociadoDto(ATIVO));

        ResponseEntity<AssociadoDto> response = controller.save(criarAssociadoDto(ATIVO));

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(criarAssociadoDto(ATIVO), response.getBody());
    }

    @Test
    void whenGetByIdThenResultSuccess() {
        when(service.findById(anyLong())).thenReturn(criarAssociado(ATIVO));
        when(modelMapper.map(any(),any())).thenReturn(criarAssociadoDto(ATIVO));

        ResponseEntity<AssociadoDto> response = controller.getById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(criarAssociadoDto(ATIVO), response.getBody());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException() {
        when(service.findById(anyLong())).thenThrow(new ServiceException(ASSOCIADO_NAO_ENCONTRADO, HttpStatus.BAD_REQUEST));

        assertThrows(ServiceException.class, () -> {
            ResponseEntity<AssociadoDto> response = controller.getById(ID);
            assertNotNull(response);
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        });
    }

    @Test
    void whenFindAllThenReturnAnListOfAssociadosAptos() {
        when(service.findAllAtivos()).thenReturn(List.of(criarAssociado(ATIVO)));
        when(modelMapper.map(any(),any())).thenReturn(criarAssociadoDto(ATIVO));

        ResponseEntity<List<AssociadoDto>> response = controller.getAssociadosAptos();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(1, response.getBody().size());
        assertEquals(criarAssociadoDto(ATIVO), response.getBody().get(0));
    }

    @Test
    void whenFindAllThenReturnAnListOfAssociadosAtivosException() {
        when(service.findAllAtivos()).thenThrow(new ServiceException(ASSOCIADOS_ATIVOS_NAO_ENCONTRADOS, HttpStatus.BAD_REQUEST));
        assertThrows(ServiceException.class,() -> {
            ResponseEntity<List<AssociadoDto>> response = controller.getAssociadosAptos();
            assertNotNull(response);
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        });
    }

    @Test
    void whenGetStatusCpfAnAssociadoStatusDto() {
        when(service.getStatusCpf(any())).thenReturn(criarAssociadoStatusDto());
        ResponseEntity<AssociadoStatusDto> response = controller.validarCpfApto(CPF_ASSOCIADO);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
    }
}
