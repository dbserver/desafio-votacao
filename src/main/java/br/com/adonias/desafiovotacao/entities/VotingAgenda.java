package br.com.adonias.desafiovotacao.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@ToString
@Entity
@Table(name = "tb_agenda")
public class VotingAgenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @OneToMany
    @JoinColumn(name = "agenda_id")
    private List<Vote> votes;
}
