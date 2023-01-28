package br.com.adonias.desafiovotacao.dto;

import br.com.adonias.desafiovotacao.entities.enums.Answer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class VoteDTO {

    private Long id;

    private String cpf_associate;

    private Answer answer;

    private Long agenda_id;

    private LocalDateTime dateTime;

}
