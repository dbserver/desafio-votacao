package br.com.adonias.desafiovotacao.entities;

import br.com.adonias.desafiovotacao.entities.enums.Answer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@ToString
@Entity
@Table(name = "tb_vote")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String cpf_associate;

    @Column(nullable = false)
    private Answer answer;

    @Column
    private Long agenda_id;

    @Column
    private LocalDateTime dateTime;

}
