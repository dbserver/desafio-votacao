package com.dbserver.desafio.votacao.repository.mapper

import br.com.six2six.fixturefactory.Fixture
import com.dbserver.desafio.votacao.repository.entity.VotoEntity
import com.dbserver.desafio.votacao.usecase.domain.Voto
import spock.lang.Specification

import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates
import static fixtures.VotoEntityTemplate.VOTO_ENTITY_PAUTA_SALVA
import static fixtures.VotoEntityTemplate.VOTO_ENTITY_PAUTA_SEM_SESSAO
import static fixtures.VotoEntityTemplate.VOTO_ENTITY_PAUTA_SEM_PAUTA
import static fixtures.VotoTemplate.VOTO_PAUTA_COM_SESSAO
import static fixtures.VotoTemplate.VOTO_PAUTA
import static fixtures.VotoTemplate.VOTO_PAUTA_SEM_PAUTA

class VotoEntityParaVotoMapperSpec extends Specification {

    VotoEntity votoEntityRequerida
    VotoEntity votoEntitySemSessaoRequerida
    VotoEntity votoEntitySemPautaRequerida
    List<VotoEntity> votoEntityListRequerida

    Voto votoMock
    Voto votoSemSessaoMock
    Voto votoSemPautaMock
    List<Voto> votoListMock

    def setup() {
        loadTemplates("fixtures")

        votoEntityRequerida = Fixture.from(VotoEntity).gimme(VOTO_ENTITY_PAUTA_SALVA)
        votoEntitySemSessaoRequerida = Fixture.from(VotoEntity).gimme(VOTO_ENTITY_PAUTA_SEM_SESSAO)
        votoEntitySemPautaRequerida = Fixture.from(VotoEntity).gimme(VOTO_ENTITY_PAUTA_SEM_PAUTA)
        votoEntityListRequerida = List.of(Fixture.from(VotoEntity).gimme(VOTO_ENTITY_PAUTA_SALVA))

        votoMock = Fixture.from(Voto).gimme(VOTO_PAUTA_COM_SESSAO)
        votoSemSessaoMock = Fixture.from(Voto).gimme(VOTO_PAUTA)
        votoSemPautaMock = Fixture.from(Voto).gimme(VOTO_PAUTA_SEM_PAUTA)
        votoListMock = List.of(Fixture.from(Voto).gimme(VOTO_PAUTA_COM_SESSAO))
    }

    def "Deveria converter um objeto VotoEntity valido em um objeto Voto"() {
        given: "Um objeto VotoEntity de entrada valido"
        votoEntityRequerida

        when: "quando o metodo map do Voto for invocado"
        Voto votoResultado = VotoEntityParaVotoMapper.INSTANCE.map(votoEntityRequerida)

        then: "o objeto Voto deve ser válido com todos os campos válidos"
        votoResultado == votoMock
    }

    def "Deveria converter um objeto VotoEntity sem sessao valido em um objeto Voto sem sessao"() {
        given: "Um objeto VotoEntity de entrada valido"
        votoEntitySemSessaoRequerida

        when: "quando o metodo map do Voto for invocado"
        Voto votoResultado = VotoEntityParaVotoMapper.INSTANCE.map(votoEntitySemSessaoRequerida)

        then: "o objeto Voto deve ser válido sem sessao"
        votoResultado == votoSemSessaoMock
    }

    def "Deveria converter um objeto VotoEntity sem pauta valido em um objeto Voto sem pauta"() {
        given: "Um objeto VotoEntity de entrada valido"
        votoEntitySemPautaRequerida

        when: "quando o metodo map do Voto for invocado"
        Voto votoResultado = VotoEntityParaVotoMapper.INSTANCE.map(votoEntitySemPautaRequerida)

        then: "o objeto Voto deve ser válido sem pauta"
        votoResultado == votoSemPautaMock
    }

    def "Deveria converter um objeto VotoEntity nulo em um objeto Voto nulo"() {
        given: "Um objeto VotoEntity de entrada nulo"
        votoEntityRequerida = null

        when: "quando o metodo map do Voto for invocado"
        Voto votoResultado = VotoEntityParaVotoMapper.INSTANCE.map(votoEntityRequerida)

        then: "o objeto Voto deve ser nulo"
        votoResultado == null
    }

    def "Deveria converter uma lista de VotoEntity valido em uma lista de Voto"() {
        given: "Um objeto VotoEntity de entrada valido"
        votoEntityListRequerida

        when: "quando o metodo map do Voto for invocado"
        List<Voto> votoListResultado = VotoEntityParaVotoMapper.INSTANCE.mapList(votoEntityListRequerida)

        then: "o objeto Voto deve ser válido com todos os campos vazios"
        votoListResultado == votoListMock
    }

    def "Deveria converter uma lista vazia de VotoEntity valido em uma lista vazia de Voto"() {
        given: "Um objeto VotoEntity de entrada valido"
        votoEntityListRequerida = Collections.emptyList()

        when: "quando o metodo map do Voto for invocado"
        List<Voto> votoListResultado = VotoEntityParaVotoMapper.INSTANCE.mapList(votoEntityListRequerida)

        then: "o objeto Voto deve ser válido com todos os campos válidos"
        votoListResultado == Collections.emptyList()
    }

    def "Deveria converter uma lista nula de VotoEntity valido em uma lista nula de Voto"() {
        given: "Um objeto VotoEntity de entrada valido"
        votoEntityListRequerida = null

        when: "quando o metodo map do Voto for invocado"
        List<Voto> votoListResultado = VotoEntityParaVotoMapper.INSTANCE.mapList(votoEntityListRequerida)

        then: "o objeto Voto deve ser nulo"
        votoListResultado == null
    }
}
