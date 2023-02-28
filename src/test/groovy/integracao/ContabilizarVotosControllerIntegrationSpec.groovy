package integracao

import br.com.six2six.fixturefactory.Fixture
import com.dbserver.desafio.votacao.endpoint.CadastrarPautaController
import com.dbserver.desafio.votacao.endpoint.ContabilizarVotosController
import com.dbserver.desafio.votacao.endpoint.dto.PautaDTO
import com.dbserver.desafio.votacao.endpoint.dto.VotosPautaDTO
import com.dbserver.desafio.votacao.usecase.assembleia.ContabilizarVotosUsecase
import com.dbserver.desafio.votacao.usecase.domain.Pauta
import com.dbserver.desafio.votacao.usecase.domain.VotosPauta
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
import static fixtures.PautaDtoTemplate.PAUTA_DTO_OBRA
import static fixtures.VotosPautaDtoTemplate.VOTOS_PAUTA_DTO_PAUTA_COM_SESSAO
import static fixtures.PautaTemplate.PAUTA_OBRA
import static fixtures.VotosPautaTemplate.VOTOS_PAUTA_PAUTA_COM_SESSAO

@SpringBootTest(classes = ContabilizarVotosController.class)
@AutoConfigureMockMvc
@EnableWebMvc
class ContabilizarVotosControllerIntegrationSpec extends Specification {

    String urlBase = "/contabilizar-votos"

    @Autowired
    MockMvc mockMvc

    @SpringBean
    ContabilizarVotosUsecase contabilizarVotosUsecase = Mock()

    PautaDTO pautaDTORequisicao

    Pauta pautaRequerida
    VotosPauta votosPautaMock

    def setup() {
        loadTemplates("fixtures")

        pautaDTORequisicao = Fixture.from(PautaDTO).gimme(PAUTA_DTO_OBRA)

        pautaRequerida = Fixture.from(Pauta).gimme(PAUTA_OBRA)
        votosPautaMock = Fixture.from(VotosPauta).gimme(VOTOS_PAUTA_PAUTA_COM_SESSAO)
    }

    def "Deveria validar o fluxo de uma request para contabilizar votos da pauta com código HTTP de retorno igual a 200 e uma resposta válida"() {
        given: "Dado que as chamadas para contabilizar votos da pauta retornem OK"
        def pautaContent = new Gson().toJson(pautaRequerida)

        and: "chamado o execute do contabilizarVotosUsecase"
        1 * contabilizarVotosUsecase.execute(pautaRequerida.idPauta) >> votosPautaMock

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

        VotosPautaDTO votosPautaDTOMock = Fixture.from(VotosPautaDTO).gimme(VOTOS_PAUTA_DTO_PAUTA_COM_SESSAO)

        verifyAll(jsonNode) {
            it.get("totalVotosSim").asInt() == votosPautaDTOMock.totalVotosSim
            it.get("totalVotosNao").asInt() == votosPautaDTOMock.totalVotosNao

            verifyAll (it.get("pauta")){
                def pautaMock = votosPautaDTOMock.pauta

                it.get("idPauta").asInt() == pautaMock.idPauta
                it.get("nome").asText() == pautaMock.nome
                it.get("descricao").asText() == pautaMock.descricao
            }
        }
    }
}
