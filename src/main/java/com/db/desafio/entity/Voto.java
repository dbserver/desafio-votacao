package com.db.desafio.entity;


import com.db.desafio.enumerate.VotoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
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
    @NotNull
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;
    @ManyToOne
    @NotNull
    @JoinColumn(name = "associado_id")
    private Associado associado;

    public Voto(VotoEnum votoEnum, Pauta pauta, Associado associado) {
        this.votoEnum = votoEnum;
        this.pauta = pauta;
        this.associado = associado;
    }
}
