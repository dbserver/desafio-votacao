package br.tec.db.desafio.business.service;

import br.tec.db.desafio.api.v1.dto.pauta.PautaMapperV1;
import br.tec.db.desafio.api.v1.dto.pauta.PautaRequestV1;
import br.tec.db.desafio.api.v1.dto.pauta.PautaResponseV1;
import br.tec.db.desafio.api.v1.dto.sessao.SessaoMapperV1;
import br.tec.db.desafio.api.v1.dto.sessao.request.SessaoParaCriarRequestV1;
import br.tec.db.desafio.api.v1.dto.sessao.response.SessaoCriadaResponseV1;
import br.tec.db.desafio.business.domain.Pauta;
import br.tec.db.desafio.business.domain.Sessao;
import br.tec.db.desafio.business.service.implementation.PautaServiceImpl;
import br.tec.db.desafio.business.service.implementation.SessaoServiceImpl;
import br.tec.db.desafio.business.service.implementation.validacao.pauta.ValidacaoPauta;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.ValidacaoSessao;
import br.tec.db.desafio.repository.PautaRepository;
import br.tec.db.desafio.repository.SessaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SessaoServiceImplTest {
    @Mock
    PautaRepository pautaRepository;
    @Mock
    SessaoRepository sessaoRepository;
    @Mock
    List<ValidacaoSessao> validacoesSessao;

    @Test
    void devePersistirSessaoComSucesso() {
        SessaoServiceImpl sessaoServiceImpl = new SessaoServiceImpl(sessaoRepository,pautaRepository,validacoesSessao);

        SessaoParaCriarRequestV1 shouldSessaoRequestV1 =
                new SessaoParaCriarRequestV1(
                        "Tema da pauta",
                        2L);

        Sessao shouldSessaoRequestV1ToSessao=
                SessaoMapperV1.sessaoParaCriarRequestV1ToSessao
                        (shouldSessaoRequestV1);

        when(sessaoRepository.save(any())
        ).thenReturn(shouldSessaoRequestV1ToSessao);

        SessaoParaCriarRequestV1 sessaoRequestV1 = new SessaoParaCriarRequestV1(
                "Tema da pauta",
                2L);
        Sessao sessaoRequestV1ToPauta =
                SessaoMapperV1.sessaoParaCriarRequestV1ToSessao(sessaoRequestV1);

        SessaoCriadaResponseV1 actual = sessaoServiceImpl.criarUmaNovaSessao(sessaoRequestV1);

        SessaoCriadaResponseV1 expected =
                SessaoMapperV1.sessaoToSessaoCriadaResponseV1(sessaoRequestV1ToPauta);

        assertThat(expected).usingRecursiveComparison().isEqualTo(
                actual
        );
    }
}

