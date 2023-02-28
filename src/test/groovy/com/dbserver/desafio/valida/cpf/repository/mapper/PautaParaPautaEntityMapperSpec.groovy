package com.dbserver.desafio.valida.cpf.repository.mapper

import br.com.six2six.fixturefactory.Fixture
import com.dbserver.desafio.valida.cpf.repository.entity.PautaEntity
import spock.lang.Specification

import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates
import static fixtures.PautaEntityTemplate.PAUTA_ENTITY_OBRA_COM_SESSAO
import static fixtures.PautaTemplate.PAUTA_OBRA_COM_SESSAO

class PautaParaPautaEntityMapperSpec extends Specification {

    com.dbserver.desafio.valida.cpf.usecase.domain.Pauta pautaRequerida
    PautaEntity pautaEntityMock

    def setup() {
        loadTemplates("fixtures")

        pautaEntityMock = Fixture.from(PautaEntity).gimme(PAUTA_ENTITY_OBRA_COM_SESSAO)

        pautaRequerida = Fixture.from(com.dbserver.desafio.valida.cpf.usecase.domain.Pauta).gimme(PAUTA_OBRA_COM_SESSAO)
    }

    def "Deveria converter um objeto Pauta valido em um objeto PautaEntity"() {
        given: "Um objeto Pauta de entrada valido"
        pautaRequerida

        when: "quando o metodo map do PautaEntity for invocado"
        PautaEntity pautaEntityResultado = PautaParaPautaEntityMapper.INSTANCE.map(pautaRequerida)

        then: "o objeto PautaEntity deve ser válido com todos os campos válidos"
        pautaEntityResultado == pautaEntityMock
    }

    def "Deveria converter um objeto Pauta nulo em um objeto PautaEntity nulo"() {
        given: "Um objeto Pauta de entrada nulo"
        pautaRequerida = null

        when: "quando o metodo map do PautaEntity for invocado"
        PautaEntity pautaEntityResultado = PautaParaPautaEntityMapper.INSTANCE.map(pautaRequerida)

        then: "o objeto PautaEntity deve ser nulo"
        pautaEntityResultado == null
    }
}

