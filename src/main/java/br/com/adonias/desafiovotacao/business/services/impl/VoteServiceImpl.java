package br.com.adonias.desafiovotacao.business.services.impl;

import br.com.adonias.desafiovotacao.business.services.IVoteService;
import br.com.adonias.desafiovotacao.entities.Vote;
import br.com.adonias.desafiovotacao.repositories.IVoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("voteService")
public class VoteServiceImpl implements IVoteService {

    @Autowired
    private IVoteRepository voteRepository;
    @Override
    public Optional<Vote> getVoteById(Long id) {
        return voteRepository.findById(id);
    }

    @Override
    public List<Vote> getAllVotes() {
        return voteRepository.findAll();
    }

    @Override
    public List<Vote> getVotesByAgendaId(Long agendaId) {
        return voteRepository.findAllByAgendaId(agendaId);
    }

    @Override
    public Vote create(Vote vote) {
        if (!voteRepository.existsVoteByAgendaIdAndCpfAssociate(vote.getAgendaId(), vote.getCpfAssociate())){
            return voteRepository.save(vote);
        }
        return null;
    }

    @Override
    public Vote update(Vote vote) {
        return voteRepository.save(vote);
    }

    @Override
    public void delete(Long id) {
        if(voteRepository.existsById(id)){
            voteRepository.deleteById(id);
        }
    }
}
