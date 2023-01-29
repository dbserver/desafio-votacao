package br.com.occ.desafiovotacao.v1.service;

import br.com.occ.desafiovotacao.v1.model.Pauta;
import br.com.occ.desafiovotacao.v1.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PautaService implements IPautaService{

    @Autowired
    PautaRepository repository;

    @Override
    public Optional<Pauta> findById(Long id) {
        Optional<Pauta> pautaOptional = repository.findById(id);
        return pautaOptional;
    }

    @Override
    public List<Pauta> findAll() {
        return repository.findAll();
    }

    @Override
    public Pauta save(Pauta pauta) {
        return repository.save(pauta);
    }

    @Override
    public Pauta update(Pauta pauta) {
        return repository.save(pauta);
    }

    @Override
    public void remove(Pauta pauta) {
        repository.delete(pauta);
    }

    @Override
    public List<Pauta> findAllAtivas() {
        return repository.findAllPautasAtivas(LocalDateTime.now());
    }
}
