package br.tec.db.desafio.business.domain;

import br.tec.db.desafio.business.domain.enums.Voto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Sessao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pauta_id", referencedColumnName = "id")
    private Pauta pauta;
    private Voto voto;
    private int totalVotos;
}