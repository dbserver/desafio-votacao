package com.fernandesclaudi.desafiovotacao.model;

import com.fernandesclaudi.desafiovotacao.enums.VotoEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "voto")
public class Voto {
    @Id
    @SequenceGenerator(name = "seq_voto", sequenceName = "seq_voto", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_voto")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "idassociado", referencedColumnName = "id")
    private Associado associado;
    @Column(name = "voto")
    @Enumerated(value = EnumType.STRING)
    private VotoEnum voto;
    @Column(name = "dtvoto")
    private LocalDateTime dtVoto;
    @OneToOne
    @JoinColumn(name = "idsessao", referencedColumnName = "id")
    private Sessao sessao;
}
