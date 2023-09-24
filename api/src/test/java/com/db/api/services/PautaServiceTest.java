package com.db.api.services;

import com.db.api.dtos.PautaDto;
import com.db.api.exceptions.ParametrosInvalidosException;
import com.db.api.models.Pauta;
import com.db.api.repositories.PautaRepository;
import com.db.api.stubs.PautaStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@DisplayName("Testes para PautaService")
class PautaServiceTest {

    @Mock
    private PautaRepository pautaRepository;

    @InjectMocks
    private PautaService pautaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Ao criar uma nova pauta com valores válidos, o método save do repositório deve ser chamado")
    void criarNovaPauta_comValoresValidos() {
        PautaDto pautaDto = new PautaDto(PautaStub.gerarPautaDtoValida().getTitulo(), PautaStub.gerarPautaDtoValida().getDescricao());

        pautaService.criarNovaPauta(pautaDto);

        verify(pautaRepository, Mockito.times(1)).save(any(Pauta.class));
    }

    @Test
    @DisplayName("Ao criar uma nova pauta sem titulo, deve retornar exceção")
    void criarNovaPauta_comTituloEmBranco() {
        PautaDto pautaDto = new PautaDto(PautaStub.gerarPautaDtoTituloVazio().getTitulo(), PautaStub.gerarPautaDtoTituloVazio().getDescricao());

        assertThrows(ParametrosInvalidosException.class, () ->
                pautaService.criarNovaPauta(pautaDto));
    }
}
