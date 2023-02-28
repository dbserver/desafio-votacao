package integracao

import br.com.six2six.fixturefactory.Fixture
import com.dbserver.desafio.votacao.endpoint.CadastrarPautaController
import com.dbserver.desafio.votacao.endpoint.dto.PautaDTO
import com.dbserver.desafio.votacao.usecase.domain.Pauta
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

import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates
import static fixtures.PautaDtoTemplate.PAUTA_DTO_OBRA
import static fixtures.PautaDtoTemplate.PAUTA_DTO_OBRA_SALVA
import static fixtures.PautaTemplate.PAUTA_OBRA
import static fixtures.PautaTemplate.PAUTA_OBRA_CADASTRADA
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post as postMockMvc

@SpringBootTest(classes = CadastrarPautaController.class)
@AutoConfigureMockMvc
@EnableWebMvc
class CadastrarPautaControllerIntegrationSpec extends Specification {

    String urlBase = "/cadastrar"

    @Autowired
    MockMvc mockMvc

    @SpringBean
    SalvarPautaUsecase salvarPautaUsecase = Mock()

    PautaDTO pautaDTORequisicao

    Pauta pautaRequerida
    Pauta pautaMock

    def setup() {
        loadTemplates("fixtures")

        pautaDTORequisicao = Fixture.from(PautaDTO).gimme(PAUTA_DTO_OBRA)

        pautaRequerida = Fixture.from(Pauta).gimme(PAUTA_OBRA)
        pautaMock = Fixture.from(Pauta).gimme(PAUTA_OBRA_CADASTRADA)
    }

    def "Deveria validar o fluxo de uma request para cadastro de pauta com código HTTP de retorno igual a 200 e uma resposta válida"() {
        given: "Dado que as chamadas para cadastro de pauta retornem OK"
        def pautaContent = new Gson().toJson(pautaDTORequisicao)

        and: "chamado o execute dosalvarPautaUsecase"
        1 * salvarPautaUsecase.execute(pautaRequerida) >> pautaMock

        when: "O cadastro da pauta for chamado"
        MvcResult mvcResult = mockMvc.perform(postMockMvc(urlBase)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(pautaContent))
                .andReturn()

        then: "Deve retornar status 200"
        mvcResult.getResponse().getStatus() == HttpStatus.OK.value()

        and: "Deve retornar uma pauta valida"
        def jsonNode = new ObjectMapper()
                .readTree(mvcResult.response.getContentAsString(StandardCharsets.UTF_8))

        PautaDTO pautaDTOMock = Fixture.from(PautaDTO).gimme(PAUTA_DTO_OBRA_SALVA)

        verifyAll(jsonNode) {
            it.get("idPauta").asInt() == pautaDTOMock.idPauta
            it.get("nome").asText() == pautaDTOMock.nome
            it.get("descricao").asText() == pautaDTOMock.descricao
        }
    }
}
