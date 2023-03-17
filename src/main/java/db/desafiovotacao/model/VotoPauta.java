package db.desafiovotacao.model;

import db.desafiovotacao.dto.VotoPautaRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "votos_pauta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VotoPauta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private Voto voto;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pauta pauta;

    public VotoPauta(VotoPautaRequest votoPautaRequest, Pauta pauta){
        this.voto = new Voto(votoPautaRequest.voto());
        this.pauta = pauta;
    }
}
