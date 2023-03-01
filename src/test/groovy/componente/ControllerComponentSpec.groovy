package componente

import br.com.six2six.fixturefactory.Fixture
import com.dbserver.desafio.votacao.endpoint.CadastrarPautaController
import com.dbserver.desafio.votacao.endpoint.ContabilizarVotosController
import com.dbserver.desafio.votacao.endpoint.IniciarPautaController
import com.dbserver.desafio.votacao.endpoint.ReceberVotoController
import com.dbserver.desafio.votacao.endpoint.dto.PautaDTO
import com.dbserver.desafio.votacao.endpoint.dto.PautaDuracaoDTO
import com.dbserver.desafio.votacao.endpoint.dto.PautaIdDTO
import com.dbserver.desafio.votacao.endpoint.dto.PautaSessaoDTO
import com.dbserver.desafio.votacao.endpoint.dto.VotoDTO
import com.dbserver.desafio.votacao.endpoint.dto.VotosPautaDTO
import com.dbserver.desafio.votacao.gateway.ValidarCpfGateway
import com.dbserver.desafio.votacao.gateway.client.ValidaCpfClient
import com.dbserver.desafio.votacao.gateway.dto.CpfStatusRespostaDTO
import com.dbserver.desafio.votacao.repository.PautaRepository
import com.dbserver.desafio.votacao.repository.VotoRepository
import com.dbserver.desafio.votacao.repository.entity.PautaEntity
import com.dbserver.desafio.votacao.repository.entity.VotoEntity
import com.dbserver.desafio.votacao.usecase.assembleia.ReceberVotoUseCase
import com.dbserver.desafio.votacao.usecase.domain.Voto
import com.fasterxml.jackson.databind.ObjectMapper
import com.microsoft.applicationinsights.core.dependencies.google.gson.Gson
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.setup.MockMvcBuilders
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
import static fixtures.PautaEntityTemplate.PAUTA_ENTITY_OBRA_COM_SESSAO
import static fixtures.PautaEntityTemplate.PAUTA_ENTITY_OBRA_COM_SESSAO_SEM_ID
import static fixtures.PautaEntityTemplate.PAUTA_ENTITY_OBRA_SEM_SESSAO
import static fixtures.PautaEntityTemplate.PAUTA_ENTITY_OBRA
import static fixtures.PautaIdDtoTemplate.PAUTA_ID_DTO_OBRA
import static fixtures.PautaSessaoDtoTemplate.PAUTA_SESSAO_DTO_OBRA_SALVA
import static fixtures.VotoDtoTemplate.VOTO_DTO_PAUTA
import static fixtures.VotoEntityTemplate.VOTO_ENTITY_PAUTA_NAO
import static fixtures.VotoEntityTemplate.VOTO_ENTITY_PAUTA_SALVA
import static fixtures.VotoEntityTemplate.VOTO_ENTITY_PAUTA_SIM
import static fixtures.VotoTemplate.VOTO_COM_ID_PAUTA
import static fixtures.CpfStatusRespostaDTOTemplate.CPF_STATUS_DTO
import static fixtures.VotosPautaDtoTemplate.VOTOS_PAUTA_DTO_PAUTA_COM_SESSAO
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post as postMockMvc

@SpringBootTest(classes = [{}])
@AutoConfigureMockMvc
@EnableWebMvc
@ComponentScan(basePackages = [
        "com.dbserver.desafio.votacao.endpoint",
        "com.dbserver.desafio.votacao.usecase",
        "com.dbserver.desafio.votacao.repository",
        "com.dbserver.desafio.votacao.gateway"])
class ControllerComponentSpec extends Specification {

    @Autowired
    MockMvc mockMvc

    @SpringBean
    PautaRepository pautaRepository = Mock()

    @SpringBean
    Clock clock = Clock.fixed(Instant.parse("2023-02-25T19:30:00Z"), ZoneOffset.UTC)

    @SpringBean
    VotoRepository votoRepository = Mock()

    @SpringBean
    ValidaCpfClient validaCpfClient = Mock()

    def setup() {
        loadTemplates("fixtures")
    }

