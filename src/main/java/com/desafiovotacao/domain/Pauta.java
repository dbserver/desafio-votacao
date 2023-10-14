package com.desafiovotacao.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pautas")
public class Pauta {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = true)
    private Long votosAFavor;

    @Column(nullable = true)
    private Long votosContra;

    @Column
    private boolean resultadoVerificado;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pauta")
    private List<SessaoPauta> sessoesPautas;

    public boolean possuiSessoesAtivas() {
        LocalDateTime dataAtual = LocalDateTime.now();
        List<SessaoPauta> sessoesAtivas = this.sessoesPautas.stream().filter(s -> {
            return dataAtual.isBefore(s.getDataFim()) && dataAtual.isAfter(s.getDataInicio());
        }).collect(Collectors.toList());
        return sessoesAtivas.size() > 0;
    }
}
