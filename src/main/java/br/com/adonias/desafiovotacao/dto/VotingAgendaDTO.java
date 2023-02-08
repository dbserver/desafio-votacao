package br.com.adonias.desafiovotacao.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class VotingAgendaDTO {
    private Long id;

    private String description;

    private List<VoteDTO> votes;

    private int qt_yes;

    private int qt_no;
}
