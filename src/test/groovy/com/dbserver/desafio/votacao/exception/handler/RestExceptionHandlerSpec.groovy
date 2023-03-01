package com.dbserver.desafio.votacao.exception.handler

import com.dbserver.desafio.votacao.exception.PautaInexistenteException
import com.dbserver.desafio.votacao.exception.PautaSemSessaoException
import com.dbserver.desafio.votacao.exception.PautaSemVotoException
import com.dbserver.desafio.votacao.exception.SessaoFinalizadaException
import com.dbserver.desafio.votacao.exception.VotoJaRealizadoException
import feign.FeignException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import spock.lang.Specification

import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates

class RestExceptionHandlerSpec extends Specification {

    RestExceptionHandler restExceptionHandler

    def setup() {
        loadTemplates("fixtures")

        restExceptionHandler = new RestExceptionHandler()

    }

    def "Deveria chamar o handle VotoJaRealizadoException e validar o status code"() {

        when: "quando o handle restExceptionHandler for incovado"
        def exception = restExceptionHandler.handleVotoJaRealizadoException(new VotoJaRealizadoException())

        then: "validar o status code 500"
        exception.statusCode == HttpStatus.INTERNAL_SERVER_ERROR

        and: "validar a mensagem"
        exception.body.mensagem == "Voto já foi realizado para esse CPF!"
    }

    def "Deveria chamar o handle SessaoFinalizadaException e validar o status code"() {

        when: "quando o handle restExceptionHandler for incovado"
        def exception = restExceptionHandler.handleSessaoFinalizadaException(new SessaoFinalizadaException())

        then: "validar o status code 500"
        exception.statusCode == HttpStatus.INTERNAL_SERVER_ERROR

        and: "validar a mensagem"
        exception.body.mensagem == "Sessão já finalizada para esta Pauta!"
    }

    def "Deveria chamar o handle PautaSemSessaoException e validar o status code"() {

        when: "quando o handle restExceptionHandler for incovado"
        def exception = restExceptionHandler.handlePautaSemSessaoException(new PautaSemSessaoException())

        then: "validar o status code 500"
        exception.statusCode == HttpStatus.INTERNAL_SERVER_ERROR

        and: "validar a mensagem"
        exception.body.mensagem == "Não existe sessão para esta Pauta"
    }

    def "Deveria chamar o handle PautaInexistenteException e validar o status code"() {

        when: "quando o handle restExceptionHandler for incovado"
        def exception = restExceptionHandler.handlePautaInexistenteException(new PautaInexistenteException())

        then: "validar o status code 500"
        exception.statusCode == HttpStatus.INTERNAL_SERVER_ERROR

        and: "validar a mensagem"
        exception.body.mensagem == "Pauta Inexistente!"
    }

    def "Deveria chamar o handle PautaSemVotoException e validar o status code"() {

        when: "quando o handle restExceptionHandler for incovado"
        def exception = restExceptionHandler.handlePautaSemVotoException(new PautaSemVotoException())

        then: "validar o status code 500"
        exception.statusCode == HttpStatus.INTERNAL_SERVER_ERROR

        and: "validar a mensagem"
        exception.body.mensagem == "Esta Pauta não contém votos!"
    }

    def "Deveria chamar o handle MethodArgumentNotValidException e validar o status code"() {

        when: "quando o handle restExceptionHandler for incovado"
        def exception = restExceptionHandler.handleMethodArgumentNotValidException(null)

        then: "validar o status code 404"
        exception.statusCode == HttpStatus.BAD_REQUEST

        and: "validar a mensagem"
        exception.body.mensagem == "Verificar campos obrigatórios do payload"
    }

    def "Deveria chamar o handle handleHttpMessageNotReadableException e validar o status code"() {

        when: "quando o handle restExceptionHandler for incovado"
        def exception = restExceptionHandler.handleHttpMessageNotReadableException(null)

        then: "validar o status code 404"
        exception.statusCode == HttpStatus.BAD_REQUEST

        and: "validar a mensagem"
        exception.body.mensagem == "Verificar os formatos corretos dos campos do payload"
    }

    def "Deveria chamar o handle FeignException e validar o status code"() {

        when: "quando o handle restExceptionHandler for incovado"
        def exception = restExceptionHandler.handleFeignException(null)

        then: "validar o status code 404"
        exception.statusCode == HttpStatus.BAD_REQUEST

        and: "validar a mensagem"
        exception.body.mensagem == "CPF Inválido"
    }

    def "Deveria chamar o handle Exception e validar o status code"() {

        when: "quando o handle restExceptionHandler for incovado"
        def exception = restExceptionHandler.handleDefaultException(new Exception())

        then: "validar o status code 500"
        exception.statusCode == HttpStatus.INTERNAL_SERVER_ERROR

        and: "validar a mensagem"
        exception.body.mensagem == null
    }
}
