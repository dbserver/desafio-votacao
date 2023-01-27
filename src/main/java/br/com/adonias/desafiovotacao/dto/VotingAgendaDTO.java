package br.com.adonias.desafiovotacao.dto;

import br.com.adonias.desafiovotacao.entities.Vote;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class VotingAgendaDTO {
    private Long id;

    private String description;

    private List<VoteDTO> votes;

    private int qt_yes;

    private int qt_no;
}
