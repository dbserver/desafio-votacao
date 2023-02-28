package integracao

import br.com.six2six.fixturefactory.Fixture
import com.dbserver.desafio.votacao.endpoint.IniciarPautaController
import com.dbserver.desafio.votacao.endpoint.dto.PautaDTO
import com.dbserver.desafio.votacao.endpoint.dto.PautaDuracaoDTO
import com.dbserver.desafio.votacao.endpoint.dto.PautaSessaoDTO
import com.dbserver.desafio.votacao.usecase.domain.Pauta
import com.dbserver.desafio.votacao.usecase.pauta.IniciarPautaUsecase
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
import static fixtures.PautaDuracaoDtoTemplate.PAUTA_DURACAO_DTO_OBRA
import static fixtures.PautaSessaoDtoTemplate.PAUTA_SESSAO_DTO_OBRA_SALVA
import static fixtures.PautaTemplate.PAUTA_OBRA_COM_SESSAO
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post as postMockMvc

@SpringBootTest(classes = IniciarPautaController.class)
@AutoConfigureMockMvc
@EnableWebMvc
class IniciarPautaControllerIntegracaoSpec extends Specification {

    String urlBase = "/iniciar-pauta"

    @Autowired
    MockMvc mockMvc

    @SpringBean
    IniciarPautaUsecase iniciarPautaUsecase = Mock()

    PautaDTO pautaDTORequisicao

    PautaDuracaoDTO pautaDuracaoRequerida
    Pauta pautaMock

    def setup() {
        loadTemplates("fixtures")

        pautaDuracaoRequerida = Fixture.from(PautaDuracaoDTO).gimme(PAUTA_DURACAO_DTO_OBRA)
        pautaMock = Fixture.from(Pauta).gimme(PAUTA_OBRA_COM_SESSAO)
    }

    def "Deveria validar o fluxo de uma request para iniciar a pauta com código HTTP de retorno igual a 200 e uma resposta válida"() {
        given: "Dado que as chamadas para iniciar a pauta retornem OK"
        def pautaContent = new Gson().toJson(pautaDuracaoRequerida)

        and: "chamado o execute dosalvarPautaUsecase"
        1 * iniciarPautaUsecase.execute(pautaDuracaoRequerida.idPauta, pautaDuracaoRequerida.duracaoSessao) >> pautaMock

        when: "O iniciar a pauta for chamado"
        MvcResult mvcResult = mockMvc.perform(postMockMvc(urlBase)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(pautaContent))
                .andReturn()

        then: "Deve retornar status 200"
        mvcResult.getResponse().getStatus() == HttpStatus.OK.value()

        and: "Deve retornar uma pauta valida"
        def jsonNode = new ObjectMapper()
                .readTree(mvcResult.response.getContentAsString(StandardCharsets.UTF_8))

        PautaSessaoDTO pautaSessaoDTOMock = Fixture.from(PautaSessaoDTO).gimme(PAUTA_SESSAO_DTO_OBRA_SALVA)

        verifyAll(jsonNode) {
            it.get("idPauta").asInt() == pautaSessaoDTOMock.idPauta
            it.get("nomePauta").asText() == pautaSessaoDTOMock.nomePauta
            it.get("descricaoPauta").asText() == pautaSessaoDTOMock.descricaoPauta

            verifyAll(it.get("sessao")) {
                def sessaoMock = pautaSessaoDTOMock.sessao

                it.get("duracaoEmMunitos").asInt() == sessaoMock.duracaoEmMunitos
            }
        }
    }
}
