package com.db.api.services;

import com.db.api.dtos.AssociadoDto;
import com.db.api.exceptions.AssociadoJaCadastradoException;
import com.db.api.exceptions.ParametrosInvalidosException;
import com.db.api.models.Associado;
import com.db.api.repositories.AssociadoRepository;
import com.db.api.stubs.AssociadoStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Testes para AssociadoService")
class AssociadoServiceTest {

    @Mock
    private AssociadoRepository associadoRepository;

    @Mock
    private Validator validator;

    @InjectMocks
    private AssociadoService associadoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Ao criar um novo associado com valores válidos, o método save do repositório deve ser chamado")
    void registrarAssociado_ComValoresValidos() {
        when(validator.validate(any())).thenReturn(new HashSet<>());

        AssociadoDto associadoDto = new AssociadoDto(AssociadoStub.gerarAssociadoDtoValida().getNome(), AssociadoStub.gerarAssociadoDtoValida().getCpf());

        associadoService.registrarAssociado(associadoDto);

        verify(associadoRepository, Mockito.times(1)).save(any(Associado.class));
    }

    @Test
    @DisplayName("Ao tentar cadastrar um associado com CPF já cadastrado, deve retornar exceção de associado já cadastrado")
    void registrarAssociado_ComCpfJaCadastrado() {
        when(validator.validate(any())).thenReturn(new HashSet<>());

        AssociadoDto associadoDto = new AssociadoDto(AssociadoStub.gerarAssociadoDtoValida().getNome(), AssociadoStub.gerarAssociadoDtoValida().getCpf());

        when(associadoRepository.existsAssociadoByCpf(associadoDto.getCpf())).thenReturn(true);

        assertThrows(AssociadoJaCadastradoException.class, () ->
                associadoService.registrarAssociado(associadoDto));
    }
}
