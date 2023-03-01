package com.dbserver.desafio.votacao.endpoint.mapper

import br.com.six2six.fixturefactory.Fixture
import com.dbserver.desafio.votacao.endpoint.dto.PautaDTO
import com.dbserver.desafio.votacao.usecase.domain.Pauta
import spock.lang.Specification

import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates
import static fixtures.PautaDtoTemplate.PAUTA_DTO_OBRA
import static fixtures.PautaTemplate.PAUTA_OBRA

class  PautaDtoParaPautaMapperSpec extends Specification {

    PautaDTO pautaDTORequerida
    Pauta pautaMock

    def setup() {
        loadTemplates("fixtures")

        pautaDTORequerida = Fixture.from(PautaDTO)
                .gimme(PAUTA_DTO_OBRA)

        pautaMock = Fixture.from(Pauta)
                .gimme(PAUTA_OBRA)
    }

    def "Deveria converter um objeto PautaDTO valido em um objeto Pauta"() {
        given: "Um objeto PautaDTO de entrada valido"
        pautaDTORequerida

        when: "quando o metodo map do Pauta for invocado"
        Pauta pautaResultado = PautaDtoParaPautaMapper.INSTANCE.map(pautaDTORequerida)

        then: "o objeto pauta deve ser válido com todos os campos válidos"
        pautaResultado
        verifyAll(pautaResultado) {
            nome == pautaMock.nome
            descricao == pautaMock.descricao
        }
    }

    def "Deveria converter um objeto PautaDTO nulo em um objeto Pauta nulo"() {
        given: "Um objeto PautaDTO de entrada nulo"
        pautaDTORequerida = null

        when: "quando o metodo map do Pauta for invocado"
        Pauta pautaResultado = PautaDtoParaPautaMapper.INSTANCE.map(pautaDTORequerida)

        then: "o objeto pauta deve ser nulo"
        pautaResultado == null
    }
}
