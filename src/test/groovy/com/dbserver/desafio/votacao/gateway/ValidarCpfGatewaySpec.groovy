package com.dbserver.desafio.votacao.gateway

import br.com.six2six.fixturefactory.Fixture
import com.dbserver.desafio.votacao.gateway.client.ValidaCpfClient
import com.dbserver.desafio.votacao.gateway.dto.CpfStatusRespostaDTO
import com.dbserver.desafio.votacao.gateway.impl.ValidarCpfGatewayImpl
import com.dbserver.desafio.votacao.usecase.domain.Voto
import org.springframework.http.ResponseEntity
import spock.lang.Specification

import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates
import static fixtures.CpfStatusRespostaDTOTemplate.CPF_STATUS_DTO
import static fixtures.VotoTemplate.VOTO_PAUTA

class ValidarCpfGatewaySpec extends Specification {

    ValidaCpfClient validaCpfClient = Mock()

    ValidarCpfGateway validarCpfGateway

    Voto votoRequerido
    CpfStatusRespostaDTO cpfStatusRespostaDTOMock
    ResponseEntity<CpfStatusRespostaDTO> cpfStatusDTOResponseEntity

    def setup() {
        loadTemplates("fixtures")

        validarCpfGateway = new ValidarCpfGatewayImpl(validaCpfClient)

        votoRequerido = Fixture.from(Voto).gimme(VOTO_PAUTA)
        cpfStatusRespostaDTOMock = Fixture.from(CpfStatusRespostaDTO).gimme(CPF_STATUS_DTO)
        cpfStatusDTOResponseEntity = ResponseEntity<CpfStatusRespostaDTO>.ok(cpfStatusRespostaDTOMock)
    }

    def "Deveria validar uma chamada com sucesso ao metodo execute da classe ValidarCpfGatewayImpl "() {
        given: "Um cpfAssociado valido"
        votoRequerido

        and: "uma chamada válida ao método validaClient de validaCpfClient"
        1 * validaCpfClient.validaClient(votoRequerido.cpfAssociado) >> cpfStatusDTOResponseEntity

        when: "o método execute do SalvarPautaUsecase for invocado"
        CpfStatusRespostaDTO cpfStatusRespostaDTO = validarCpfGateway.execute(votoRequerido.cpfAssociado)

        then: "o objeto pauta deve ser válido com todos os campos válidos"
        cpfStatusRespostaDTO
        verifyAll(cpfStatusRespostaDTO) {
            statusCpf == cpfStatusRespostaDTOMock.statusCpf
        }
    }

    def "Deveria validar uma chamada que gera uma exception ao metodo execute da classe ValidarCpfGatewayImpl"() {
        given: "Um cpfAssociado valido"
        votoRequerido

        and: "uma chamada que gera exception ao método validaClient de validaCpfClient"
        1 * validaCpfClient.validaClient(votoRequerido.cpfAssociado) >> { throw new Exception() }

        when: "o método execute do SalvarPautaUsecase for invocado"
        validarCpfGateway.execute(votoRequerido.cpfAssociado)

        then: "o retorno do voto da pauta deve gerar uma exception"
        thrown(Exception)
    }
}
