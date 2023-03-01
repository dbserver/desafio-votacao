package com.dbserver.desafio.votacao.endpoint.mapper

import br.com.six2six.fixturefactory.Fixture
import com.dbserver.desafio.votacao.endpoint.dto.PautaSessaoDTO
import com.dbserver.desafio.votacao.usecase.domain.Pauta
import spock.lang.Specification

import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates
import static fixtures.PautaSessaoDtoTemplate.PAUTA_SESSAO_DTO_OBRA
import static fixtures.PautaTemplate.PAUTA_OBRA

class PautaParaPautaSessaoDtoMapperSpec extends Specification {

    Pauta pautaRequerida
    PautaSessaoDTO pautaSessaoDTOMock

    def setup() {
        loadTemplates("fixtures")

        pautaRequerida = Fixture.from(Pauta)
                .gimme(PAUTA_OBRA)

        pautaSessaoDTOMock = Fixture.from(PautaSessaoDTO)
                .gimme(PAUTA_SESSAO_DTO_OBRA)
    }

    def "Deveria converter um objeto Pauta valido em um objeto PautaSessaoDTO"() {
        given: "Um objeto Pauta de entrada valido"
        pautaRequerida

        when: "quando o metodo map do PautaSessaoDTO for invocado"
        PautaSessaoDTO pautaSessaoDTOResultado = PautaParaPautaSessaoDtoMapper.INSTANCE.map(pautaRequerida)

        then: "o objeto PautaSessaoDTO deve ser válido com todos os campos válidos"
        pautaSessaoDTOResultado
        verifyAll(pautaSessaoDTOResultado) {
            nomePauta == pautaSessaoDTOMock.nomePauta
            descricaoPauta == pautaSessaoDTOMock.descricaoPauta
        }
    }

    def "Deveria converter um objeto Pauta nulo em um objeto PautaSessaoDTO nulo"() {
        given: "Um objeto Pauta de entrada nulo"
        pautaRequerida = null

        when: "quando o metodo map do PautaSessaoDTO for invocado"
        PautaSessaoDTO pautaSessaoDTOResultado = PautaParaPautaSessaoDtoMapper.INSTANCE.map(pautaRequerida)

        then: "o objeto PautaSessaoDTO deve ser nulo"
        pautaSessaoDTOResultado == null
    }
}