    def "Deveria validar o fluxo de uma request para cadastro de pauta com código HTTP de retorno igual a 200 e uma resposta válida"() {
        given: "Dado que as chamadas para cadastro de pauta retornem OK"
        String urlBase = "/cadastrar"
        PautaDTO pautaDTORequisicao = Fixture.from(PautaDTO).gimme(PAUTA_DTO_OBRA)
        PautaEntity pautaEntityRequerida = Fixture.from(PautaEntity).gimme(PAUTA_ENTITY_OBRA)
        PautaEntity pautaEntityMock = Fixture.from(PautaEntity).gimme(PAUTA_ENTITY_OBRA_SEM_SESSAO)
        def pautaContent = new Gson().toJson(pautaDTORequisicao)

        and: "chamado o execute dosalvarPautaUsecase"
        1 * pautaRepository.save(pautaEntityRequerida) >> pautaEntityMock

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

    def "Deveria validar o fluxo de sucesso para iniciar a pauta com código HTTP de retorno igual a 200"() {
        given: "Dado que as chamadas para iniciar a pauta retornem OK"
        String urlBase = "/iniciar-pauta"
        PautaDuracaoDTO pautaDuracaoRequerida = Fixture.from(PautaDuracaoDTO).gimme(PAUTA_DURACAO_DTO_OBRA)
        PautaEntity pautaSemSessaoEntityMock = Fixture.from(PautaEntity).gimme(PAUTA_ENTITY_OBRA_SEM_SESSAO)
        PautaEntity pautaEntityComSessaoSemIdMock = Fixture.from(PautaEntity).gimme(PAUTA_ENTITY_OBRA_COM_SESSAO_SEM_ID)
        PautaEntity pautaEntityComSessaoMock = Fixture.from(PautaEntity).gimme(PAUTA_ENTITY_OBRA_COM_SESSAO)
        def pautaContent = new Gson().toJson(pautaDuracaoRequerida)

        and: "chamado o findById do pautaRepository"
        1 * pautaRepository.findById(pautaDuracaoRequerida.idPauta) >> Optional.of(pautaSemSessaoEntityMock)

        and: "chamado o save do pautaRepository"
        1 * pautaRepository.save(pautaEntityComSessaoSemIdMock) >> pautaEntityComSessaoMock

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

    def "Deveria validar o fluxo de uma request para receber votos com código HTTP de retorno igual a 200"() {
        given: "Dado que as chamadas para receber votos de pauta retornem OK"
        String urlBase = "/receber-voto"
        PautaEntity pautaEntity = Fixture.from(PautaEntity).gimme(PAUTA_ENTITY_OBRA_COM_SESSAO)
        VotoEntity votoEntitySemIdRequerida = Fixture.from(VotoEntity).gimme(VOTO_ENTITY_PAUTA_SIM)
        VotoEntity votoEntityComIdMock = Fixture.from(VotoEntity).gimme(VOTO_ENTITY_PAUTA_SALVA)
        Voto votoRequerida = Fixture.from(Voto).gimme(VOTO_COM_ID_PAUTA)
        ResponseEntity<CpfStatusRespostaDTO> cpfStatusDTOResponseEntity = ResponseEntity<CpfStatusRespostaDTO>
                .ok(Fixture.from(CpfStatusRespostaDTO).gimme(CPF_STATUS_DTO))

        def votoDTOContent = "{\"cpfAssociado\":\"999.999.999-99\",\"idPauta\":19900,\"voto\":\"Sim\"}"

        and: "chamado o findById pautaRepository"
        1 * pautaRepository.findById(votoRequerida.getPauta().getIdPauta()) >> Optional.of(pautaEntity)

        and: "chamado o findByCpfAssociado votoRepository"
        1 * votoRepository.findByCpfAssociado(votoRequerida.getCpfAssociado()) >> Collections.emptyList()

        and: "chamado o findByCpfAssociado votoRepository"
        1 * validaCpfClient.validaClient(votoRequerida.getCpfAssociado()) >> cpfStatusDTOResponseEntity

        and: "chamado o save do votoRepository"
        1 * votoRepository.save(votoEntitySemIdRequerida) >> votoEntityComIdMock

        when: "A votacao da pauta for chamado"
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
        PautaIdDTO pautaIdDTORequerida = Fixture.from(PautaIdDTO).gimme(PAUTA_ID_DTO_OBRA)
        List<VotoEntity> votoEntityListMock = List.of(Fixture.from(VotoEntity).gimme(VOTO_ENTITY_PAUTA_SALVA),
                Fixture.from(VotoEntity).gimme(VOTO_ENTITY_PAUTA_SIM),
                Fixture.from(VotoEntity).gimme(VOTO_ENTITY_PAUTA_NAO))
        PautaEntity pautaEntityMock = Fixture.from(PautaEntity).gimme(PAUTA_ENTITY_OBRA_COM_SESSAO)
        def pautaContent = new Gson().toJson(pautaIdDTORequerida)

        and: "chamado o findById do pautaRepository"
        1 * pautaRepository.findById(pautaIdDTORequerida.idPauta) >> Optional.of(pautaEntityMock)

        and: "chamado o findByPauta do votoRepository"
        1 * votoRepository.findByPauta(pautaEntityMock) >> votoEntityListMock

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
