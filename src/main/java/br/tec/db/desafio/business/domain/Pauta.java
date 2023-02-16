package br.tec.db.desafio.business.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Pauta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    public Pauta(String assunto) {
        this.assunto = assunto;
    }

    @OneToOne(mappedBy = "pauta")
    private Sessao sessao;
    private String assunto;

}