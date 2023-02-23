package br.tec.db.desafio.business.service;

import br.tec.db.desafio.api.v1.dto.pauta.PautaMapperV1;
import br.tec.db.desafio.api.v1.dto.pauta.PautaRequestV1;
import br.tec.db.desafio.api.v1.dto.pauta.PautaResponseV1;
import br.tec.db.desafio.business.domain.Pauta;
import br.tec.db.desafio.business.service.implementation.PautaServiceImpl;
import br.tec.db.desafio.business.service.implementation.validacao.pauta.ValidacaoPauta;
import br.tec.db.desafio.repository.PautaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PautaServiceImplTest {
    @Mock
    PautaRepository pautaRepository;
    @Mock
    List<ValidacaoPauta> validacoesPauta;

    @Test
    void devePersistirPautaComSucesso() {
        PautaServiceImpl pautaServiceImpl = new PautaServiceImpl(pautaRepository,validacoesPauta);

        PautaRequestV1 shouldPautaRequestV1 = new PautaRequestV1("Tema da pauta");

        Pauta shouldPautaRequestV1ToPauta=
                PautaMapperV1.pautaRequestV1ToPauta(shouldPautaRequestV1);

        when(pautaRepository.save(any())
        ).thenReturn(shouldPautaRequestV1ToPauta);

        PautaRequestV1 pautaRequestV1 = new PautaRequestV1("Tema da pauta");
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
