package com.db.votacao.api.model;
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
    private UUID idPauta;

    @Column(name = "titulo_pauta", nullable = false, length = 150)
    private String descricaoTituloPauta;

    @Column(name = "descricao_pauta", nullable = false)
    private String descricaoPauta;

    @Column(name = "data_inicio_pauta", nullable = false)
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
