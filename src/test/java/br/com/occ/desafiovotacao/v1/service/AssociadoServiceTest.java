package br.com.occ.desafiovotacao.v1.service;

import br.com.occ.desafiovotacao.config.exception.ServiceException;
import br.com.occ.desafiovotacao.v1.model.Associado;
import br.com.occ.desafiovotacao.v1.repository.AssociadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.List;

import static br.com.occ.desafiovotacao.utils.EntityUtils.*;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class AssociadoServiceTest {

    private static final String ASSOCIADO_NAO_ENCONTRADO = "Associado não localizado";

    @InjectMocks
    private AssociadoService service;

    @Mock
    private AssociadoRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenFindByIdThenReturnAnAssociadoInstance() {
        when(repository.findById(anyLong())).thenReturn(criarAssociadoOptional(true));

        Associado response = service.findById(ID);

        assertNotNull(response);

        assertEquals(Associado.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(CPF, response.getCpf());
        assertEquals(ATIVO, response.getAtivo());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException() {
        when(repository.findById(anyLong()))
                .thenThrow(new ServiceException(ASSOCIADO_NAO_ENCONTRADO, HttpStatus.BAD_REQUEST));

        try{
            service.findById(ID);
        } catch (Exception ex) {
            assertEquals(ServiceException.class, ex.getClass());
            assertEquals(ASSOCIADO_NAO_ENCONTRADO, ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAnListOfAssociadosAtivos() {
        when(repository.findAllByAtivoIs(ATIVO)).thenReturn(List.of(criarAssociado(ATIVO)));

        List<Associado> response = service.findAllAtivos();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Associado.class, response.get(0).getClass());
        assertEquals(ID, response.get(0).getId());
        assertEquals(NOME, response.get(0).getNome());
        assertEquals(CPF, response.get(0).getCpf());
        assertEquals(ATIVO, response.get(0).getAtivo());
    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(repository.save(any(Associado.class))).thenReturn(criarAssociado(true));

        Associado response = service.save(criarAssociado(true));

        assertNotNull(response);

        assertEquals(Associado.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(CPF, response.getCpf());
        assertEquals(ATIVO, response.getAtivo());
    }
}
