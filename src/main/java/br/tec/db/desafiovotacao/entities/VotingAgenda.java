package br.tec.db.desafiovotacao.entities;

import br.tec.db.desafiovotacao.dto.AssociateDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class VotingAgenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @OneToMany
    private List<Vote> votes;

}
