package com.dbserver.desafio.votacao.endpoint

import br.com.six2six.fixturefactory.Fixture
import com.dbserver.desafio.votacao.endpoint.dto.PautaDTO
import com.dbserver.desafio.votacao.usecase.pauta.SalvarPautaUsecase
import com.dbserver.desafio.votacao.usecase.domain.Pauta
import org.springframework.http.ResponseEntity
import spock.lang.Specification

import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates
import static fixtures.PautaDtoTemplate.PAUTA_DTO_OBRA
import static fixtures.PautaDtoTemplate.PAUTA_DTO_OBRA_CADASTRADA
import static fixtures.PautaDtoTemplate.PAUTA_DTO_VAZIO
import static fixtures.PautaTemplate.PAUTA_OBRA
import static fixtures.PautaTemplate.PAUTA_OBRA_CADASTRADA
import static fixtures.PautaTemplate.PAUTA_VAZIO

class CadastrarPautaControllerSpec extends Specification {

    SalvarPautaUsecase salvarPautaUsecase = Mock()

    CadastrarPautaController cadastrarPautaController

    PautaDTO pautaDTORequerida
    PautaDTO pautaDTOVazioRequerida
    PautaDTO pautaDTOMock

    Pauta pautaMock
    Pauta pautaRequerida
    Pauta pautaVazioRequerida

    def setup() {
        loadTemplates("fixtures")

        cadastrarPautaController = new CadastrarPautaController(salvarPautaUsecase)

        pautaDTORequerida = Fixture.from(PautaDTO).gimme(PAUTA_DTO_OBRA)
        pautaDTOVazioRequerida = Fixture.from(PautaDTO).gimme(PAUTA_DTO_VAZIO)
        pautaMock = Fixture.from(Pauta).gimme(PAUTA_OBRA)
        pautaDTOMock = Fixture.from(PautaDTO).gimme(PAUTA_DTO_OBRA_CADASTRADA)
        pautaRequerida = Fixture.from(Pauta).gimme(PAUTA_OBRA_CADASTRADA)
        pautaVazioRequerida = Fixture.from(Pauta).gimme(PAUTA_VAZIO)
    }

    def "Deveria validar uma chamada com sucesso ao metodo execute da classe cadastrarPautaController "() {
        given: "Um objeto PautaDTO de entrada valido"
        pautaDTORequerida

        and: "uma chamada válida ao método execute de salvarPautaUsecase"
        1 * salvarPautaUsecase.execute(pautaMock) >> pautaMock

        when: "o método cadastrarPauta do cadastrarPautaController for invocado"
        def pautaResultado = cadastrarPautaController.cadastrarPauta(pautaDTORequerida)

        then: "o objeto pauta deve ser válido com todos os campos válidos"
        pautaResultado == ResponseEntity.ok(pautaDTOMock)
    }

    def "Deveria validar uma chamada com parametro nulo ao metodo execute da classe cadastrarPautaController "() {
        given: "Um objeto PautaDTO de entrada nulo"
        pautaDTORequerida = null

        and: "uma chamada com parametro nulo ao método execute de salvarPautaUsecase"
        1 * salvarPautaUsecase.execute(null) >> null

        when: "o método cadastrarPauta do cadastrarPautaController for invocado"
        def pautaResultado = cadastrarPautaController.cadastrarPauta(pautaDTORequerida)

        then: "o objeto pauta deve ser nulo"
        pautaResultado == ResponseEntity.ok(null)
    }

    def "Deveria validar uma chamada com parametro vazio ao metodo execute da classe cadastrarPautaController "() {
        given: "Um objeto PautaDTO de entrada vazio"
        pautaDTOVazioRequerida

        and: "uma chamada com parametro vazio ao método execute de salvarPautaUsecase"
        1 * salvarPautaUsecase.execute(pautaVazioRequerida) >> pautaVazioRequerida

        when: "o método cadastrarPauta do cadastrarPautaController for invocado"
        def pautaResultado = cadastrarPautaController.cadastrarPauta(pautaDTOVazioRequerida)

        then: "o objeto pauta deve ser válido com todos os campos vazios"
        pautaResultado == ResponseEntity.ok(pautaDTOVazioRequerida)
    }

    def "Deveria validar uma chamada que gera uma exception ao metodo execute da classe cadastrarPautaController"() {
        given: "Um objeto PautaDTO de entrada valido"
        pautaDTORequerida

        and: "uma chamada que gera exception ao método execute de salvarPautaUsecase"
        1 * salvarPautaUsecase.execute(pautaMock) >> { throw new Exception() }

        when: "o método cadastrarPauta do cadastrarPautaController for invocado"
        cadastrarPautaController.cadastrarPauta(pautaDTORequerida)

        then: "o retorno do cadastro da pauta deve gerar uma exception"
        thrown(Exception)
    }
}
