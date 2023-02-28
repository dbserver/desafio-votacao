package com.dbserver.desafio.valida.cpf.endpoint.mapper

import br.com.six2six.fixturefactory.Fixture
import com.dbserver.desafio.valida.cpf.endpoint.dto.VotoDTO
import spock.lang.Specification

import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates
import static fixtures.VotoDtoTemplate.VOTO_DTO_PAUTA
import static fixtures.VotoTemplate.VOTO_PAUTA

class VotoDtoParaVotoMapperSpec extends Specification {

    VotoDTO votoDTORequerida
    com.dbserver.desafio.valida.cpf.usecase.domain.Voto votoMock

    def setup() {
        loadTemplates("fixtures")

        votoDTORequerida = Fixture.from(VotoDTO).gimme(VOTO_DTO_PAUTA)

        votoMock = Fixture.from(com.dbserver.desafio.valida.cpf.usecase.domain.Voto).gimme(VOTO_PAUTA)
    }

    def "Deveria converter um objeto votoDTO valido em um objeto Voto"() {
        given: "Um objeto votoDTO de entrada valido"
        votoDTORequerida

        when: "quando o metodo map do Voto for invocado"
        com.dbserver.desafio.valida.cpf.usecase.domain.Voto votoResultado = VotoDtoParaVotoMapper.INSTANCE.map(votoDTORequerida)

        then: "o objeto Voto deve ser válido com todos os campos válidos"
        votoResultado
        verifyAll (votoResultado){
            voto == votoMock.voto
            pauta.idPauta == votoMock.pauta.idPauta
            cpfAssociado == votoMock.cpfAssociado
        }
    }

    def "Deveria converter um objeto votoDTO nulo em um objeto Voto nulo"() {
        given: "Um objeto votoDTO de entrada nulo"
        votoDTORequerida = null

        when: "quando o metodo map do Voto for invocado"
        com.dbserver.desafio.valida.cpf.usecase.domain.Voto votoResultado = VotoDtoParaVotoMapper.INSTANCE.map(votoDTORequerida)

        then: "o objeto Voto deve ser nulo"
        votoResultado == null
    }
}