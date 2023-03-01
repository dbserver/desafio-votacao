package com.dbserver.desafio.votacao.endpoint.mapper

import br.com.six2six.fixturefactory.Fixture
import com.dbserver.desafio.votacao.endpoint.dto.VotosPautaDTO
import com.dbserver.desafio.votacao.usecase.domain.VotosPauta
import spock.lang.Specification

import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates
import static fixtures.VotosPautaDtoTemplate.VOTOS_PAUTA_DTO_PAUTA
import static fixtures.VotosPautaTemplate.VOTOS_PAUTA_PAUTA

class VotosPautaParaVotosPautaDTOMapperSpec extends Specification {

    VotosPautaDTO votosPautaDTOMock
    VotosPauta votosPautaRequerida

    def setup() {
        loadTemplates("fixtures")

        votosPautaDTOMock = Fixture.from(VotosPautaDTO).gimme(VOTOS_PAUTA_DTO_PAUTA)

        votosPautaRequerida = Fixture.from(VotosPauta).gimme(VOTOS_PAUTA_PAUTA)
    }

    def "Deveria converter um objeto votosPauta valido em um objeto votosPauta"() {
        given: "Um objeto votosPauta de entrada valido"
        votosPautaRequerida

        when: "quando o metodo map do VotosPautaParaVotosPautaDTOMapper for invocado"
        VotosPautaDTO votosPautaDTOResultado = VotosPautaParaVotosPautaDTOMapper.INSTANCE.map(votosPautaRequerida)

        then: "o objeto votosPautaDTO deve ser válido com todos os campos válidos"
        votosPautaDTOResultado == votosPautaDTOMock
    }

    def "Deveria converter um objeto votosPauta nulo em um objeto votosPautaDTO nulo"() {
        given: "Um objeto votosPauta de entrada nulo"
        votosPautaRequerida = null

        when: "quando o metodo map do votosPauta for invocado"
        VotosPautaDTO votosPautaDTOResultado = VotosPautaParaVotosPautaDTOMapper.INSTANCE.map(votosPautaRequerida)

        then: "o objeto votosPauta deve ser nulo"
        votosPautaDTOResultado == null
    }
}