package db.desafiovotacao.service;

import db.desafiovotacao.exceptions.ConflictException;
import db.desafiovotacao.model.Sessao;
import db.desafiovotacao.service.interfaces.ISessaoService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SessaoService implements ISessaoService {

    public SessaoService(){}

    @Override
    public Sessao validarSessao(Sessao sessao) {

        LocalDateTime duracaoPadrao = sessao.getInicioSessao().plusMinutes(1);
        LocalDateTime inicioSessao = sessao.getInicioSessao();
        LocalDateTime finalSessao = sessao.getFinalSessao();

        if(sessao.getFinalSessao() == null || (finalSessao.isAfter(inicioSessao) && finalSessao.isBefore(duracaoPadrao))
            || finalSessao.isEqual(inicioSessao))
            sessao.setFinalSessao(sessao.getInicioSessao().plusMinutes(1));

        if (inicioSessao.isAfter(finalSessao))
            throw new ConflictException("Inicio da sessão não pode ser após o final da sessão");

        return sessao;
    }
}
