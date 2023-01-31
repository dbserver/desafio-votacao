package br.com.occ.desafiovotacao.v1.controller;

import br.com.occ.desafiovotacao.config.exception.ServiceException;
import br.com.occ.desafiovotacao.v1.dto.VotoDto;
import br.com.occ.desafiovotacao.v1.service.IVotoService;
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
class VotoControllerTest {

    private static final String VOTO_NAO_ENCONTRADO = "Voto não encontrada";
    private static final String SEM_VOTO = "Associado não possui voto em nenhuma pauta";
    private static final String JA_VOTOU = "Associado já votou na pauta";

    @InjectMocks
    private VotoController controller;

    @Mock
    private IVotoService service;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenGetByIdThenResultSuccess() {
        when(service.findById(anyLong())).thenReturn(criarVoto());
        when(modelMapper.map(any(),any())).thenReturn(criarVotoDto());

        ResponseEntity<VotoDto> response = controller.getById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(criarVotoDto(), response.getBody());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException() {
        when(service.findById(anyLong())).thenThrow(new ServiceException(VOTO_NAO_ENCONTRADO, HttpStatus.BAD_REQUEST));

        assertThrows(ServiceException.class, () -> {
            ResponseEntity<VotoDto> response = controller.getById(ID);
            assertNotNull(response);
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        });
    }

    @Test
    void whenFindAllThenReturnAnListByAssociado() {
        when(service.findAllByAssociado(anyLong())).thenReturn(List.of(criarVoto()));
        when(modelMapper.map(any(),any())).thenReturn(criarVotoDto());

        ResponseEntity<List<VotoDto>> response = controller.getVotosByAssociado(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(1, response.getBody().size());
        assertEquals(criarVotoDto(), response.getBody().get(0));
    }

    @Test
    void whenFindAllThenReturnAnListOfVotoException() {
        when(service.findAllByAssociado(anyLong())).thenThrow(new ServiceException(SEM_VOTO, HttpStatus.BAD_REQUEST));
        assertThrows(ServiceException.class,() -> {
            ResponseEntity<List<VotoDto>> response = controller.getVotosByAssociado(ID);
            assertNotNull(response);
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        });
    }

    @Test
    void whenVotarThenReturnAnVotoInstance() {
        when(service.votar(any())).thenReturn(criarVoto());
        when(modelMapper.map(any(),any())).thenReturn(criarVotoDto());

        ResponseEntity<VotoDto> response = controller.votar(criarVotoDto());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(criarVotoDto(), response.getBody());
    }

    @Test
    void whenVotarThenReturnAnExceptionExistsVoto() {
        when(service.votar(any())).thenThrow(new ServiceException(JA_VOTOU, HttpStatus.BAD_REQUEST));
        when(modelMapper.map(any(),any())).thenReturn(criarVotoDto());

        assertThrows(ServiceException.class, () -> {
            ResponseEntity<VotoDto> response = controller.votar(criarVotoDto());
            assertNotNull(response);
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        });
    }
}
