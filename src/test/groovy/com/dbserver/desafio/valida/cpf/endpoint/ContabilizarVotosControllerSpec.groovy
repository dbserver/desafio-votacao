package com.dbserver.desafio.valida.cpf.endpoint

import br.com.six2six.fixturefactory.Fixture
import com.dbserver.desafio.valida.cpf.endpoint.dto.VotosPautaDTO
import com.dbserver.desafio.valida.cpf.usecase.assembleia.ContabilizarVotosUsecase
import org.springframework.http.ResponseEntity
import spock.lang.Specification

import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates
import static fixtures.PautaIdDtoTemplate.PAUTA_ID_DTO_OBRA
import static fixtures.PautaIdDtoTemplate.PAUTA_ID_DTO_OBRA_VAZIA
import static fixtures.VotosPautaDtoTemplate.VOTOS_PAUTA_DTO_PAUTA_COM_SESSAO
import static fixtures.VotosPautaDtoTemplate.VOTOS_PAUTA_DTO_PAUTA_VAZIO
import static fixtures.VotosPautaTemplate.VOTOS_PAUTA_PAUTA_COM_SESSAO
import static fixtures.VotosPautaTemplate.VOTOS_PAUTA_PAUTA_VAZIO

class ContabilizarVotosControllerSpec extends Specification {

    ContabilizarVotosUsecase contabilizarVotosUsecase = Mock()

    ContabilizarVotosController contabilizarVotosController

    com.dbserver.desafio.valida.cpf.endpoint.dto.PautaIdDTO pautaIdDTORequerida
    com.dbserver.desafio.valida.cpf.endpoint.dto.PautaIdDTO pautaIdDTOVazioRequerida

    com.dbserver.desafio.valida.cpf.usecase.domain.VotosPauta votosPautaMock
    com.dbserver.desafio.valida.cpf.usecase.domain.VotosPauta votosPautaVazioMock

    VotosPautaDTO votosPautaDTOMock
    VotosPautaDTO votosPautaDTOVazioMock

    def setup() {
        loadTemplates("fixtures")

        contabilizarVotosController = new ContabilizarVotosController(contabilizarVotosUsecase)

        pautaIdDTORequerida = Fixture.from(com.dbserver.desafio.valida.cpf.endpoint.dto.PautaIdDTO).gimme(PAUTA_ID_DTO_OBRA)
        pautaIdDTOVazioRequerida = Fixture.from(com.dbserver.desafio.valida.cpf.endpoint.dto.PautaIdDTO).gimme(PAUTA_ID_DTO_OBRA_VAZIA)

        votosPautaDTOMock = Fixture.from(VotosPautaDTO).gimme(VOTOS_PAUTA_DTO_PAUTA_COM_SESSAO)
        votosPautaDTOVazioMock = Fixture.from(VotosPautaDTO).gimme(VOTOS_PAUTA_DTO_PAUTA_VAZIO)

        votosPautaMock = Fixture.from(com.dbserver.desafio.valida.cpf.usecase.domain.VotosPauta).gimme(VOTOS_PAUTA_PAUTA_COM_SESSAO)
        votosPautaVazioMock = Fixture.from(com.dbserver.desafio.valida.cpf.usecase.domain.VotosPauta).gimme(VOTOS_PAUTA_PAUTA_VAZIO)
    }

    def "Deveria validar uma chamada com sucesso ao metodo execute da classe contabilizarVotosController "() {
        given: "Um objeto PautaDTO de entrada valido"
        pautaIdDTORequerida

        and: "uma chamada válida ao método execute de contabilizarVotosUsecase"
        1 * contabilizarVotosUsecase.execute(pautaIdDTORequerida.idPauta) >> votosPautaMock

        when: "o método contabilizarVotos do contabilizarVotosController for invocado"
        def votosPautaResultado = contabilizarVotosController.contabilizarVotos(pautaIdDTORequerida)

        then: "o objeto VotosPauta deve ser válido com todos os campos válidos"
        votosPautaResultado == ResponseEntity.ok(votosPautaDTOMock)
    }

    def "Deveria validar uma chamada com parametro vazio ao metodo execute da classe ReceberVotoController "() {
        given: "Um objeto PautaDTO de entrada vazio"
        pautaIdDTOVazioRequerida

        and: "uma chamada válida ao método execute de ContabilizarVotosUsecase"
        1 * contabilizarVotosUsecase.execute(pautaIdDTOVazioRequerida.idPauta) >> votosPautaVazioMock

        when: "o método receberVoto do ContabilizarVotosController for invocado"
        def votosPautaResultado = contabilizarVotosController.contabilizarVotos(pautaIdDTOVazioRequerida)

        then: "o objeto Voto deve ser válido com todos os campos válidos"
        votosPautaResultado == ResponseEntity.ok(votosPautaDTOVazioMock)
    }

    def "Deveria validar uma chamada que gera uma exception ao metodo execute da classe ContabilizarVotosController"() {
        given: "Um objeto PautaDTO de entrada valido"
        pautaIdDTORequerida

        and: "uma chamada válida ao método execute de contabilizarVotosUsecase"
        1 * contabilizarVotosUsecase.execute(pautaIdDTORequerida.idPauta) >> { throw new Exception() }

        when: "o método contabilizarVotos do ContabilizarVotosController for invocado"
        contabilizarVotosController.contabilizarVotos(pautaIdDTORequerida)

        then: "o retorno do cadastro da pauta deve gerar uma exception"
        thrown(Exception)
    }
}

