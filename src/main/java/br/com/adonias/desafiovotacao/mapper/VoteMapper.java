package br.com.adonias.desafiovotacao.mapper;

import br.com.adonias.desafiovotacao.dto.VoteDTO;
import br.com.adonias.desafiovotacao.entities.Vote;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VoteMapper {
    @Autowired
    private ModelMapper modelMapper;

    public VoteDTO convertToDto(Vote vote) {
        return modelMapper.map(vote, VoteDTO.class);
    }

    public Vote convertToEntity(VoteDTO voteDTO) {
        return modelMapper.map(voteDTO, Vote.class);
    }

}
