package com.dbserver.desafio.valida.cpf.endpoint

import br.com.six2six.fixturefactory.Fixture
import com.dbserver.desafio.valida.cpf.endpoint.dto.VotoDTO
import com.dbserver.desafio.valida.cpf.usecase.assembleia.ReceberVotoUseCase
import org.springframework.http.ResponseEntity
import spock.lang.Specification

import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates
import static fixtures.VotoDtoTemplate.VOTO_DTO_PAUTA
import static fixtures.VotoDtoTemplate.VOTO_DTO_PAUTA_VAZIO
import static fixtures.VotoTemplate.VOTO_COM_ID_PAUTA
import static fixtures.VotoTemplate.VOTO_PAUTA
import static fixtures.VotoTemplate.VOTO_PAUTA_VAZIO

class ReceberVotoControllerSpec extends Specification {

    ReceberVotoUseCase receberVotoUseCase = Mock()

    ReceberVotoController receberVotoController

    VotoDTO votoDTOMock
    VotoDTO votoDTORequerida
    VotoDTO votoVazioDTORequerida

    com.dbserver.desafio.valida.cpf.usecase.domain.Voto votoMock
    com.dbserver.desafio.valida.cpf.usecase.domain.Voto votoRequerido
    com.dbserver.desafio.valida.cpf.usecase.domain.Voto votoVazioRequerido

    def setup() {
        loadTemplates("fixtures")

        receberVotoController = new ReceberVotoController(receberVotoUseCase)

        votoDTOMock = Fixture.from(VotoDTO).gimme(VOTO_DTO_PAUTA)
        votoDTORequerida = Fixture.from(VotoDTO).gimme(VOTO_DTO_PAUTA)
        votoVazioDTORequerida = Fixture.from(VotoDTO).gimme(VOTO_DTO_PAUTA_VAZIO)

        votoMock = Fixture.from(com.dbserver.desafio.valida.cpf.usecase.domain.Voto).gimme(VOTO_PAUTA)
        votoRequerido = Fixture.from(com.dbserver.desafio.valida.cpf.usecase.domain.Voto).gimme(VOTO_COM_ID_PAUTA)
        votoVazioRequerido = Fixture.from(com.dbserver.desafio.valida.cpf.usecase.domain.Voto).gimme(VOTO_PAUTA_VAZIO)
    }

    def "Deveria validar uma chamada com sucesso ao metodo execute da classe cadastrarPautaController "() {
        given: "Um objeto VotoDTO de entrada valido"
        votoDTORequerida

        and: "uma chamada válida ao método execute de receberVotoUseCase"
        1 * receberVotoUseCase.execute(votoRequerido) >> votoMock

        when: "o método receberVoto do receberVotoController for invocado"
        def pautaResultado = receberVotoController.receberVoto(votoDTORequerida)

        then: "o objeto Voto deve ser válido com todos os campos válidos"
        pautaResultado == ResponseEntity.ok(votoDTOMock)
    }

    def "Deveria validar uma chamada com parametro vazio ao metodo execute da classe cadastrarPautaController "() {
        given: "Um objeto PautaDuracaoDTO de entrada vazio"
        votoVazioDTORequerida

        and: "uma chamada válida ao método execute de receberVotoUseCase"
        1 * receberVotoUseCase.execute(votoVazioRequerido) >> votoVazioRequerido

        when: "o método receberVoto do receberVotoController for invocado"
        def pautaResultado = receberVotoController.receberVoto(votoVazioDTORequerida)

        then: "o objeto Voto deve ser válido com todos os campos válidos"
        pautaResultado == ResponseEntity.ok(votoVazioDTORequerida)
    }

    def "Deveria validar uma chamada que gera uma exception ao metodo execute da classe cadastrarPautaController"() {
        given: "Um objeto PautaDuracaoDTO de entrada valido"
        votoDTORequerida

        and: "uma chamada que gera exception ao método execute de receberVotoUseCase"
        1 * receberVotoUseCase.execute(votoRequerido) >> { throw new Exception() }

        when: "o método receberVoto do receberVotoController for invocado"
        receberVotoController.receberVoto(votoDTORequerida)

        then: "o retorno do cadastro da pauta deve gerar uma exception"
        thrown(Exception)
    }
}
