package com.dbserver.desafiovotacao.model;

import com.dbserver.desafiovotacao.enums.PautaAndamentoEnum;
import com.dbserver.desafiovotacao.enums.PautaResultadoEnum;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "pauta")
public class Pauta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "titulo", length = 30, nullable = false)
    private String titulo;
    @Column(name = "descricao", length = 255)
    private String descricao;
    @OneToOne
    @JoinColumn(name = "autor_pauta")
    private Votante autorPauta;
    @OneToMany
    @JoinTable(name = "pauta_votantes", joinColumns = @JoinColumn(name = "pauta_id"),
            inverseJoinColumns = @JoinColumn(name = "votantes_id"))
    private List<Votante> associados = new ArrayList<>();
    @Column(name = "resultado")
    @Enumerated(EnumType.STRING)
    private PautaResultadoEnum resultado;
    @Column(name = "andamento")
    @Enumerated(EnumType.STRING)
    private PautaAndamentoEnum andamento;
    @Column(name = "hash" , nullable =false)
    private String hash;

}
