package br.com.occ.desafiovotacao.v1.controller;

import br.com.occ.desafiovotacao.config.exception.ServiceException;
import br.com.occ.desafiovotacao.v1.dto.SessaoDto;
import br.com.occ.desafiovotacao.v1.service.ISessaoService;
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
class SessaoControllerTest {

    private static final String SESSAO_NAO_ENCONTRADA = "Sessão não encontrada";
    private static final String NAO_EXISTE_SESSAO_ATIVA = "Não existe sessões ativas";

    @InjectMocks
    private SessaoController controller;

    @Mock
    private ISessaoService service;
    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenSaveThenReturnSuccess() {
        when(service.save(any(), anyLong())).thenReturn(criarSessaoUmMinuto());
        when(modelMapper.map(any(), any())).thenReturn(criarSessaoDto());

        ResponseEntity<SessaoDto> response = controller.salvar(criarSessaoDtoDataFimNull(), ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(criarSessaoDtoUmMinuto(), response.getBody());
    }

    @Test
    void whenCreateThenReturnSuccessOnDateFinal() {
        when(service.save(any(), anyLong())).thenReturn(criarSessao());
        when(modelMapper.map(any(), any())).thenReturn(criarSessaoDto());

        ResponseEntity<SessaoDto> response = controller.salvar(criarSessaoDto(), ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(criarSessaoDto(), response.getBody());
    }

    @Test
    void whenGetByIdThenResultSuccess() {
        when(service.findById(anyLong())).thenReturn(criarSessao());
        when(modelMapper.map(any(),any())).thenReturn(criarSessaoDto());

        ResponseEntity<SessaoDto> response = controller.getById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(criarSessaoDto(), response.getBody());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException() {
        when(service.findById(anyLong())).thenThrow(new ServiceException(SESSAO_NAO_ENCONTRADA, HttpStatus.BAD_REQUEST));

        assertThrows(ServiceException.class, () -> {
            ResponseEntity<SessaoDto> response = controller.getById(ID);
            assertNotNull(response);
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        });
    }

    @Test
    void whenFindAllThenReturnAnListOfPautasAtivas() {
        when(service.findAllAtivas()).thenReturn(List.of(criarSessaoUmMinuto()));
        when(modelMapper.map(any(),any())).thenReturn(criarSessaoDtoUmMinuto());

        ResponseEntity<List<SessaoDto>> response = controller.getSessoesAtivas();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(1, response.getBody().size());
        assertEquals(criarSessaoDtoUmMinuto(), response.getBody().get(0));
    }

    @Test
    void whenFindAllThenReturnAnListOfPautasAtivasException() {
        when(service.findAllAtivas()).thenThrow(new ServiceException(NAO_EXISTE_SESSAO_ATIVA, HttpStatus.BAD_REQUEST));
        assertThrows(ServiceException.class,() -> {
            ResponseEntity<List<SessaoDto>> response = controller.getSessoesAtivas();
            assertNotNull(response);
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        });
    }
}
