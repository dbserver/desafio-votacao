package br.com.adonias.desafiovotacao.entities;

import br.com.adonias.desafiovotacao.entities.enums.Answer;
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

    @Column(name = "cpf_associate", nullable = false)
    private String cpfAssociate;

    @Column(nullable = false)
    private Answer answer;

    @Column(name = "agenda_id", nullable = false)
    private Long agendaId;

    @Column
    private LocalDateTime dateTime;

}
