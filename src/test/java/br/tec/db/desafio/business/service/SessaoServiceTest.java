package br.tec.db.desafio.business.service;

import br.tec.db.desafio.api.v1.dto.sessao.SessaoMapperV1;
import br.tec.db.desafio.api.v1.dto.sessao.request.SessaoParaCriarRequestV1;
import br.tec.db.desafio.api.v1.dto.sessao.response.SessaoCriadaResponseV1;
import br.tec.db.desafio.business.domain.Sessao;
import br.tec.db.desafio.business.service.implementation.SessaoService;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.AValidacaoCriarUmaNovaSessao;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.AValidacaoTotalDeVotosDaSessao;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.AValidacaoVotarEmUmaSessao;
import br.tec.db.desafio.repository.AssociadoRepository;
import br.tec.db.desafio.repository.AssociadoSessaoRepository;
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
public class SessaoServiceTest {
    @Mock
    PautaRepository pautaRepository;
    @Mock
    SessaoRepository sessaoRepository;
    @Mock
    AssociadoRepository associadoRepository;
    @Mock
    AssociadoSessaoRepository associadoSessaoRepository;
    @Mock
    List<AValidacaoCriarUmaNovaSessao> aValidacaoCriarUmaNovaSessaoList;
    @Mock
    List<AValidacaoTotalDeVotosDaSessao> aValidacaoTotalDeVotosDaSessaoList;
    @Mock
    List<AValidacaoVotarEmUmaSessao> aValidacaoVotarEmUmaSessaoList;
    private static final String ASSUNTO_PAUTA = "tema da pauta";
    private static final Long DURACAO_SESSAO = 2L;


    @Test
    void devePersistirSessaoComSucesso() {
        SessaoService sessaoServiceImpl = new SessaoService(sessaoRepository,pautaRepository, associadoRepository,associadoSessaoRepository,
                aValidacaoTotalDeVotosDaSessaoList,aValidacaoCriarUmaNovaSessaoList,aValidacaoVotarEmUmaSessaoList
        );

        SessaoParaCriarRequestV1 shouldSessaoRequestV1 =
                new SessaoParaCriarRequestV1(
                        ASSUNTO_PAUTA,
                        2L);

        Sessao shouldSessaoRequestV1ToSessao=
                SessaoMapperV1.sessaoParaCriarRequestV1ToSessao
                        (shouldSessaoRequestV1);

        when(sessaoRepository.save(any())
        ).thenReturn(shouldSessaoRequestV1ToSessao);

        when(pautaRepository.findPautaByAssunto(any())
        ).thenReturn(shouldSessaoRequestV1ToSessao.getPauta());


        SessaoParaCriarRequestV1 sessaoRequestV1 = new SessaoParaCriarRequestV1(
                ASSUNTO_PAUTA,
                DURACAO_SESSAO);
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

