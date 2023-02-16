package br.tec.db.votacao.model;

import br.tec.db.votacao.enums.SessaoDeVotacaoStatusEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sessao_de_votacao")
@EqualsAndHashCode(of = "id")
public class SessaoDeVotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private LocalDateTime inicio = LocalDateTime.now();

    @NotBlank
    private LocalDateTime fim = LocalDateTime.now().plusMinutes(1);

    @Enumerated(EnumType.STRING)
    private SessaoDeVotacaoStatusEnum status;

    @OneToOne
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;

    @OneToMany
    @JoinTable(name = "sessao_de_votacao_votos", joinColumns = @JoinColumn(name = "sessao_de_votacao_id"),
            inverseJoinColumns = @JoinColumn(name = "voto_id"))
    private List<Voto> votos;

}
