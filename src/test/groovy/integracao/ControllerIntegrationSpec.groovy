package integracao

import br.com.six2six.fixturefactory.Fixture
import com.dbserver.desafio.votacao.endpoint.CadastrarPautaController
import com.dbserver.desafio.votacao.endpoint.ContabilizarVotosController
import com.dbserver.desafio.votacao.endpoint.IniciarPautaController
import com.dbserver.desafio.votacao.endpoint.ReceberVotoController
import com.dbserver.desafio.votacao.endpoint.dto.PautaDTO
import com.dbserver.desafio.votacao.endpoint.dto.PautaDuracaoDTO
import com.dbserver.desafio.votacao.endpoint.dto.PautaSessaoDTO
import com.dbserver.desafio.votacao.endpoint.dto.VotoDTO
import com.dbserver.desafio.votacao.endpoint.dto.VotosPautaDTO
import com.dbserver.desafio.votacao.usecase.assembleia.ContabilizarVotosUsecase
import com.dbserver.desafio.votacao.usecase.assembleia.ReceberVotoUseCase
import com.dbserver.desafio.votacao.usecase.domain.Pauta
import com.dbserver.desafio.votacao.usecase.domain.Voto
import com.dbserver.desafio.votacao.usecase.domain.VotosPauta
import com.dbserver.desafio.votacao.usecase.pauta.IniciarPautaUsecase
import com.dbserver.desafio.votacao.usecase.pauta.SalvarPautaUsecase
import com.fasterxml.jackson.databind.ObjectMapper
import com.microsoft.applicationinsights.core.dependencies.google.gson.Gson
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import spock.lang.Specification

import java.nio.charset.StandardCharsets
import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset

import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates
import static fixtures.PautaDtoTemplate.PAUTA_DTO_OBRA
import static fixtures.PautaDtoTemplate.PAUTA_DTO_OBRA_SALVA
import static fixtures.PautaDuracaoDtoTemplate.PAUTA_DURACAO_DTO_OBRA
import static fixtures.PautaSessaoDtoTemplate.PAUTA_SESSAO_DTO_OBRA_SALVA
import static fixtures.PautaTemplate.PAUTA_OBRA
import static fixtures.PautaTemplate.PAUTA_OBRA_CADASTRADA
import static fixtures.PautaTemplate.PAUTA_OBRA_COM_SESSAO
import static fixtures.VotoDtoTemplate.VOTO_DTO_PAUTA
import static fixtures.VotoTemplate.VOTO_COM_ID_PAUTA
import static fixtures.VotoTemplate.VOTO_PAUTA_COM_SESSAO
import static fixtures.VotosPautaDtoTemplate.VOTOS_PAUTA_DTO_PAUTA_COM_SESSAO
import static fixtures.VotosPautaTemplate.VOTOS_PAUTA_PAUTA_COM_SESSAO
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post as postMockMvc

@SpringBootTest(classes = [
        {CadastrarPautaController.class},
        {ContabilizarVotosController.class},
        {IniciarPautaController.class},
        {ReceberVotoController.class}])
@AutoConfigureMockMvc
@EnableWebMvc
@ComponentScan(basePackages = [
        "com.dbserver.desafio.votacao.endpoint",
        "com.dbserver.desafio.votacao.usecase",
        "com.dbserver.desafio.votacao.repository"])
class ControllerIntegrationSpec extends Specification {

    @Autowired
    MockMvc mockMvc

    @SpringBean
    Clock clock = Clock.fixed(Instant.parse("2023-02-25T19:30:00Z"), ZoneOffset.UTC)

    @SpringBean
    SalvarPautaUsecase salvarPautaUsecase = Mock()

    @SpringBean
    ContabilizarVotosUsecase contabilizarVotosUsecase = Mock()

    @SpringBean
    IniciarPautaUsecase iniciarPautaUsecase = Mock()

    @SpringBean
    ReceberVotoUseCase receberVotoUseCase = Mock()

    PautaDTO pautaDTORequisicao

    Pauta pautaRequerida

    def setup() {
        loadTemplates("fixtures")

        pautaDTORequisicao = Fixture.from(PautaDTO).gimme(PAUTA_DTO_OBRA)

        pautaRequerida = Fixture.from(Pauta).gimme(PAUTA_OBRA)
    }

    def "Deveria validar o fluxo de uma request para cadastro de pauta com código HTTP de retorno igual a 200 e uma resposta válida"() {
        given: "Dado que as chamadas para cadastro de pauta retornem OK"
        String urlBase = "/cadastrar"
        Pauta pautaMock = Fixture.from(Pauta).gimme(PAUTA_OBRA_CADASTRADA)
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

    def "Deveria validar o fluxo de uma request para iniciar a pauta com código HTTP de retorno igual a 200 e uma resposta válida"() {
        given: "Dado que as chamadas para iniciar a pauta retornem OK"
        PautaDuracaoDTO pautaDuracaoRequerida = Fixture.from(PautaDuracaoDTO).gimme(PAUTA_DURACAO_DTO_OBRA)
        Pauta pautaMock = Fixture.from(Pauta).gimme(PAUTA_OBRA_COM_SESSAO)
        String urlBase = "/iniciar-pauta"
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

    def "Deveria validar o fluxo de uma request para receber votos de pauta com código HTTP de retorno igual a 200 e uma resposta válida"() {
        given: "Dado que para receber votos na pauta retornem OK"
        String urlBase = "/receber-voto"
        Voto votoRequerida = Fixture.from(Voto).gimme(VOTO_COM_ID_PAUTA)
        Voto votoMock = Fixture.from(Voto).gimme(VOTO_PAUTA_COM_SESSAO)
        def votoDTOContent = "{\"cpfAssociado\":\"999.999.999-99\",\"idPauta\":19900,\"voto\":\"Sim\"}"

        and: "chamado o execute do salvarPautaUsecase"
        1 * receberVotoUseCase.execute(votoRequerida) >> votoMock

        when: "A Votação da pauta for chamado"
        MvcResult mvcResult = mockMvc.perform(postMockMvc(urlBase)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(votoDTOContent))
                .andReturn()

        then: "Deve retornar status 200"
        mvcResult.getResponse().getStatus() == HttpStatus.OK.value()

        and: "Deve retornar um voto valida"
        def jsonNode = new ObjectMapper()
                .readTree(mvcResult.response.getContentAsString(StandardCharsets.UTF_8))

        VotoDTO votoDTOMock = Fixture.from(VotoDTO).gimme(VOTO_DTO_PAUTA)

        verifyAll(jsonNode) {
            it.get("idPauta").asInt() == votoDTOMock.idPauta
            it.get("cpfAssociado").asText() == votoDTOMock.cpfAssociado
            it.get("voto").asText() == votoDTOMock.voto.toValue()
        }
    }

    def "Deveria validar o fluxo de uma request para contabilizar votos da pauta com código HTTP de retorno igual a 200 e uma resposta válida"() {
        given: "Dado que as chamadas para contabilizar votos da pauta retornem OK"
        String urlBase = "/contabilizar-votos"
        Pauta pautaRequerida = Fixture.from(Pauta).gimme(PAUTA_OBRA)
        VotosPauta votosPautaMock = Fixture.from(VotosPauta).gimme(VOTOS_PAUTA_PAUTA_COM_SESSAO)
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
