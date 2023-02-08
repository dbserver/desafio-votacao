package br.com.adonias.desafiovotacao.business.services.impl;

import br.com.adonias.desafiovotacao.business.services.IVotingAgendaService;
import br.com.adonias.desafiovotacao.entities.VotingAgenda;
import br.com.adonias.desafiovotacao.repositories.IVotingAgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("votingAgendaService")
public class VotingAgendaServiceImpl implements IVotingAgendaService {

    @Autowired
    private IVotingAgendaRepository votingAgendaRepository;

    @Override
    public Optional<VotingAgenda> getVotingAgendaById(Long id) {
        return votingAgendaRepository.findById(id);
    }

    @Override
    public List<VotingAgenda> getAllVotingAgendas() {
        return votingAgendaRepository.findAll();
    }

    @Override
    public VotingAgenda save(VotingAgenda votingAgenda) {
        return votingAgendaRepository.save(votingAgenda);
    }

    @Override
    public void delete(Long id) {
        if(votingAgendaRepository.existsById(id)){
            votingAgendaRepository.deleteById(id);
        }
    }
}
