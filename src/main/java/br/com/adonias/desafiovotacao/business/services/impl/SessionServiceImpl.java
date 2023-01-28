package br.com.adonias.desafiovotacao.business.services.impl;

import br.com.adonias.desafiovotacao.business.services.ISessionService;
import br.com.adonias.desafiovotacao.entities.Session;
import br.com.adonias.desafiovotacao.repositories.ISessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessionServiceImpl implements ISessionService {
    @Autowired
    private ISessionRepository repository;

    @Override
    public Session save(Session session) {
        return repository.save(session);
    }

    @Override
    public Optional<Session> getSessionById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
        }
    }

    @Override
    public List<Session> getAllSessions() {
        return repository.findAll();
    }
}
