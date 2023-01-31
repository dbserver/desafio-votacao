package br.com.occ.desafiovotacao.v1.service;

import br.com.occ.desafiovotacao.config.exception.ServiceException;
import br.com.occ.desafiovotacao.v1.model.Pauta;
import br.com.occ.desafiovotacao.v1.repository.PautaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.List;

import static br.com.occ.desafiovotacao.utils.EntityUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PautaServiceTest {

    private static final String PAUTA_NAO_ENCONTRADA = "Pauta não encontrada";

    @InjectMocks
    private PautaService service;
    @Mock
    private PautaRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenFindByIdThenReturnAnPautaInstance() {
        when(repository.findById(anyLong())).thenReturn(criarPautaOptional());

        Pauta response = service.findById(ID);

        assertNotNull(response);

        assertEquals(Pauta.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(DESCRICAO_PAUTA, response.getDescricao());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException() {
        when(repository.findById(anyLong()))
                .thenThrow(new ServiceException(PAUTA_NAO_ENCONTRADA, HttpStatus.BAD_REQUEST));

        try{
            service.findById(ID);
        } catch (Exception ex) {
            assertEquals(ServiceException.class, ex.getClass());
            assertEquals(PAUTA_NAO_ENCONTRADA, ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAnListOfPautas() {
        when(repository.findAll()).thenReturn(List.of(criarPauta()));

        List<Pauta> response = service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Pauta.class, response.get(0).getClass());
        assertEquals(ID, response.get(0).getId());
        assertEquals(DESCRICAO_PAUTA, response.get(0).getDescricao());
    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(repository.save(any(Pauta.class))).thenReturn(criarPauta());
        when(modelMapper.map(any(), any())).thenReturn(criarPauta());

        Pauta response = service.save(criarPautaDto());

        assertNotNull(response);

        assertEquals(Pauta.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(DESCRICAO_PAUTA, response.getDescricao());
    }

    @Test
    void whenFindAllThenReturnAnListOfAsPautasAtivas() {
        when(repository.findAllPautasAtivas(any())).thenReturn(List.of(criarPauta()));

        List<Pauta> response = service.findAllAtivas();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Pauta.class, response.get(0).getClass());
        assertEquals(ID, response.get(0).getId());
        assertEquals(DESCRICAO_PAUTA, response.get(0).getDescricao());
    }
}
