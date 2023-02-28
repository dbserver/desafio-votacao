package com.dbserver.desafio.valida.cpf.endpoint.mapper

import br.com.six2six.fixturefactory.Fixture
import com.dbserver.desafio.valida.cpf.endpoint.dto.VotoDTO
import spock.lang.Specification

import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates
import static fixtures.VotoDtoTemplate.VOTO_DTO_PAUTA
import static fixtures.VotoTemplate.VOTO_PAUTA

class VotoParaVotoDTOMapperSpec extends Specification {

    VotoDTO votoDTOMock
    com.dbserver.desafio.valida.cpf.usecase.domain.Voto votoRequerida

    def setup() {
        loadTemplates("fixtures")

        votoDTOMock = Fixture.from(VotoDTO).gimme(VOTO_DTO_PAUTA)

        votoRequerida = Fixture.from(com.dbserver.desafio.valida.cpf.usecase.domain.Voto).gimme(VOTO_PAUTA)
    }

    def "Deveria converter um objeto voto valido em um objeto votoDTO"() {
        given: "Um objeto voto de entrada valido"
        votoRequerida

        when: "quando o metodo map do votoDTO for invocado"
        VotoDTO votoDTOResultadoo = VotoParaVotoDTOMapper.INSTANCE.map(votoRequerida)

        then: "o objeto votoDTO deve ser válido com todos os campos válidos"
        votoDTOResultadoo
        verifyAll (votoDTOResultadoo){
            voto == votoRequerida.voto
            idPauta == votoRequerida.pauta.idPauta
            cpfAssociado == votoDTOMock.cpfAssociado
        }
    }

    def "Deveria converter um objeto voto nulo em um objeto votoDTO nulo"() {
        given: "Um objeto voto de entrada nulo"
        votoRequerida = null

        when: "quando o metodo map do votoDTO for invocado"
        VotoDTO votoDTOResultado = VotoParaVotoDTOMapper.INSTANCE.map(votoRequerida)

        then: "o objeto votoDTO deve ser nulo"
        votoDTOResultado == null
    }
}