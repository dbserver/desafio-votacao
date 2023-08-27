package com.db.votacao.api.model;

import com.db.votacao.api.enums.EnumOpcoesVoto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = Voto.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Voto {

    public static final String TABLE_NAME = "voto";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_voto", unique = true)
    private UUID idVoto;

    @ManyToOne
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;

    @ManyToOne
    @JoinColumn(name = "associado_id")
    private Associado associado;

    @Column(name = "voto")
    @Enumerated(EnumType.STRING)
    private EnumOpcoesVoto voto;
}
