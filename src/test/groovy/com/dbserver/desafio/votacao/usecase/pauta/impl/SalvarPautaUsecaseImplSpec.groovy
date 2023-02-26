package com.dbserver.desafio.votacao.usecase.pauta.impl

import br.com.six2six.fixturefactory.Fixture
import com.dbserver.desafio.votacao.repository.PautaRepository
import com.dbserver.desafio.votacao.repository.entity.PautaEntity
import com.dbserver.desafio.votacao.usecase.domain.Pauta
import com.dbserver.desafio.votacao.usecase.pauta.SalvarPautaUsecase
import spock.lang.Specification

import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates
import static fixtures.PautaEntityTemplate.PAUTA_ENTITY_OBRA
import static fixtures.PautaTemplate.PAUTA_OBRA
import static fixtures.PautaTemplate.PAUTA_OBRA_CADASTRADA

class SalvarPautaUsecaseImplSpec extends Specification {

    PautaRepository pautaRepository = Mock()

    SalvarPautaUsecase cadastrarPautaUsecase

    Pauta pautaRequerida
    Pauta pautaMock
    PautaEntity pautaEntityRequerida

    def setup() {
        loadTemplates("fixtures")

        cadastrarPautaUsecase = new SalvarPautaUsecaseImpl(pautaRepository)

        pautaRequerida = Fixture.from(Pauta).gimme(PAUTA_OBRA)
        pautaMock = Fixture.from(Pauta).gimme(PAUTA_OBRA_CADASTRADA)
        pautaEntityRequerida = Fixture.from(PautaEntity).gimme(PAUTA_ENTITY_OBRA)
    }

    def "Deveria validar uma chamada com sucesso ao metodo execute da classe CadastrarPautaUsecaseImpl "() {
        given: "Um objeto Pauta de entrada valido"
        pautaRequerida

        and: "uma chamada válida ao método save de pautaRepository"
        1 * pautaRepository.save(pautaEntityRequerida) >> pautaEntityRequerida

        when: "o método execute do SalvarPautaUsecase for invocadoo"
        Pauta pautaResultado = cadastrarPautaUsecase.execute(pautaRequerida)

        then: "o objeto pauta deve ser válido com todos os campos válidos"
        pautaResultado
        verifyAll(pautaResultado) {
            nome == pautaMock.nome
            descricao == pautaMock.descricao
        }
    }

    def "Deveria validar uma chamada com pauta nulo ao metodo execute da classe CadastrarPautaUsecaseImpl"() {
        given: "Um objeto Pauta de entrada vazio"
        pautaRequerida = null

        and: "uma chamada com parametro nulo ao método save de pautaRepository"
        1 * pautaRepository.save(null) >> null

        when: "o método execute do SalvarPautaUsecase for invocadoo"
        Pauta pautaResultado = cadastrarPautaUsecase.execute(pautaRequerida)

        then: "o objeto pauta deve nulo"
        pautaResultado == null
    }

    def "Deveria validar uma chamada que gera uma exception ao metodo execute da classe CadastrarPautaUsecaseImpl"() {
        given: "Um objeto Pauta de entrada valido"
        pautaRequerida

        and: "uma chamada que gera exception ao método save de pautaRepository"
        1 * pautaRepository.save(pautaEntityRequerida) >> { throw new Exception() }

        when: "o método execute do SalvarPautaUsecase for invocado"
        cadastrarPautaUsecase.execute(pautaRequerida)

        then: "o retorno do cadastro da pauta deve gerar uma exception"
        thrown(Exception)
    }
}
