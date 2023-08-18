package com.db.votacao.api.model;

import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = Pauta.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Pauta {

    public static final String TABLE_NAME = "pauta";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_pauta", unique = true)
    @ApiModelProperty(notes = "ID único da pauta")
    private UUID idPauta;

    @Column(name = "titulo_pauta", nullable = false, length = 150)
    @ApiModelProperty(notes = "Título da pauta")
    private String descricaoTituloPauta;

    @Column(name = "descricao_pauta", nullable = false)
    @ApiModelProperty(notes = "Descrição da pauta")
    private String descricaoPauta;

    @Column(name = "data_inicio_pauta", nullable = false)
    @ApiModelProperty(notes = "Data de início da pauta")
    private LocalDateTime dataInicioPauta = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "sessao_id")
    private Sessao sessao;

    @OneToMany(mappedBy = "pauta", cascade = CascadeType.ALL)
    private List<Voto> votos;

    public Pauta(@NonNull String dsTituloPauta, @NonNull String dsDescricaoPauta) {
        this.descricaoTituloPauta = dsTituloPauta;
        this.descricaoPauta = dsDescricaoPauta;
    }
}
