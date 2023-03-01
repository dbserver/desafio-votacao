package br.tec.db.desafio.business.service.implementation.validacao;

import br.tec.db.desafio.api.v1.dto.associado.AssociadoRequestV1;
import br.tec.db.desafio.api.v1.dto.associado.client.AssociadoClientRequestV1;
import br.tec.db.desafio.business.domain.Associado;
import br.tec.db.desafio.business.domain.Pauta;
import br.tec.db.desafio.business.domain.Sessao;
import br.tec.db.desafio.business.service.implementation.validacao.associado.*;
import br.tec.db.desafio.business.service.implementation.validacao.pauta.ValidarPautaComPoucoCaracter;
import br.tec.db.desafio.business.service.implementation.validacao.pauta.ValidarPautaJaExistente;
import br.tec.db.desafio.business.service.implementation.validacao.pauta.ValidarPautaVazia;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.criarsessao.ValidarInexistentePorId;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.criarsessao.ValidarSessaoRepetida;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.votar.ValidarSessaoExpirada;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.votar.ValidarSessaoJaVotada;
import br.tec.db.desafio.business.service.implementation.validacao.sessao.votar.ValidarSessaoResultado;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
public class FactoryValidacao {


    public void validarCpf(AssociadoRequestV1 cpf){
        ValidarCpf validarCpf = new ValidarCpf();
        validarCpf.validar(cpf);
    }

    public void validarCpf(AssociadoClientRequestV1 cpf){
        ValidarCpf validarCpf = new ValidarCpf();
        validarCpf.validar(cpf);
    }

    public void validarNaoPodeSerNulo(Associado dado){
        ValidarNaoPodeSerNulo validarNaoPodeSerNulo = new ValidarNaoPodeSerNulo();
        validarNaoPodeSerNulo.validar(dado);
    }

    public void validarComPoucoCaracter(String dado){
        ValidarPautaComPoucoCaracter validarComPoucoCaracter = new ValidarPautaComPoucoCaracter();
        validarComPoucoCaracter.validar(dado);
    }

    public void validarJaExistente(Pauta dado){
        ValidarPautaJaExistente validarJaExistente = new ValidarPautaJaExistente();
        validarJaExistente.validar(dado);
    }
    public void validarVazio(String dado){
        ValidarPautaVazia validarVazio = new ValidarPautaVazia();
        validarVazio.validar(dado);
    }

    public void validarSessaoInexistente(Long id){
        ValidarInexistentePorId validarSessaoInexistente = new ValidarInexistentePorId();
        validarSessaoInexistente.validar(id);
    }

    public void validarSessaoRepetida(Sessao dado){
        ValidarSessaoRepetida validarSessaoRepetida = new ValidarSessaoRepetida();
        validarSessaoRepetida.validar(dado);
    }

    public void validarSessaoExpirada(LocalDateTime localDateTime){
        ValidarSessaoExpirada validarSessaoExpirada = new ValidarSessaoExpirada();
        validarSessaoExpirada.validar(localDateTime);
    }

    public void validarSessaoJaVotada(Long id){
        ValidarSessaoJaVotada validarSessaoJaVotada = new ValidarSessaoJaVotada();
        validarSessaoJaVotada.validar(id);
    }

    public void validarSessaoResultado(Sessao sessao) {
        ValidarSessaoResultado validarSessaoExpirada = new ValidarSessaoResultado();
        validarSessaoExpirada.validar(sessao);
    }
}
