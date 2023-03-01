package br.tec.db.desafio.business.service;

import br.tec.db.desafio.api.v1.dto.associado.AssociadoMapperV1;
import br.tec.db.desafio.api.v1.dto.associado.AssociadoRequestV1;
import br.tec.db.desafio.api.v1.dto.associado.AssociadoResponseV1;
import br.tec.db.desafio.business.domain.Associado;
import br.tec.db.desafio.business.service.implementation.AssociadoService;
import br.tec.db.desafio.repository.AssociadoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AssociadoServiceTest {
    @Mock
    AssociadoRepository associadoRepository;
    private static final String NOME = "guilherme";
    private static final String CPF = "12312366990";

    @Test
    void devePersistirPautaComSucesso() {
        AssociadoService associadoService = new AssociadoService(associadoRepository);

        AssociadoRequestV1 shouldAssociadoRequestV1 = new AssociadoRequestV1(
                CPF,
                NOME);

        Associado shouldAssociadoRequestV1ToAssociado=
                AssociadoMapperV1.associadoRequestV1ToAssociado(shouldAssociadoRequestV1);

        when(associadoRepository.save(any())
        ).thenReturn(shouldAssociadoRequestV1ToAssociado);

        AssociadoRequestV1 associadoRequestV1 = new AssociadoRequestV1(CPF, NOME);
        Associado associadoRequestV1ToAssociado =
                AssociadoMapperV1.associadoRequestV1ToAssociado(associadoRequestV1);

        AssociadoResponseV1 actual = associadoService.criarUmNovoAssociado(associadoRequestV1);

        AssociadoResponseV1 expected =
                AssociadoMapperV1.associadoToAssociadoResponseV1(associadoRequestV1ToAssociado);

        assertThat(expected).usingRecursiveComparison().isEqualTo(
                actual
        );
    }
}
