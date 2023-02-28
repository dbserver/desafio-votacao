package com.dbserver.desafio.valida.cpf.repository.mapper

import br.com.six2six.fixturefactory.Fixture
import com.dbserver.desafio.valida.cpf.repository.entity.PautaEntity
import spock.lang.Specification

import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates
import static fixtures.PautaTemplate.PAUTA_OBRA_COM_SESSAO
import static fixtures.PautaEntityTemplate.PAUTA_ENTITY_OBRA_COM_SESSAO

class PautaEntityParaPautaMapperSpec extends Specification {

    PautaEntity pautaEntityRequerida
    com.dbserver.desafio.valida.cpf.usecase.domain.Pauta pautaMock

    def setup() {
        loadTemplates("fixtures")

        pautaEntityRequerida = Fixture.from(PautaEntity)
                .gimme(PAUTA_ENTITY_OBRA_COM_SESSAO)

        pautaMock = Fixture.from(com.dbserver.desafio.valida.cpf.usecase.domain.Pauta)
                .gimme(PAUTA_OBRA_COM_SESSAO)
    }

    def "Deveria converter um objeto PautaEntity valido em um objeto Pauta"() {
        given: "Um objeto PautaEntity de entrada valido"
        pautaEntityRequerida

        when: "quando o metodo map do Pauta for invocado"
        com.dbserver.desafio.valida.cpf.usecase.domain.Pauta pautaResultado = PautaEntityParaPautaMapper.INSTANCE.map(pautaEntityRequerida)

        then: "o objeto pauta deve ser válido com todos os campos válidos"
        pautaResultado == pautaMock
    }

    def "Deveria converter um objeto PautaEntity nulo em um objeto Pauta nulo"() {
        given: "Um objeto PautaEntity de entrada nulo"
        pautaEntityRequerida = null

        when: "quando o metodo map do Pauta for invocado"
        com.dbserver.desafio.valida.cpf.usecase.domain.Pauta pautaResultado = PautaEntityParaPautaMapper.INSTANCE.map(pautaEntityRequerida)

        then: "o objeto pauta deve ser nulo"
        pautaResultado == null
    }
}

