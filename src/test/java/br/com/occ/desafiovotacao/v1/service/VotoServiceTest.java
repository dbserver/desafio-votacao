package br.com.occ.desafiovotacao.v1.service;

import br.com.occ.desafiovotacao.config.exception.ServiceException;
import br.com.occ.desafiovotacao.v1.enums.VotoEnum;
import br.com.occ.desafiovotacao.v1.model.Pauta;
import br.com.occ.desafiovotacao.v1.model.Sessao;
import br.com.occ.desafiovotacao.v1.model.Voto;
import br.com.occ.desafiovotacao.v1.repository.VotoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.List;

import static br.com.occ.desafiovotacao.utils.EntityUtils.*;
import static br.com.occ.desafiovotacao.utils.EntityUtils.ID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class VotoServiceTest {

    private static final String VOTO_NAO_ENCONTRADO = "Voto não encontrada";
    private static final String SEM_VOTO = "Associado não possui voto em nenhuma pauta";

    @InjectMocks
    VotoService service;
    @Mock
    VotoRepository repository;
    @Mock
    AssociadoService associadoService;
    @Mock
    PautaService pautaService;

    @Mock
    SessaoService sessaoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenFindByIdThenReturnAnVotoInstance() {
        when(repository.findById(anyLong())).thenReturn(criarVotoOptional());

        Voto response = service.findById(ID);

        assertNotNull(response);

        assertEquals(Voto.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(criarAssociado(true), response.getAssociado());
        assertEquals(criarPauta(), response.getPauta());
        assertEquals(VotoEnum.SIM, response.getVoto());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException() {
        when(repository.findById(anyLong()))
                .thenThrow(new ServiceException(VOTO_NAO_ENCONTRADO, HttpStatus.BAD_REQUEST));

        try{
            service.findById(ID);
        } catch (Exception ex) {
            assertEquals(ServiceException.class, ex.getClass());
            assertEquals(VOTO_NAO_ENCONTRADO, ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAnListByAssociado() {
        when(repository.findAllByAssociado_Id(anyLong())).thenReturn(List.of(criarVoto()));

        List<Voto> response = service.findAllByAssociado(ID);

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Voto.class, response.get(0).getClass());
        assertEquals(ID, response.get(0).getId());
        assertEquals(criarPauta(), response.get(0).getPauta());
        assertEquals(VotoEnum.SIM, response.get(0).getVoto());
    }

    @Test
    void whenFindAllThenReturnAnServiceException() {
        when(repository.findAllByAssociado_Id(anyLong())).thenThrow(new ServiceException(SEM_VOTO, HttpStatus.BAD_REQUEST));

        try{
            service.findAllByAssociado(ID);
        } catch (Exception ex) {
            assertEquals(ServiceException.class, ex.getClass());
            assertEquals(SEM_VOTO, ex.getMessage());
        }
    }


    @Test
    void whenVotarThenReturnAnVotoInstance() {
        when(repository.save(any(Voto.class))).thenReturn(criarVoto());
        when(associadoService.findById(anyLong())).thenReturn(criarAssociado(true));
        when(pautaService.findById(anyLong())).thenReturn(criarPautaComSessao());

        Voto response = service.votar(criarVoto());

        assertEquals(Voto.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(criarAssociado(true), response.getAssociado());
        assertEquals(criarPauta(), response.getPauta());
        assertEquals(VotoEnum.SIM, response.getVoto());
    }

    @Test
    void whenVotarThenReturnAnExceptionExistsVoto() {
        when(repository.save(any(Voto.class))).thenReturn(criarVoto());
        when(associadoService.findById(anyLong())).thenReturn(criarAssociado(true));
        when(pautaService.findById(anyLong())).thenReturn(criarPautaComSessao());
        when(repository.existsVotoByAssociado_IdAndPauta_Id(anyLong(),anyLong())).thenThrow(new ServiceException("Associado já votou nesta pauta", HttpStatus.BAD_REQUEST));

        assertThrows(ServiceException.class, () -> service.votar(criarVoto()));
    }
}
