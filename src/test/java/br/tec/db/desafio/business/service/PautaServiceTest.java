package br.tec.db.desafio.business.service;

import br.tec.db.desafio.api.v1.dto.pauta.PautaMapperV1;
import br.tec.db.desafio.api.v1.dto.pauta.PautaRequestV1;
import br.tec.db.desafio.api.v1.dto.pauta.PautaResponseV1;
import br.tec.db.desafio.business.domain.Pauta;
import br.tec.db.desafio.business.service.implementation.PautaService;
import br.tec.db.desafio.repository.PautaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PautaServiceTest {
    @Mock
    PautaRepository pautaRepository;
    private static final String ASSUNTO = "tema da pauta";

    @Test
    void devePersistirPautaComSucesso() {
        PautaService pautaServiceImpl = new PautaService(pautaRepository);

        PautaRequestV1 shouldPautaRequestV1 = new PautaRequestV1(ASSUNTO);

        Pauta shouldPautaRequestV1ToPauta=
                PautaMapperV1.pautaRequestV1ToPauta(shouldPautaRequestV1);

        when(pautaRepository.save(any())
        ).thenReturn(shouldPautaRequestV1ToPauta);

        PautaRequestV1 pautaRequestV1 = new PautaRequestV1(ASSUNTO);
        Pauta pautaRequestV1ToPauta =
                PautaMapperV1.pautaRequestV1ToPauta(pautaRequestV1);

        PautaResponseV1 actual = pautaServiceImpl.criarUmaNovaPauta(pautaRequestV1);

        PautaResponseV1 expected =
                PautaMapperV1.pautaToPautaResponseV1(pautaRequestV1ToPauta);

        assertThat(expected).usingRecursiveComparison().isEqualTo(
                actual
        );
    }
}
