package db.desafiovotacao.model;

import db.desafiovotacao.dto.AssociadoPautaRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "associados_pauta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssociadoPauta {

    @Id
    @Column(name = "uuid_associado_pauta")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuidAssociadoPauta;

    @Column(name = "votou")
    private Boolean votou;

    @ManyToOne(fetch = FetchType.LAZY)
    private Associado associado;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pauta pauta;

    public AssociadoPauta(Associado associado, Pauta pauta){
        this.votou = false;
        this.associado = associado;
        this.pauta = pauta;
    }
}
