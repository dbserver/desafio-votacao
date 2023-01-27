package br.com.adonias.desafiovotacao.mapper;

import br.com.adonias.desafiovotacao.dto.VotingAgendaDTO;
import br.com.adonias.desafiovotacao.entities.VotingAgenda;
import br.com.adonias.desafiovotacao.entities.enums.Answer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class VotingAgendaMapper {
    @Autowired
    private ModelMapper modelMapper;

    public VotingAgendaDTO convertToDto(VotingAgenda agenda) {
        VotingAgendaDTO agendaDTO = modelMapper.map(agenda, VotingAgendaDTO.class);
        agendaDTO.setQt_no(countVotes(agenda, Answer.NO));
        agendaDTO.setQt_yes(countVotes(agenda, Answer.YES));
        return agendaDTO;
    }

    private static int countVotes(VotingAgenda agenda, Answer answer) {
        return agenda.getVotes().stream().filter(a -> a.getAnswer().equals(answer)).collect(Collectors.toList()).size();
    }

    public VotingAgenda convertToEntity(VotingAgendaDTO voteDTO) {
        return modelMapper.map(voteDTO, VotingAgenda.class);
    }


}
