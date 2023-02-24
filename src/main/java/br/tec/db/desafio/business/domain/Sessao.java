package br.tec.db.desafio.business.domain;

import br.tec.db.desafio.business.domain.enums.Voto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Sessao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pauta_id", referencedColumnName = "id")
    private Pauta pauta;
    private Voto voto;
    @ManyToMany(mappedBy = "sessoes")
    private List<Associado> associados;

    public void addAssociado(Associado associado) {
        this.associados.add(associado);
        associado.getSessoes().add(this);
    }
    private LocalDateTime duracao;
    private int totalVotos;
}