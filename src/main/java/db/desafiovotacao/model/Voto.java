package db.desafiovotacao.model;


import db.desafiovotacao.enums.EnumVoto;
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

    public static final String TABLE_NAME = "votos";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uuid_voto", unique = true)
    private UUID uuidVoto;

    @Column(name = "opcao_voto")
    @Enumerated(EnumType.STRING)
    private EnumVoto opcaoVoto;
}
