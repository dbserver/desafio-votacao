package br.com.aplicationvotacao.aplicationvotacao.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="sessao_votacao")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SessaoVotacao implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "O campo id_pauta é obrigatório.")
    @OneToOne
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;

}

