package com.dbserver.desafio.votacao.endpoint

import br.com.six2six.fixturefactory.Fixture
import com.dbserver.desafio.votacao.endpoint.dto.PautaDTO
import com.dbserver.desafio.votacao.endpoint.dto.VotosPautaDTO
import com.dbserver.desafio.votacao.usecase.assembleia.ContabilizarVotosUsecase
import com.dbserver.desafio.votacao.usecase.domain.VotosPauta
import org.springframework.http.ResponseEntity
import spock.lang.Specification

import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates
import static fixtures.PautaDtoTemplate.PAUTA_DTO_OBRA_SALVA
import static fixtures.PautaDtoTemplate.PAUTA_DTO_VAZIO
import static fixtures.VotosPautaDtoTemplate.VOTOS_PAUTA_DTO_PAUTA_COM_SESSAO
import static fixtures.VotosPautaDtoTemplate.VOTOS_PAUTA_DTO_PAUTA_VAZIO
import static fixtures.VotosPautaTemplate.VOTOS_PAUTA_PAUTA_COM_SESSAO
import static fixtures.VotosPautaTemplate.VOTOS_PAUTA_PAUTA_VAZIO

class ContabilizarVotosControllerSpec extends Specification {

    ContabilizarVotosUsecase contabilizarVotosUsecase = Mock()

    ContabilizarVotosController contabilizarVotosController

    PautaDTO pautaDTORequerida
    PautaDTO pautaDTOVazioRequerida

    VotosPauta votosPautaMock
    VotosPauta votosPautaVazioMock

    VotosPautaDTO votosPautaDTOMock
    VotosPautaDTO votosPautaDTOVazioMock

    def setup() {
        loadTemplates("fixtures")

        contabilizarVotosController = new ContabilizarVotosController(contabilizarVotosUsecase)

        pautaDTORequerida = Fixture.from(PautaDTO).gimme(PAUTA_DTO_OBRA_SALVA)
        pautaDTOVazioRequerida = Fixture.from(PautaDTO).gimme(PAUTA_DTO_VAZIO)

        votosPautaDTOMock = Fixture.from(VotosPautaDTO).gimme(VOTOS_PAUTA_DTO_PAUTA_COM_SESSAO)
        votosPautaDTOVazioMock = Fixture.from(VotosPautaDTO).gimme(VOTOS_PAUTA_DTO_PAUTA_VAZIO)

        votosPautaMock = Fixture.from(VotosPauta).gimme(VOTOS_PAUTA_PAUTA_COM_SESSAO)
        votosPautaVazioMock = Fixture.from(VotosPauta).gimme(VOTOS_PAUTA_PAUTA_VAZIO)
    }

    def "Deveria validar uma chamada com sucesso ao metodo execute da classe contabilizarVotosController "() {
        given: "Um objeto PautaDTO de entrada valido"
        pautaDTORequerida

        and: "uma chamada válida ao método execute de contabilizarVotosUsecase"
        1 * contabilizarVotosUsecase.execute(pautaDTORequerida.idPauta) >> votosPautaMock

        when: "o método contabilizarVotos do contabilizarVotosController for invocado"
        def votosPautaResultado = contabilizarVotosController.contabilizarVotos(pautaDTORequerida)

        then: "o objeto VotosPauta deve ser válido com todos os campos válidos"
        votosPautaResultado == ResponseEntity.ok(votosPautaDTOMock)
    }

    def "Deveria validar uma chamada com parametro vazio ao metodo execute da classe ReceberVotoController "() {
        given: "Um objeto PautaDTO de entrada vazio"
        pautaDTOVazioRequerida

        and: "uma chamada válida ao método execute de ContabilizarVotosUsecase"
        1 * contabilizarVotosUsecase.execute(pautaDTOVazioRequerida.idPauta) >> votosPautaVazioMock

        when: "o método receberVoto do ContabilizarVotosController for invocado"
        def votosPautaResultado = contabilizarVotosController.contabilizarVotos(pautaDTOVazioRequerida)

        then: "o objeto Voto deve ser válido com todos os campos válidos"
        votosPautaResultado == ResponseEntity.ok(votosPautaDTOVazioMock)
    }

    def "Deveria validar uma chamada que gera uma exception ao metodo execute da classe ContabilizarVotosController"() {
        given: "Um objeto PautaDTO de entrada valido"
        pautaDTORequerida

        and: "uma chamada válida ao método execute de contabilizarVotosUsecase"
        1 * contabilizarVotosUsecase.execute(pautaDTORequerida.idPauta) >> { throw new Exception() }

        when: "o método contabilizarVotos do ContabilizarVotosController for invocado"
        contabilizarVotosController.contabilizarVotos(pautaDTORequerida)

        then: "o retorno do cadastro da pauta deve gerar uma exception"
        thrown(Exception)
    }
}

