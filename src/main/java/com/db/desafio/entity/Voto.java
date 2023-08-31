package com.db.desafio.entity;


import com.db.desafio.enumerate.VotoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_voto")
public class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Enumerated(EnumType.STRING)
    @NotNull
    private VotoEnum votoEnum;
    @ManyToOne
    @JoinColumn(name = "id_sessao_votacao")
    private SessaoVotacao sessaoVotacao;
    @ManyToOne
    @NotNull
    @JoinColumn(name = "id_associado")
    private Associado associado;


    public Voto(VotoEnum votoEnum, Associado associado) {
        this.votoEnum = votoEnum;
        this.associado = associado;
    }

    public Voto(VotoEnum votoEnum) {
        this.votoEnum = votoEnum;
    }
}
