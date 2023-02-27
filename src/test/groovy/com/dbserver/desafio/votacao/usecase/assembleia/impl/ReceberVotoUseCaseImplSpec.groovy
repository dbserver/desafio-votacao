package com.dbserver.desafio.votacao.usecase.assembleia.impl

import br.com.six2six.fixturefactory.Fixture
import com.dbserver.desafio.votacao.exception.PautaInexistenteException
import com.dbserver.desafio.votacao.exception.PautaSemSessaoException
import com.dbserver.desafio.votacao.exception.SessaoFinalizadaException
import com.dbserver.desafio.votacao.exception.VotoJaRealizadoException
import com.dbserver.desafio.votacao.repository.PautaRepository
import com.dbserver.desafio.votacao.repository.VotoRepository
import com.dbserver.desafio.votacao.repository.entity.PautaEntity
import com.dbserver.desafio.votacao.repository.entity.VotoEntity
import com.dbserver.desafio.votacao.usecase.assembleia.ReceberVotoUseCase
import com.dbserver.desafio.votacao.usecase.domain.Voto
import spock.lang.Specification

import java.time.Clock
import java.time.Instant
import java.time.LocalDateTime
import java.time.Month
import java.time.ZoneOffset

import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates
import static fixtures.PautaEntityTemplate.PAUTA_ENTITY_OBRA
import static fixtures.PautaEntityTemplate.PAUTA_ENTITY_OBRA_COM_SESSAO
import static fixtures.VotoEntityTemplate.VOTO_ENTITY_PAUTA
import static fixtures.VotoEntityTemplate.VOTO_ENTITY_PAUTA_SALVA
import static fixtures.VotoTemplate.*

class ReceberVotoUseCaseImplSpec extends Specification {

    Clock clock = Clock.fixed(Instant.parse("2023-02-25T19:30:00Z"), ZoneOffset.UTC)

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

        receberVotoUseCase = new ReceberVotoUseCaseImpl(clock, pautaRepository, votoRepository)

        votoRequerido = Fixture.from(Voto).gimme(VOTO_PAUTA)
        votoVazioRequerido = Fixture.from(Voto).gimme(VOTO_PAUTA_VAZIO)
        votoMock = Fixture.from(Voto).gimme(VOTO_PAUTA_COM_SESSAO)

        votoEntityListMock = List.of(Fixture.from(VotoEntity).gimme(VOTO_ENTITY_PAUTA))
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

    def "Deveria validar uma chamada com parametos nulos ao metodo execute da classe receberVotoUseCaseImpl gera PautaInexistenteException "() {
        given: "Um objeto Voto de entrada com atributos nulos"
        votoVazioRequerido

        and: "uma chamada válida ao método findById do pautaRepository"
        1 * pautaRepository.findById(votoVazioRequerido.pauta.idPauta) >> Optional.empty()

        and: "sem uma chamada válida ao método findByCpfAssociado do votoRepository"
        0 * votoRepository.findByCpfAssociado(votoRequerido.cpfAssociado)

        and: "sem uma chamada válida ao método save do votoRepository"
        0 * votoRepository.save(votoEntityMock)

        when: "o método execute do receberVotoUseCase for invocado"
        receberVotoUseCase.execute(votoVazioRequerido)

        then: "o objeto sessao deve gerar PautaInexistenteException"
        thrown(PautaInexistenteException)
    }

    def "Deveria validar uma chamada sem sessao ao metodo execute da classe receberVotoUseCaseImpl gera PautaSemSessaoException"() {
        given: "Um objeto Voto de entrada valido"
        votoRequerido

        and: "uma chamada válida ao método findById do pautaRepository"
        1 * pautaRepository.findById(votoRequerido.pauta.idPauta) >> Optional.of(pautaEntitySemSessaoMock)

        and: "sem uma chamada válida ao método findByCpfAssociado do votoRepository"
        0 * votoRepository.findByCpfAssociado(votoRequerido.cpfAssociado)

        and: "sem uma chamada válida ao método save do votoRepository"
        0 * votoRepository.save(votoEntityMock)

        when: "o método execute do receberVotoUseCase for invocado"
        receberVotoUseCase.execute(votoRequerido)

        then: "o objeto sessao deve gerar PautaSemSessaoException"
        thrown(PautaSemSessaoException)
    }

    def "Deveria validar uma chamada que gera VotoJaRealizadoException ao metodo execute da classe receberVotoUseCaseImpl "() {
        given: "Um objeto Voto de entrada valido"
        votoRequerido

        and: "uma chamada válida ao método findById do pautaRepository"
        1 * pautaRepository.findById(votoRequerido.pauta.idPauta) >> Optional.of(pautaEntityMock)

        and: "uma chamada válida ao método findByCpfAssociado do votoRepository"
        1 * votoRepository.findByCpfAssociado(votoRequerido.cpfAssociado) >> List.of(votoEntityListMock)

        and: "sem uma chamada válida ao método save do votoRepository"
        0 * votoRepository.save(votoEntityMock)

        when: "o método execute do receberVotoUseCase for invocado"
        receberVotoUseCase.execute(votoRequerido)

        then: "o objeto sessao deve gerar VotoJaRealizadoException"
        thrown(VotoJaRealizadoException)
    }

    def "Deveria validar uma chamada que gera SessaoFinalizadaException ao metodo execute da classe receberVotoUseCaseImpl "() {
        given: "Um objeto Voto de entrada valido"
        votoRequerido
        pautaEntityMock.sessao.inicio = LocalDateTime.of(2022, Month.FEBRUARY,01,01,01,01)

        and: "uma chamada válida ao método findById do pautaRepository"
        1 * pautaRepository.findById(votoRequerido.pauta.idPauta) >> Optional.of(pautaEntityMock)

        and: "sem uma chamada válida ao método findByCpfAssociado do votoRepository"
        0 * votoRepository.findByCpfAssociado(votoRequerido.cpfAssociado)

        and: "sem uma chamada válida ao método save do votoRepository"
        0 * votoRepository.save(votoEntityMock)

        when: "o método execute do receberVotoUseCase for invocado"
        receberVotoUseCase.execute(votoRequerido)

        then: "o objeto sessao deve gerar SessaoFinalizadaException"
        thrown(SessaoFinalizadaException)
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
