package db.desafiovotacao.service;

import db.desafiovotacao.dto.AssociadoPautaRequest;
import db.desafiovotacao.exceptions.ConflictException;
import db.desafiovotacao.exceptions.NotFoundException;
import db.desafiovotacao.model.Associado;
import db.desafiovotacao.model.AssociadoPauta;
import db.desafiovotacao.model.Pauta;
import db.desafiovotacao.repository.AssociadoPautaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AssociadoPautaServiceTest {

    private AssociadoPautaService associadoPautaService;

    @Mock
    private AssociadoPautaRepository associadoPautaRepository;

    @Mock
    private AssociadoService associadoService;

    @Mock
    private PautaService pautaService;

    @Mock
    private Associado associado;

    @Mock
    private Pauta pauta;

    @Mock
    private AssociadoPautaRequest associadoPautaRequest;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);

        associadoPautaService = new AssociadoPautaService(associadoPautaRepository, associadoService, pautaService);

        associado = Associado.builder()
                .id(1L)
                .CPF("000.000.000-00")
                .build();

        pauta = Pauta.builder()
                .id(1L)
                .titulo("Pauta Teste")
                .descricao("Teste")
                .build();

        associadoPautaRequest = new AssociadoPautaRequest(1L, "000.000.000-00");
    }


    @Test
    void deveCadastrarUmAssociadoEmUmaPauta(){

        AssociadoPauta associadoPauta = AssociadoPauta.builder()
                .id(1L)
                .pauta(pauta)
                .associado(associado)
                .votou(false)
                .build();

        Mockito.when(associadoService.buscarPorCPF(Mockito.any())).thenReturn(associado);
        Mockito.when(pautaService.buscarPautaPorID(Mockito.any())).thenReturn(pauta);

        Mockito.when(associadoPautaRepository.findByAssociadoAndPauta(associado, pauta)).thenReturn(Optional.empty());

        associadoPautaService.associadoEstaCadastrado(associado, pauta);

        Mockito.when(associadoPautaRepository.save(Mockito.any())).thenReturn(associadoPauta);

        associadoPautaService.cadastarAssociadoNaPauta(associadoPautaRequest);

        assertAll("cadastro de associado na pauta",
                () -> assertEquals(associado.getCPF(), associadoPauta.getAssociado().getCPF()),
                () -> assertEquals(pauta.getId(), associadoPauta.getPauta().getId()),
                () -> assertFalse(associadoPauta.getVotou())
        );
    }

    @Test
    void deveCadastrarUmAssociadoEmUmaPautaException(){

        AssociadoPauta associadoPauta = AssociadoPauta.builder()
                .id(1L)
                .pauta(pauta)
                .associado(associado)
                .votou(false)
                .build();

        Mockito.when(associadoService.buscarPorCPF(Mockito.any())).thenReturn(associado);
        Mockito.when(pautaService.buscarPautaPorID(Mockito.any())).thenReturn(pauta);

        Mockito.when(associadoPautaRepository.findByAssociadoAndPauta(associado, pauta)).thenReturn(Optional.of(associadoPauta));

        associadoPautaService.associadoEstaCadastrado(associado, pauta);

        assertThrows(ConflictException.class,
                () -> associadoPautaService.cadastarAssociadoNaPauta(associadoPautaRequest));
    }


    @Test
    void deveBuscarUmAssociadoCadastradoNaPauta(){

        AssociadoPauta associadoPauta = AssociadoPauta.builder()
                .id(1L)
                .pauta(pauta)
                .associado(associado)
                .votou(false)
                .build();

        Mockito.when(associadoPautaRepository.findByAssociadoAndPauta(associado, pauta)).thenReturn(Optional.of(associadoPauta));

        AssociadoPauta cadastrado = associadoPautaService.buscarAssociadoPauta(associado, pauta);

        assertAll("busca associado",
                () -> assertNotNull(cadastrado),
                () -> assertEquals(associadoPauta.getId(), cadastrado.getId()),
                () -> assertEquals(associadoPauta.getPauta(), cadastrado.getPauta()),
                () -> assertEquals(associadoPauta.getAssociado(), cadastrado.getAssociado()),
                () -> assertEquals(associadoPauta.getVotou(), cadastrado.getVotou())
        );
    }

    @Test
    void deveBuscarAssociadoCadastradoNaPautaException(){

        Mockito.when(associadoPautaRepository.findByAssociadoAndPauta(associado, pauta)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class,
                () -> associadoPautaService.buscarAssociadoPauta(associado, pauta));
    }


    @Test
    void deveRetornarFalsoSeAssociadoNaoEstiverCadastradoNaPauta(){

        Boolean associadoEstaCadastrado = associadoPautaService.associadoEstaCadastrado(associado, pauta);

        assertFalse(associadoEstaCadastrado);
    }

    @Test
    void deveRetornarVerdadeiroSeAssociadoEstiverCadastradoNaPauta(){

        Mockito.when(associadoPautaRepository.findByAssociadoAndPauta(Mockito.any(), Mockito.any())).thenReturn(Optional.of(new AssociadoPauta()));

        Boolean associadoEstaCadastrado = associadoPautaService.associadoEstaCadastrado(associado, pauta);

        assertTrue(associadoEstaCadastrado);
    }
}