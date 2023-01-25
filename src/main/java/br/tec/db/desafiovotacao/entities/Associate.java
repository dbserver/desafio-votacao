package br.tec.db.desafiovotacao.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Associate {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String cpf;

    @OneToMany
    @JsonIgnore
    private List<Vote> votes;
}
