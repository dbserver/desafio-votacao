package integracao

import br.com.six2six.fixturefactory.Fixture
import com.dbserver.desafio.votacao.endpoint.CadastrarPautaController
import com.dbserver.desafio.votacao.endpoint.ReceberVotoController
import com.dbserver.desafio.votacao.endpoint.dto.PautaDTO
import com.dbserver.desafio.votacao.endpoint.dto.VotoDTO
import com.dbserver.desafio.votacao.usecase.assembleia.ReceberVotoUseCase
import com.dbserver.desafio.votacao.usecase.domain.Pauta
import com.dbserver.desafio.votacao.usecase.domain.Voto
import com.dbserver.desafio.votacao.usecase.pauta.SalvarPautaUsecase
import com.fasterxml.jackson.databind.ObjectMapper
import com.microsoft.applicationinsights.core.dependencies.google.gson.Gson
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import spock.lang.Specification

import java.nio.charset.StandardCharsets

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post as postMockMvc
import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates
import static fixtures.VotoDtoTemplate.VOTO_DTO_PAUTA
import static fixtures.VotoTemplate.VOTO_COM_ID_PAUTA
import static fixtures.VotoTemplate.VOTO_PAUTA_COM_SESSAO

@SpringBootTest(classes = ReceberVotoController.class)
@AutoConfigureMockMvc
@EnableWebMvc
class ReceberVotoControllerIntegracaoSpec extends Specification {

    String urlBase = "/receber-voto"

    @Autowired
    MockMvc mockMvc

    @SpringBean
    ReceberVotoUseCase receberVotoUseCase = Mock()

    VotoDTO votoDTOORequisicao

    Voto votoRequerida
    Voto votoMock

    def setup() {
        loadTemplates("fixtures")

        votoDTOORequisicao = Fixture.from(VotoDTO).gimme(VOTO_DTO_PAUTA)

        votoRequerida = Fixture.from(Voto).gimme(VOTO_COM_ID_PAUTA)
        votoMock = Fixture.from(Voto).gimme(VOTO_PAUTA_COM_SESSAO)
    }

    def "Deveria validar o fluxo de uma request para cadastro de pauta com código HTTP de retorno igual a 200 e uma resposta válida"() {
        given: "Dado que as chamadas para cadastro de pauta retornem OK"
        def votoDTOContent = "{\"cpfAssociado\":\"999.999.999-99\",\"idPauta\":19900,\"voto\":\"Sim\"}"

        and: "chamado o execute dosalvarPautaUsecase"
        1 * receberVotoUseCase.execute(votoRequerida) >> votoMock

        when: "O cadastro da pauta for chamado"
        MvcResult mvcResult = mockMvc.perform(postMockMvc(urlBase)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(votoDTOContent))
                .andReturn()

        then: "Deve retornar status 200"
        mvcResult.getResponse().getStatus() == HttpStatus.OK.value()

        and: "Deve retornar uma pauta valida"
        def jsonNode = new ObjectMapper()
                .readTree(mvcResult.response.getContentAsString(StandardCharsets.UTF_8))

        VotoDTO votoDTOMock = Fixture.from(VotoDTO).gimme(VOTO_DTO_PAUTA)

        verifyAll(jsonNode) {
            it.get("idPauta").asInt() == votoDTOMock.idPauta
            it.get("cpfAssociado").asText() == votoDTOMock.cpfAssociado
            it.get("voto").asText() == votoDTOMock.voto.toValue()
        }
    }
}
