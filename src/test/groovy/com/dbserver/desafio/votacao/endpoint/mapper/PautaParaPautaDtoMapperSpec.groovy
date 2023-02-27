package com.dbserver.desafio.votacao.endpoint.mapper

import br.com.six2six.fixturefactory.Fixture
import com.dbserver.desafio.votacao.endpoint.dto.PautaDTO
import com.dbserver.desafio.votacao.usecase.domain.Pauta
import spock.lang.Specification

import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates
import static fixtures.PautaDtoTemplate.PAUTA_DTO_OBRA
import static fixtures.PautaTemplate.PAUTA_OBRA

class PautaParaPautaDtoMapperSpec extends Specification {

    Pauta pautaRequerida
    PautaDTO pautaDTOMock

    def setup() {
        loadTemplates("fixtures")

        pautaRequerida = Fixture.from(Pauta)
                .gimme(PAUTA_OBRA)

        pautaDTOMock = Fixture.from(PautaDTO)
                .gimme(PAUTA_DTO_OBRA)
    }

    def "Deveria converter um objeto Pauta valido em um objeto PautaDTO"() {
        given: "Um objeto Pauta de entrada valido"
        pautaRequerida

        when: "quando o metodo map do PautaDTO for invocado"
        PautaDTO pautaDTOResultado = PautaParaPautaDtoMapper.INSTANCE.map(pautaRequerida)

        then: "o objeto PautaDTO deve ser válido com todos os campos válidos"
        pautaDTOResultado
        verifyAll(pautaDTOResultado) {
            nome == pautaDTOMock.nome
            descricao == pautaDTOMock.descricao
        }
    }

    def "Deveria converter um objeto Pauta nulo em um objeto PautaDTO nulo"() {
        given: "Um objeto Pauta de entrada nulo"
        pautaRequerida = null

        when: "quando o metodo map do PautaDTO for invocado"
        PautaDTO pautaDTOResultado = PautaParaPautaDtoMapper.INSTANCE.map(pautaRequerida)

        then: "o objeto PautaDTO deve ser nulo"
        pautaDTOResultado == null
    }
}
