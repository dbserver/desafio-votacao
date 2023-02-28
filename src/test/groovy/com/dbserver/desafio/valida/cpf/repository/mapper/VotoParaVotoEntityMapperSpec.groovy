package com.dbserver.desafio.valida.cpf.repository.mapper

import br.com.six2six.fixturefactory.Fixture
import com.dbserver.desafio.valida.cpf.repository.entity.VotoEntity
import spock.lang.Specification

import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates
import static fixtures.VotoEntityTemplate.VOTO_ENTITY_PAUTA
import static fixtures.VotoEntityTemplate.VOTO_ENTITY_PAUTA_SEM_SESSAO
import static fixtures.VotoEntityTemplate.VOTO_ENTITY_PAUTA_SEM_PAUTA
import static fixtures.VotoTemplate.VOTO_PAUTA_COM_SESSAO
import static fixtures.VotoTemplate.VOTO_PAUTA
import static fixtures.VotoTemplate.VOTO_PAUTA_SEM_PAUTA

class VotoParaVotoEntityMapperSpec extends Specification {

    com.dbserver.desafio.valida.cpf.usecase.domain.Voto votoRequerida
    com.dbserver.desafio.valida.cpf.usecase.domain.Voto votoSemSessaoRequerida
    com.dbserver.desafio.valida.cpf.usecase.domain.Voto votoSemPautaRequerida

    VotoEntity votoEntityMock
    VotoEntity votoEntitySemSessaoMock
    VotoEntity votoEntitySemPautaMock

    def setup() {
        loadTemplates("fixtures")

        votoRequerida = Fixture.from(com.dbserver.desafio.valida.cpf.usecase.domain.Voto).gimme(VOTO_PAUTA_COM_SESSAO)
        votoSemSessaoRequerida = Fixture.from(com.dbserver.desafio.valida.cpf.usecase.domain.Voto).gimme(VOTO_PAUTA)
        votoSemPautaRequerida = Fixture.from(com.dbserver.desafio.valida.cpf.usecase.domain.Voto).gimme(VOTO_PAUTA_SEM_PAUTA)

        votoEntityMock = Fixture.from(VotoEntity).gimme(VOTO_ENTITY_PAUTA)
        votoEntitySemSessaoMock = Fixture.from(VotoEntity).gimme(VOTO_ENTITY_PAUTA_SEM_SESSAO)
        votoEntitySemPautaMock = Fixture.from(VotoEntity).gimme(VOTO_ENTITY_PAUTA_SEM_PAUTA)
    }

    def "Deveria converter um objeto Voto valido em um objeto VotoEntity"() {
        given: "Um objeto Voto de entrada valido"
        votoRequerida

        when: "quando o metodo map do VotoEntity for invocado"
        VotoEntity votoEntityResultado = VotoParaVotoEntityMapper.INSTANCE.map(votoRequerida)

        then: "o objeto VotoEntity deve ser válido com todos os campos válidos"
        votoEntityResultado == votoEntityMock
    }

    def "Deveria converter um objeto Voto sem sessao valido em um objeto VotoEntity sem sessao"() {
        given: "Um objeto Voto de entrada valido"
        votoSemSessaoRequerida

        when: "quando o metodo map do VotoEntity for invocado"
        VotoEntity votoEntityResultado = VotoParaVotoEntityMapper.INSTANCE.map(votoSemSessaoRequerida)

        then: "o objeto VotoEntity deve ser válido sem sessao"
        votoEntityResultado == votoEntitySemSessaoMock
    }

    def "Deveria converter um objeto Voto sem pauta valido em um objeto VotoEntity sem pauta"() {
        given: "Um objeto Voto de entrada valido"
        votoSemPautaRequerida

        when: "quando o metodo map do VotoEntity for invocado"
        VotoEntity votoEntityResultado = VotoParaVotoEntityMapper.INSTANCE.map(votoSemPautaRequerida)

        then: "o objeto VotoEntity deve ser válido sem pauta"
        votoEntityResultado == votoEntitySemPautaMock
    }

    def "Deveria converter um objeto Voto nulo em um objeto VotoEntity nulo"() {
        given: "Um objeto Voto de entrada nulo"
        votoRequerida = null

        when: "quando o metodo map do VotoEntity for invocado"
        VotoEntity votoEntityResultado = VotoParaVotoEntityMapper.INSTANCE.map(votoRequerida)

        then: "o objeto VotoEntity deve ser nulo"
        votoEntityResultado == null
    }
}