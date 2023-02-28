package com.dbserver.desafio.valida.cpf.endpoint

import br.com.six2six.fixturefactory.Fixture
import com.dbserver.desafio.valida.cpf.endpoint.dto.PautaDuracaoDTO
import com.dbserver.desafio.valida.cpf.endpoint.dto.PautaSessaoDTO
import org.springframework.http.ResponseEntity
import spock.lang.Specification

import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates
import static fixtures.PautaTemplate.PAUTA_OBRA_CADASTRADA
import static fixtures.PautaTemplate.PAUTA_OBRA_COM_SESSAO
import static fixtures.PautaTemplate.PAUTA_VAZIO
import static fixtures.PautaDuracaoDtoTemplate.PAUTA_DURACAO_DTO_OBRA
import static fixtures.PautaDuracaoDtoTemplate.PAUTA_DURACAO_DTO_VAZIO
import static fixtures.PautaSessaoDtoTemplate.PAUTA_SESSAO_DTO_OBRA_SALVA
import static fixtures.PautaSessaoDtoTemplate.PAUTA_SESSAO_DTO_VAZIO

class IniciarPautaControllerSpec extends Specification {

    com.dbserver.desafio.valida.cpf.usecase.pauta.IniciarPautaUsecase iniciarPautaUsecase = Mock()

    IniciarPautaController iniciarPautaController

    PautaDuracaoDTO pautaDuracaoDTORequerida
    PautaDuracaoDTO pautaDuracaoDTOVazioRequerida

    com.dbserver.desafio.valida.cpf.usecase.domain.Pauta pautaMock
    com.dbserver.desafio.valida.cpf.usecase.domain.Pauta pautaRequerida
    com.dbserver.desafio.valida.cpf.usecase.domain.Pauta pautaVazioMock

    PautaSessaoDTO pautaSessaoDTOMock
    PautaSessaoDTO pautaSessaoVazioDTOMock

    def setup() {
        loadTemplates("fixtures")

        iniciarPautaController = new IniciarPautaController(iniciarPautaUsecase)

        pautaDuracaoDTORequerida = Fixture.from(PautaDuracaoDTO).gimme(PAUTA_DURACAO_DTO_OBRA)
        pautaDuracaoDTOVazioRequerida = Fixture.from(PautaDuracaoDTO).gimme(PAUTA_DURACAO_DTO_VAZIO)

        pautaMock = Fixture.from(com.dbserver.desafio.valida.cpf.usecase.domain.Pauta).gimme(PAUTA_OBRA_COM_SESSAO)
        pautaRequerida = Fixture.from(com.dbserver.desafio.valida.cpf.usecase.domain.Pauta).gimme(PAUTA_OBRA_CADASTRADA)
        pautaVazioMock = Fixture.from(com.dbserver.desafio.valida.cpf.usecase.domain.Pauta).gimme(PAUTA_VAZIO)

        pautaSessaoDTOMock = Fixture.from(PautaSessaoDTO).gimme(PAUTA_SESSAO_DTO_OBRA_SALVA)
        pautaSessaoVazioDTOMock = Fixture.from(PautaSessaoDTO).gimme(PAUTA_SESSAO_DTO_VAZIO)
    }

    def "Deveria validar uma chamada com sucesso ao metodo execute da classe IniciarPautaController "() {
        given: "Um objeto PautaDuracaoDTO de entrada valido"
        pautaDuracaoDTORequerida

        and: "uma chamada válida ao método execute de iniciarPautaUsecase"
        1 * iniciarPautaUsecase.execute(pautaDuracaoDTORequerida.idPauta,pautaDuracaoDTORequerida.duracaoSessao) >> pautaMock

        when: "o método iniciarPauta do IniciarPautaController for invocado"
        def pautaResultado = iniciarPautaController.iniciarPauta(pautaDuracaoDTORequerida)

        then: "o objeto pauta deve ser válido com todos os campos válidos"
        pautaResultado == ResponseEntity.ok(pautaSessaoDTOMock)
    }

    def "Deveria validar uma chamada com parametro vazio ao metodo execute da classe cadastrarPautaController "() {
        given: "Um objeto PautaDuracaoDTO de entrada vazio"
        pautaDuracaoDTOVazioRequerida

        and: "uma chamada com parametro vazio ao método execute de iniciarPautaUsecase"
        1 * iniciarPautaUsecase.execute(pautaDuracaoDTOVazioRequerida.idPauta,
                                        pautaDuracaoDTOVazioRequerida.duracaoSessao) >> pautaVazioMock

        when: "o método iniciarPauta do IniciarPautaController for invocado"
        def pautaResultado = iniciarPautaController.iniciarPauta(pautaDuracaoDTOVazioRequerida)

        then: "o objeto pauta deve ser válido com todos os campos vazios"
        pautaResultado == ResponseEntity.ok(pautaSessaoVazioDTOMock)
    }

    def "Deveria validar uma chamada que gera uma exception ao metodo execute da classe cadastrarPautaController"() {
        given: "Um objeto PautaDuracaoDTO de entrada valido"
        pautaDuracaoDTORequerida

        and: "uma chamada que gera exception ao método execute de iniciarPautaUsecase"
        1 * iniciarPautaUsecase.execute(pautaDuracaoDTORequerida.idPauta,pautaDuracaoDTORequerida.duracaoSessao) >> { throw new Exception() }

        when: "o método iniciarPauta do IniciarPautaController for invocado"
        iniciarPautaController.iniciarPauta(pautaDuracaoDTORequerida)

        then: "o retorno do cadastro da pauta deve gerar uma exception"
        thrown(Exception)
    }
}