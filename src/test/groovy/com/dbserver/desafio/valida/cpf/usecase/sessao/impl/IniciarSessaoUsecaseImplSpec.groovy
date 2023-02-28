package com.dbserver.desafio.valida.cpf.usecase.sessao.impl

import br.com.six2six.fixturefactory.Fixture
import spock.lang.Specification

import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset

import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates
import static fixtures.SessaoTemplate.SESSAO_VALIDA

class IniciarSessaoUsecaseImplSpec extends Specification {

    Integer DURACAOEMMINUTO = 1;

    Clock clock = Clock.fixed(Instant.parse("2023-02-25T19:30:00Z"), ZoneOffset.UTC)

    com.dbserver.desafio.valida.cpf.usecase.sessao.IniciarSessaoUsecase iniciarSessaoUsecase

    com.dbserver.desafio.valida.cpf.usecase.domain.Sessao sessaoMock

    def setup() {
        loadTemplates("fixtures")

        iniciarSessaoUsecase = new IniciarSessaoUsecaseImpl(clock)

        sessaoMock = Fixture.from(com.dbserver.desafio.valida.cpf.usecase.domain.Sessao).gimme(SESSAO_VALIDA)
    }

    def "Deveria validar uma chamada com sucesso ao metodo execute da classe IniciarSessaoUsecaseImpl "() {
        given: "Um objeto Pauta de entrada valido"
        sessaoMock

        when: "o método execute do iniciarSessaoUsecase for invocado"
        com.dbserver.desafio.valida.cpf.usecase.domain.Sessao sessaoResultado = iniciarSessaoUsecase.execute(sessaoMock.duracao)

        then: "o objeto sessao deve ser válido com todos os campos válidos"
        sessaoResultado
        verifyAll(sessaoResultado) {
            duracao == sessaoMock.getDuracao()
        }
    }

    def "Deveria validar uma chamada com parametro nulo ao metodo execute da classe IniciarSessaoUsecaseImpl "() {
        given: "Um objeto duracao de entrada nulo"
        def duracao = null

        when: "o método execute do iniciarSessaoUsecase for invocado"
        com.dbserver.desafio.valida.cpf.usecase.domain.Sessao sessaoResultado = iniciarSessaoUsecase.execute(duracao)

        then: "o objeto sessao deve ser válido com deuracao default"
        sessaoResultado
        verifyAll(sessaoResultado) {
            sessaoResultado.duracao == DURACAOEMMINUTO
        }
    }
}
