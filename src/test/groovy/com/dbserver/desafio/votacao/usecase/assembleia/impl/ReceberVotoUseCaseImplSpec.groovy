package com.dbserver.desafio.votacao.usecase.assembleia.impl

import br.com.six2six.fixturefactory.Fixture
import com.dbserver.desafio.votacao.repository.PautaRepository
import com.dbserver.desafio.votacao.repository.VotoRepository
import com.dbserver.desafio.votacao.repository.entity.PautaEntity
import com.dbserver.desafio.votacao.repository.entity.VotoEntity
import com.dbserver.desafio.votacao.usecase.assembleia.ReceberVotoUseCase
import com.dbserver.desafio.votacao.usecase.domain.Voto
import spock.lang.Specification

import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates
import static fixtures.PautaEntityTemplate.PAUTA_ENTITY_OBRA_COM_SESSAO
import static fixtures.PautaEntityTemplate.PAUTA_ENTITY_OBRA
import static fixtures.VotoTemplate.VOTO_PAUTA
import static fixtures.VotoTemplate.VOTO_PAUTA_VAZIO
import static fixtures.VotoTemplate.VOTO_PAUTA_COM_SESSAO
import static fixtures.VotoEntityTemplate.VOTO_ENTITY_PAUTA
import static fixtures.VotoEntityTemplate.VOTO_ENTITY_PAUTA_SALVA

class ReceberVotoUseCaseImplSpec extends Specification {

    PautaRepository pautaRepository = Mock()

    VotoRepository votoRepository = Mock()

    ReceberVotoUseCase receberVotoUseCase

    Voto votoRequerido
    Voto votoVazioRequerido
    Voto votoMock
    List<VotoEntity> votoEntityListMock
    VotoEntity votoEntityMock
    VotoEntity votoEntitySalvaMock

    PautaEntity pautaEntityMock
    PautaEntity pautaEntitySemSessaoMock

    def setup() {
        loadTemplates("fixtures")

        receberVotoUseCase = new ReceberVotoUseCaseImpl(pautaRepository, votoRepository)

        votoRequerido = Fixture.from(Voto).gimme(VOTO_PAUTA)
        votoVazioRequerido = Fixture.from(Voto).gimme(VOTO_PAUTA_VAZIO)
        votoMock = Fixture.from(Voto).gimme(VOTO_PAUTA_COM_SESSAO)

        votoEntityListMock  = List.of(Fixture.from(VotoEntity).gimme(VOTO_ENTITY_PAUTA))
        votoEntityMock = Fixture.from(VotoEntity).gimme(VOTO_ENTITY_PAUTA)
        votoEntitySalvaMock = Fixture.from(VotoEntity).gimme(VOTO_ENTITY_PAUTA_SALVA)

        pautaEntityMock = Fixture.from(PautaEntity).gimme(PAUTA_ENTITY_OBRA_COM_SESSAO)
        pautaEntitySemSessaoMock = Fixture.from(PautaEntity).gimme(PAUTA_ENTITY_OBRA)
    }

    def "Deveria validar uma chamada com sucesso ao metodo execute da classe receberVotoUseCaseImpl "() {
        given: "Um objeto Voto de entrada valido"
        votoRequerido

        and: "uma chamada válida ao método findById do pautaRepository"
        1 * pautaRepository.findById(votoRequerido.pauta.idPauta) >> Optional.of(pautaEntityMock)

        and: "uma chamada válida ao método findByCpfAssociado do votoRepository"
        1 * votoRepository.findByCpfAssociado(votoRequerido.cpfAssociado) >> Collections.emptyList()

        and: "uma chamada válida ao método save do votoRepository"
        1 * votoRepository.save(votoEntityMock) >> votoEntitySalvaMock

        when: "o método execute do receberVotoUseCase for invocado"
        Voto votoResultado = receberVotoUseCase.execute(votoRequerido)

        then: "o objeto sessao deve ser válido com todos os campos válidos"
        votoResultado == votoMock
    }

    def "Deveria validar uma chamada com parametos nulos ao metodo execute da classe receberVotoUseCaseImpl "() {
        given: "Um objeto Voto de entrada com atributos nulos"
        votoVazioRequerido

        and: "uma chamada válida ao método findById do pautaRepository"
        1 * pautaRepository.findById(votoVazioRequerido.pauta.idPauta) >> Optional.empty()

        and: "sem uma chamada válida ao método findByCpfAssociado do votoRepository"
        0 * votoRepository.findByCpfAssociado(votoRequerido.cpfAssociado)

        and: "sem uma chamada válida ao método save do votoRepository"
        0 * votoRepository.save(votoEntityMock)

        when: "o método execute do receberVotoUseCase for invocado"
        Voto votoResultado = receberVotoUseCase.execute(votoVazioRequerido)

        then: "o objeto sessao deve ser válido com todos os campos válidos"
        votoResultado == null
    }

    def "Deveria validar uma chamada sem sessao ao metodo execute da classe receberVotoUseCaseImpl "() {
        given: "Um objeto Voto de entrada valido"
        votoRequerido

        and: "uma chamada válida ao método findById do pautaRepository"
        1 * pautaRepository.findById(votoRequerido.pauta.idPauta) >> Optional.of(pautaEntitySemSessaoMock)

        and: "sem uma chamada válida ao método findByCpfAssociado do votoRepository"
        0 * votoRepository.findByCpfAssociado(votoRequerido.cpfAssociado)

        and: "sem uma chamada válida ao método save do votoRepository"
        0 * votoRepository.save(votoEntityMock)

        when: "o método execute do receberVotoUseCase for invocado"
        Voto votoResultado = receberVotoUseCase.execute(votoRequerido)

        then: "o objeto sessao deve ser válido com todos os campos válidos"
        votoResultado == null
    }

    def "Deveria validar uma chamada que gera uma exception ao metodo execute da classe IniciarPautaUsecaseImpl"() {
        given: "Um objeto Pauta de entrada valido"
        votoRequerido

        and: "uma chamada que gera exception ao método save de pautaRepository"
        1 * pautaRepository.findById(votoRequerido.pauta.idPauta) >> { throw new Exception() }

        when: "o método execute do ReceberVotoUseCase for invocado"
        receberVotoUseCase.execute(votoRequerido)

        then: "o retorno do voto da pauta deve gerar uma exception"
        thrown(Exception)
    }
}
