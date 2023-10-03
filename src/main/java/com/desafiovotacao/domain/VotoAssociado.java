package com.desafiovotacao.domain;

import com.desafiovotacao.dto.TipoVotoEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "votos_associados")
public class VotoAssociado {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(nullable = false)
    private LocalDateTime data;

    @Column(nullable = false, name = "tipo_voto")
    @Enumerated(EnumType.STRING)
    private TipoVotoEnum tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "associado_id")
    private Associado associado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sessao_id")
    private SessaoPauta sessaoPauta;

}
