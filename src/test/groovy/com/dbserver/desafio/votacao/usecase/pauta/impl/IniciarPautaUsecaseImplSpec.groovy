package com.dbserver.desafio.votacao.usecase.pauta.impl

import br.com.six2six.fixturefactory.Fixture
import com.dbserver.desafio.votacao.endpoint.dto.PautaDuracaoDTO
import com.dbserver.desafio.votacao.exception.PautaInexistenteException
import com.dbserver.desafio.votacao.repository.PautaRepository
import com.dbserver.desafio.votacao.repository.entity.PautaEntity
import com.dbserver.desafio.votacao.usecase.pauta.IniciarPautaUsecase
import com.dbserver.desafio.votacao.usecase.pauta.SalvarPautaUsecase
import com.dbserver.desafio.votacao.usecase.domain.Pauta
import com.dbserver.desafio.votacao.usecase.domain.Sessao
import com.dbserver.desafio.votacao.usecase.sessao.IniciarSessaoUsecase
import spock.lang.Specification

import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates
import static fixtures.PautaDuracaoDtoTemplate.PAUTA_DURACAO_DTO_OBRA
import static fixtures.PautaDuracaoDtoTemplate.PAUTA_DURACAO_DTO_VAZIO
import static fixtures.PautaEntityTemplate.PAUTA_ENTITY_OBRA_COM_SESSAO
import static fixtures.PautaTemplate.PAUTA_OBRA_COM_SESSAO
import static fixtures.SessaoTemplate.SESSAO_VALIDA

class IniciarPautaUsecaseImplSpec extends Specification {

    PautaRepository pautaRepository = Mock()

    IniciarSessaoUsecase iniciarSessaoUsecase = Mock()

    SalvarPautaUsecase salvarPautaUsecase = Mock()

    IniciarPautaUsecase iniciarPautaUsecase

    PautaDuracaoDTO pautaDuracaoDTORequerida
    PautaDuracaoDTO pautaDuracaoVazioDTORequerida
    PautaEntity pautaEntityRequerida
    Pauta pautaMock

    Sessao sessaoMock

    def setup() {
        loadTemplates("fixtures")

        iniciarPautaUsecase = new IniciarPautaUsecaseImpl(pautaRepository, iniciarSessaoUsecase, salvarPautaUsecase)

        pautaDuracaoDTORequerida = Fixture.from(PautaDuracaoDTO).gimme(PAUTA_DURACAO_DTO_OBRA)
        pautaDuracaoVazioDTORequerida = Fixture.from(PautaDuracaoDTO).gimme(PAUTA_DURACAO_DTO_VAZIO)
        pautaEntityRequerida = Fixture.from(PautaEntity).gimme(PAUTA_ENTITY_OBRA_COM_SESSAO)
        pautaMock = Fixture.from(Pauta).gimme(PAUTA_OBRA_COM_SESSAO)

        sessaoMock = Fixture.from(Sessao).gimme(SESSAO_VALIDA)
    }

    def "Deveria validar uma chamada com sucesso ao metodo execute da classe IniciarPautaUsecaseImpl "() {
        given: "Um objeto Pauta de entrada valido"
        pautaDuracaoDTORequerida

        and: "uma chamada válida ao método save do pautaRepository"
        1 * pautaRepository.findById(pautaDuracaoDTORequerida.idPauta) >> Optional.of(pautaEntityRequerida)

        and: "uma chamada válida ao método execute do iniciarSessaoUsecase"
        1 * iniciarSessaoUsecase.execute(pautaDuracaoDTORequerida.duracaoSessao) >> sessaoMock

        and: "uma chamada válida ao método execute do salvarPautaUsecase"
        1 * salvarPautaUsecase.execute(pautaMock) >> pautaMock

        when: "o método execute do IniciarPautaUsecase for invocado"
        Pauta pautaResultado = iniciarPautaUsecase.execute(pautaDuracaoDTORequerida.idPauta,
                pautaDuracaoDTORequerida.duracaoSessao)

        then: "o objeto pauta deve ser válido com todos os campos válidos"
        pautaResultado == pautaMock
    }

    def "Deveria validar uma chamada com parametros nulos ao metodo execute da classe IniciarPautaUsecaseImpl gera PautaInexistenteException"() {
        given: "Um objeto Pauta de entrada com atributos nulos"
        pautaDuracaoVazioDTORequerida

        and: "uma chamada válida ao método save do pautaRepository"
        1 * pautaRepository.findById(pautaDuracaoVazioDTORequerida.idPauta) >> Optional.empty()

        and: "sem um chamada ao método execute do iniciarSessaoUsecase"
        0 * iniciarSessaoUsecase.execute(pautaDuracaoVazioDTORequerida.duracaoSessao)

        and: "sem uma chamada ao método execute do salvarPautaUsecase"
        0 * salvarPautaUsecase.execute(pautaMock) >> pautaMock

        when: "o método execute do IniciarPautaUsecase for invocado"
        iniciarPautaUsecase.execute(pautaDuracaoVazioDTORequerida.idPauta,
                pautaDuracaoVazioDTORequerida.duracaoSessao)

        then: "o objeto pauta deve gerar PautaInexistenteException"
        thrown(PautaInexistenteException)
    }

    def "Deveria validar uma chamada que gera uma exception ao metodo execute da classe IniciarPautaUsecaseImpl"() {
        given: "Um objeto Pauta de entrada valido"
        pautaDuracaoDTORequerida

        and: "uma chamada que gera exception ao método save de pautaRepository"
        1 * pautaRepository.findById(pautaDuracaoDTORequerida.idPauta) >> { throw new Exception() }

        when: "o método execute do SalvarPautaUsecase for invocado"
        iniciarPautaUsecase.execute(pautaDuracaoDTORequerida.idPauta,
                pautaDuracaoDTORequerida.duracaoSessao)

        then: "o retorno do cadastro da pauta deve gerar uma exception"
        thrown(Exception)
    }
}
