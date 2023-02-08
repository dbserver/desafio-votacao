package br.com.adonias.desafiovotacao.business.services;

import br.com.adonias.desafiovotacao.entities.Session;

import java.util.List;
import java.util.Optional;

public interface ISessionService {

    Session save(Session session);

    Optional<Session> getSessionById(Long id);

    void deleteById(Long id);

    List<Session> getAllSessions();

    boolean exists(Long id);
}
