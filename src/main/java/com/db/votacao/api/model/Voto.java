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

    @Column(name = "voto")
    @Enumerated(EnumType.STRING)
    private EnumOpcoesVoto voto;
}
