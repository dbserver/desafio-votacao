package br.com.occ.desafiovotacao.v1.controller;

import br.com.occ.desafiovotacao.config.exception.ServiceException;
import br.com.occ.desafiovotacao.v1.dto.PautaDto;
import br.com.occ.desafiovotacao.v1.service.IPautaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static br.com.occ.desafiovotacao.utils.EntityUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class PautaControllerTest {

    private static final String PAUTA_NAO_ENCONTRADA = "Pauta não encontrada";
    private static final String NAO_EXISTE_PAUTA_ATIVA = "Não existe pautas ativas";

    @InjectMocks
    private PautaController controller;

    @Mock
    private IPautaService service;
    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenSaveThenReturnSuccess() {
        when(service.save(any())).thenReturn(criarPauta());
        when(modelMapper.map(any(), any())).thenReturn(criarPautaDto());

        ResponseEntity<PautaDto> response = controller.salvar(criarPautaDto());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(criarPautaDto(), response.getBody());
    }

    @Test
    void whenGetByIdThenResultSuccess() {
        when(service.findById(anyLong())).thenReturn(criarPauta());
        when(modelMapper.map(any(),any())).thenReturn(criarPautaDto());

        ResponseEntity<PautaDto> response = controller.getById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(criarPautaDto(), response.getBody());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException() {
        when(service.findById(anyLong())).thenThrow(new ServiceException(PAUTA_NAO_ENCONTRADA, HttpStatus.BAD_REQUEST));

        assertThrows(ServiceException.class, () -> {
            ResponseEntity<PautaDto> response = controller.getById(ID);
            assertNotNull(response);
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        });
    }

    @Test
    void whenFindAllThenReturnAnListOfPautasAtivas() {
        when(service.findAllAtivas()).thenReturn(List.of(criarPauta()));
        when(modelMapper.map(any(),any())).thenReturn(criarPautaDto());

        ResponseEntity<List<PautaDto>> response = controller.getPautasAtivas();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(1, response.getBody().size());
        assertEquals(criarPautaDto(), response.getBody().get(0));
    }

    @Test
    void whenFindAllThenReturnAnListOfPautasAtivasException() {
        when(service.findAllAtivas()).thenThrow(new ServiceException(NAO_EXISTE_PAUTA_ATIVA, HttpStatus.BAD_REQUEST));
        assertThrows(ServiceException.class,() -> {
            ResponseEntity<List<PautaDto>> response = controller.getPautasAtivas();
            assertNotNull(response);
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        });
    }
}
