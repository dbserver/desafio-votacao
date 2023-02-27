package com.dbserver.desafio.votacao.usecase.assembleia.impl

import br.com.six2six.fixturefactory.Fixture
import com.dbserver.desafio.votacao.repository.PautaRepository
import com.dbserver.desafio.votacao.repository.VotoRepository
import com.dbserver.desafio.votacao.repository.entity.PautaEntity
import com.dbserver.desafio.votacao.repository.entity.VotoEntity
import com.dbserver.desafio.votacao.usecase.assembleia.ContabilizarVotosUsecase
import com.dbserver.desafio.votacao.usecase.domain.Voto
import com.dbserver.desafio.votacao.usecase.domain.VotosPauta
import spock.lang.Specification

import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates
import static fixtures.PautaEntityTemplate.PAUTA_ENTITY_OBRA
import static fixtures.PautaEntityTemplate.PAUTA_ENTITY_OBRA_COM_SESSAO
import static fixtures.VotoEntityTemplate.*
import static fixtures.VotoTemplate.*
import static fixtures.VotosPautaTemplate.VOTOS_PAUTA_PAUTA_COM_SESSAO

class ContabilizarVotosUsecaseImplSpec extends Specification {

    PautaRepository pautaRepository = Mock()
    VotoRepository votoRepository = Mock()

    ContabilizarVotosUsecase contabilizarVotosUsecase

    Voto votoRequerido
    Voto votoVazioRequerido
    Voto votoMock

    List<VotoEntity> votoEntityListMock
    VotoEntity votoEntityMock
    VotoEntity votoEntitySalvaMock

    PautaEntity pautaEntityMock
    PautaEntity pautaEntitySemSessaoMock

    VotosPauta votosPautaMock

    def setup() {
        loadTemplates("fixtures")

        contabilizarVotosUsecase = new ContabilizarVotosUsecaseImpl(pautaRepository, votoRepository)

        votoRequerido = Fixture.from(Voto).gimme(VOTO_PAUTA)
        votoVazioRequerido = Fixture.from(Voto).gimme(VOTO_PAUTA_VAZIO)
        votoMock = Fixture.from(Voto).gimme(VOTO_PAUTA_COM_SESSAO)

        votosPautaMock = Fixture.from(VotosPauta).gimme(VOTOS_PAUTA_PAUTA_COM_SESSAO)

        votoEntityListMock = List.of(Fixture.from(VotoEntity).gimme(VOTO_ENTITY_PAUTA),
                Fixture.from(VotoEntity).gimme(VOTO_ENTITY_PAUTA_SIM),
                Fixture.from(VotoEntity).gimme(VOTO_ENTITY_PAUTA_NAO))
        votoEntityMock = Fixture.from(VotoEntity).gimme(VOTO_ENTITY_PAUTA)
        votoEntitySalvaMock = Fixture.from(VotoEntity).gimme(VOTO_ENTITY_PAUTA_SALVA)

        pautaEntityMock = Fixture.from(PautaEntity).gimme(PAUTA_ENTITY_OBRA_COM_SESSAO)
        pautaEntitySemSessaoMock = Fixture.from(PautaEntity).gimme(PAUTA_ENTITY_OBRA)
    }

    def "Deveria validar uma chamada com sucesso ao metodo execute da classe contabilizarVotosUsecase "() {
        given: "Um objeto Voto de entrada valido"
        votoRequerido

        and: "uma chamada válida ao método findById do pautaRepository"
        1 * pautaRepository.findById(votoRequerido.pauta.idPauta) >> Optional.of(pautaEntityMock)

        and: "uma chamada válida ao método findByPauta do votoRepository"
        1 * votoRepository.findByPauta(pautaEntityMock) >> votoEntityListMock

        when: "o método execute do receberVotoUseCase for invocado"
        VotosPauta votosPautaResultado = contabilizarVotosUsecase.execute(votoRequerido.pauta.idPauta)

        then: "o objeto votosPauta deve ser válido com todos os campos válidos"
        votosPautaResultado == votosPautaMock
    }

    def "Deveria validar uma chamada com parametos nulos ao metodo execute da classe contabilizarVotosUsecase "() {
        given: "Um objeto Voto de entrada com atributos nulos"
        votoVazioRequerido

        and: "uma chamada válida ao método findById do pautaRepository"
        1 * pautaRepository.findById(votoVazioRequerido.pauta.idPauta) >> Optional.empty()

        when: "o método execute do contabilizarVotosUsecase for invocado"
        VotosPauta votosPautaResultado = contabilizarVotosUsecase.execute(votoVazioRequerido.pauta.idPauta)

        then: "o objeto votosPauta deve ser válido com todos os campos válidos"
        votosPautaResultado == null
    }

    def "Deveria validar uma chamada sem votacao ao metodo execute da classe contabilizarVotosUsecase "() {
        given: "Um objeto Voto de entrada valido"
        votoRequerido

        and: "uma chamada válida ao método findById do pautaRepository"
        1 * pautaRepository.findById(votoRequerido.pauta.idPauta) >> Optional.of(pautaEntityMock)

        and: "uma chamada válida ao método findByPauta do votoRepository com retorno vazio"
        1 * votoRepository.findByPauta(pautaEntityMock) >> Collections.emptyList()

        when: "o método execute do contabilizarVotosUsecase for invocado"
        VotosPauta votosPautaResultado = contabilizarVotosUsecase.execute(votoRequerido.pauta.idPauta)

        then: "o objeto cotosPauta deve retornar nulo"
        votosPautaResultado == null
    }

    def "Deveria validar uma chamada que gera uma exception ao metodo execute da classe contabilizarVotosUsecase"() {
        given: "Um objeto Pauta de entrada valido"
        votoRequerido

        and: "uma chamada que gera exception ao método save de pautaRepository"
        1 * pautaRepository.findById(votoRequerido.pauta.idPauta) >> { throw new Exception() }

        when: "o método execute do contabilizarVotosUsecase for invocado"
        contabilizarVotosUsecase.execute(votoRequerido.pauta.idPauta)

        then: "o retorno do votosPauta da pauta deve gerar uma exception"
        thrown(Exception)
    }
}
